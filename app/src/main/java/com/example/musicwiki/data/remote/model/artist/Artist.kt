package com.example.musicwiki.data.remote.model.artist

import com.example.musicwiki.data.remote.model.ImageArt

data class Artist (
    val name: String,
    val mbid: String,
    val url: String,
    val imageList: List<ImageArt>
    )