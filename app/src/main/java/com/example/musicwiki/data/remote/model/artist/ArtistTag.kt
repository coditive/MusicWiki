package com.example.musicwiki.data.remote.model.artist

import com.squareup.moshi.Json

data class ArtistTag(
    @Json(name = "count") val count: Int,
    @Json(name = "name") val name: String,
    @Json(name = "url") val url: String
)