package com.example.musicwiki.data.remote.model.artist

import com.squareup.moshi.Json

data class ArtistBio(
    @Json(name = "published") val published: String,
    @Json(name = "summary") val summary: String,
    @Json(name = "content") val content: String
)
