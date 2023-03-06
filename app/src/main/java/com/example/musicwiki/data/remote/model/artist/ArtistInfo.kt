package com.example.musicwiki.data.remote.model.artist

import com.example.musicwiki.data.remote.model.ImageArt

data class ArtistInfo(
    val name: String,
    val mbid: String,
    val url: String,
    val imageList: List<ImageArt>,
    val stats: ArtistStats,
    val bio: ArtistBio
)