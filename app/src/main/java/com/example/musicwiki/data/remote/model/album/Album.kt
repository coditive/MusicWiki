package com.example.musicwiki.data.remote.model.album

import com.example.musicwiki.data.remote.model.ImageArt

data class Album(
    val name: String,
    val mbid: String,
    val url: String,
    val imageList: List<ImageArt>
)
