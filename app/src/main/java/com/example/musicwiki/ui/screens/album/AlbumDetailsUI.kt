package com.example.musicwiki.ui.screens.album

import com.example.musicwiki.data.remote.model.ImageArt
import com.example.musicwiki.data.remote.model.tags.TagList
import com.example.musicwiki.data.remote.model.wiki.Wiki

data class AlbumDetailsUI (
    val name: String,
    val artist: String,
    val imageList: List<ImageArt>,
    val wiki: Wiki,
    val tagList: TagList
    )