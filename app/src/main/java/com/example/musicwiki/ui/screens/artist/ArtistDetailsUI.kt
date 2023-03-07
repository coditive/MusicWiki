package com.example.musicwiki.ui.screens.artist

import com.example.musicwiki.data.remote.model.album.Album
import com.example.musicwiki.data.remote.model.artist.ArtistBio
import com.example.musicwiki.data.remote.model.track.Track

data class ArtistDetailsUI(
    val name: String,
    val bio: ArtistBio,
    val playCount: Long,
    val followers: Long,
    val albumList: List<Album>,
    val trackList: List<Track>,
    )