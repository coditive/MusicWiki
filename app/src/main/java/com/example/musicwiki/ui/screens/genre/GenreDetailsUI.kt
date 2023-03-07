package com.example.musicwiki.ui.screens.genre

import com.example.musicwiki.data.remote.model.album.Album
import com.example.musicwiki.data.remote.model.artist.Artist
import com.example.musicwiki.data.remote.model.track.Track

data class GenreDetailsUI(
    val tagName: String,
    val summary: String,
    val content: String,
    val artistList: List<Artist>,
    val trackList: List<Track>,
    val albumList: List<Album>
)
