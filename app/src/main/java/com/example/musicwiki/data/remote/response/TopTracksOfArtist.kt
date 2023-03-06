package com.example.musicwiki.data.remote.response

import com.squareup.moshi.Json

data class TopTracksOfArtist(
    @Json(name = "toptracks") val topTracks: TrackListWithAttr
)