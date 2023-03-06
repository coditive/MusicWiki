package com.example.musicwiki.data.remote.response

import com.example.musicwiki.data.remote.model.tags.TagInfo
import com.squareup.moshi.Json

data class TagWithWiki(
    @Json(name = "tag") val tag: TagInfo
)