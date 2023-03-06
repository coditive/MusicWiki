package com.example.musicwiki.data.remote.model.track

import com.example.musicwiki.data.remote.model.ImageArt

data class Track (
    val name: String,
    val duration: Int,
    val mbid: String,
    val url: String,
    val imageList: List<ImageArt>
    )