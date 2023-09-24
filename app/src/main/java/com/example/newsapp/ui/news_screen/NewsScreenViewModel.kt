package com.example.newsapp.ui.news_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.domain.repository.NewsRepository
import com.example.newsapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsScreenViewModel @Inject constructor(
    private val newsRepository: NewsRepository
): ViewModel() {

//    var articles by mutableStateOf<List<Article>>(emptyList())

    var state by mutableStateOf(NewsScreenState())

    private var searchJob: Job? = null

    fun onEvent(event: NewsScreenEvent) {
        when(event) {
            is NewsScreenEvent.OnCategoryChanged -> {
                state = state.copy(category = event.category)//this will change category on swipe pickin string name of tab
                getNewsArticles(state.category)//this will make an api request of only that tab category
            }
            NewsScreenEvent.OnCloseIconClicked -> {
                state = state.copy(isSearchBarVisible = false)
                getNewsArticles(category = state.category)
            }
            is NewsScreenEvent.OnNewsCardClicked -> {
                state = state.copy(selectedArticle = event.article )
            }
            is NewsScreenEvent.OnSearchIconClicked -> {
                state = state.copy(isSearchBarVisible = true, articles = emptyList())
            }
            is NewsScreenEvent.OnSearchQueryChanged -> {
                state = state.copy(searchQuery = event.searchQuery)
                searchJob?.cancel()
                searchJob = viewModelScope.launch {
                    delay(1000)
                    searchForNews(query = state.searchQuery)
                }

            }
        }
    }

    init{
        getNewsArticles("general")
    }

    private fun getNewsArticles(category: String){
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            when(val result = newsRepository.getTopHeadlines(category = category)) {
                is Resource.Success -> {
                    state = state.copy(//the state will collect the request results
                        articles = result.data ?: emptyList(),
                        isLoading = false,
                        error = null
                    )
                }

                is Resource.Error -> {
                    state = state.copy(
                        error = result.message,
                        isLoading = false,
                        articles = emptyList()
                    )
                }
            }
        }
    }

    private fun searchForNews(query: String){
        if(query.isEmpty()){
            return
        }
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            when(val result = newsRepository.searchForNews(query = query)) {
                is Resource.Success -> {
                    state = state.copy(//the state will collect the request results
                        articles = result.data ?: emptyList(),
                        isLoading = false,
                        error = null
                    )
                }

                is Resource.Error -> {
                    state = state.copy(
                        error = result.message,
                        isLoading = false,
                        articles = emptyList()
                    )
                }
            }
        }
    }
}