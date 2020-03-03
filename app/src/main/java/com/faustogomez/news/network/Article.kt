package com.faustogomez.news.network

import com.squareup.moshi.Json

data class Article(
    val title: String,
    val media: List<Media>
)

data class ArticleResponse(
    var results: List<Article>
)

data class Media(
    val type: String,
    @Json(name = "media-medata") val mediaMedata: List<MediaMetadata>
)

data class MediaMetadata(
    val url: String
)