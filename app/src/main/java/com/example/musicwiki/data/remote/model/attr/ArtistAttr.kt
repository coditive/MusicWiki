package com.example.musicwiki.data.remote.model.attr

import com.squareup.moshi.Json

data class ArtistAttr(
    @Json(name = "page") val page: Int,
    @Json(name = "perPage") val perPage: Int,
    @Json(name = "totalPages") val totalPages: Int,
    @Json(name = "total") val total: Int
)