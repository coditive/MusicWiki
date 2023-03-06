package com.example.musicwiki.data.remote.response

import com.example.musicwiki.data.remote.model.album.Album
import com.example.musicwiki.data.remote.model.attr.ArtistAttr
import com.squareup.moshi.Json

data class AlbumListWithAttr(
    @Json(name = "album") val album: List<Album>,
    @Json(name = "@attr") val attr: ArtistAttr
)