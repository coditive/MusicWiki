package com.example.musicwiki.data.remote.model.artist

import com.squareup.moshi.Json

data class ArtistStats(
    @Json(name = "listeners") val listeners: Long,
    @Json(name = "playcount") val playcount: Long
)