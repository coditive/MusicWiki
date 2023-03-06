package com.example.musicwiki.data.remote.response

import com.squareup.moshi.Json

data class ArtistTopTagWithAttr (
    @Json(name = "toptags") val topTags: ArtistTopTag
    )