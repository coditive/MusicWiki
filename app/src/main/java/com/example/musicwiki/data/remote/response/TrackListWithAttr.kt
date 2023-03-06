package com.example.musicwiki.data.remote.response

import com.example.musicwiki.data.remote.model.attr.ArtistAttr
import com.example.musicwiki.data.remote.model.track.Track
import com.squareup.moshi.Json

data class TrackListWithAttr (
    val track: List<Track>,
    @Json(name = "@attr") val attr: ArtistAttr
    )