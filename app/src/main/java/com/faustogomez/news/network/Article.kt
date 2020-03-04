package com.faustogomez.news.network

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Article(
    val title: String,
    val media: List<Media>,
    val url: String
): Parcelable

data class ArticleResponse(
    var results: List<Article>
)

@Parcelize
data class Media(
    val type: String,
    @Json(name = "media-metadata") val mediaMedata: List<MediaMetadata>
):Parcelable

@Parcelize
data class MediaMetadata(
    val url: String
):Parcelable