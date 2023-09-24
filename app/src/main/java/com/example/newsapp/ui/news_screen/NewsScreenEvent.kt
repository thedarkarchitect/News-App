package com.example.newsapp.ui.news_screen

import com.example.newsapp.domain.modal.Article

sealed interface NewsScreenEvent {
    data class OnNewsCardClicked(val article: Article): NewsScreenEvent
    data class OnCategoryChanged(val category: String): NewsScreenEvent
    data class OnSearchQueryChanged(val searchQuery: String): NewsScreenEvent
    data object OnSearchIconClicked: NewsScreenEvent
    data object OnCloseIconClicked: NewsScreenEvent

}