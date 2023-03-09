package com.example.musicwiki.data.remote.model.artist

import com.example.musicwiki.data.remote.model.ImageArt
import com.squareup.moshi.Json

data class ArtistInfo(
    @Json(name = "name") val name: String,
    @Json(name = "mbid") val mbid: String?,
    @Json(name = "url") val url: String,
    @Json(name = "image") val imageList: List<ImageArt>,
    @Json(name = "stats") val stats: ArtistStats,
    @Json(name = "bio") val bio: ArtistBio
)