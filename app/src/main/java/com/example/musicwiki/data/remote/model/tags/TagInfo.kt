package com.example.musicwiki.data.remote.model.tags

import com.example.musicwiki.data.remote.model.wiki.Wiki

data class TagInfo (
    val name: String,
    val total: Long,
    val reach: Long,
    val wiki: Wiki
    )