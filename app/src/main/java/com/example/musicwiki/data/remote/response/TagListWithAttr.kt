package com.example.musicwiki.data.remote.response

import com.example.musicwiki.data.remote.model.attr.TagAttr
import com.example.musicwiki.data.remote.model.tags.Tag
import com.squareup.moshi.Json

data class TagListWithAttr (
    @Json(name = "tag") val tag: List<Tag>,
    @Json(name = "@attr") val attr: TagAttr
    )