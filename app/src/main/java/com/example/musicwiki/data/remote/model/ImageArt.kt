package com.example.musicwiki.data.remote.model

import com.squareup.moshi.Json

data class ImageArt(
    @Json(name = "#text") val imageUrl: String?,
    @Json(name = "size") val size: String
)
