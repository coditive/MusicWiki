package com.example.musicwiki.data.remote.response

import com.squareup.moshi.Json

data class TopAlbumOfArtist (
    @Json(name = "topalbums") val topAlbums: AlbumListWithAttr
    )