package com.example.musicwiki.data.remote.model.album

import com.example.musicwiki.data.remote.model.ImageArt
import com.example.musicwiki.data.remote.model.tags.TagList
import com.example.musicwiki.data.remote.model.wiki.Wiki
import com.squareup.moshi.Json

data class AlbumInfo (
    val artist: String,
    val mbid: String?,
    val tags: TagList,
    @Json(name = "playcount") val playCount: Long,
    @Json(name = "image") val imageList: List<ImageArt>,
    val url: String,
    val name: String,
    val wiki: Wiki
    )