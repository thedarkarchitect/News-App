package com.example.newsapp.domain.repository

import com.example.newsapp.domain.modal.Article
import com.example.newsapp.util.Resource

interface NewsRepository {
    suspend fun getTopHeadlines(
        category: String
    ): Resource<List<Article>>//data passed into the sealed class

    suspend fun searchForNews(
        query: String
    ): Resource<List<Article>>
}