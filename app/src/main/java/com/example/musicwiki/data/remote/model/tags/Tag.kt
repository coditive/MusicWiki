package com.example.musicwiki.data.remote.model.tags

import com.squareup.moshi.Json

data class Tag(
    @Json(name = "name") val name: String,
    @Json(name = "url") val url: String
)