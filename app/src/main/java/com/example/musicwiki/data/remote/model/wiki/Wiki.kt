package com.example.musicwiki.data.remote.model.wiki

import com.squareup.moshi.Json

data class Wiki(
    @Json(name = "summary") val summary: String,
    @Json(name = "content") val content: String
)