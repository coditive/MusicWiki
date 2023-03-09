package com.example.musicwiki.data.remote.model.track

import com.example.musicwiki.data.remote.model.ImageArt
import com.squareup.moshi.Json

data class Track(
    @Json(name = "name") val name: String,
    @Json(name = "duration") val duration: Int,
    @Json(name = "mbid") val mbid: String,
    @Json(name = "url") val url: String,
    @Json(name = "image") val imageList: List<ImageArt>
)