package com.example.musicwiki.data.remote.response

import com.example.musicwiki.data.remote.model.artist.Artist
import com.example.musicwiki.data.remote.model.attr.ArtistAttr
import com.squareup.moshi.Json

data class ArtistListWithAttr (
   @Json(name = "artist") val artist: List<Artist>,
   @Json(name = "@attr") val attr: ArtistAttr
    )