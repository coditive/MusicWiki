package com.example.musicwiki.data.remote.model.tags

data class Tag (
    val name: String,
    val url: String,
    val reach: Long,
    val taggings: Long
    )