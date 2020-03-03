package com.faustogomez.news.network

data class Article(
    val title: String
)

data class ArticleResponse(
    var results: List<Article>
)