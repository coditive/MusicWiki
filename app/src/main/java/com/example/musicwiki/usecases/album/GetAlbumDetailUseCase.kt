package com.example.musicwiki.usecases.album

import com.example.musicwiki.BuildConfig
import com.example.musicwiki.data.remote.ApiService
import com.example.musicwiki.data.remote.model.ImageArt
import com.example.musicwiki.data.remote.model.tags.TagList
import com.example.musicwiki.data.remote.model.wiki.Wiki
import com.example.musicwiki.ui.screens.album.AlbumDetailsUI
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class GetAlbumDetailUseCase @Inject constructor(private val apiService: ApiService) {

    private val _albumDetailsFlow = MutableStateFlow(AlbumDetails())
    val albumDetailFlow: StateFlow<AlbumDetails> = _albumDetailsFlow

    suspend fun execute(album: String, mbid: String) {
        try {
            val response = apiService.getAlbumInfoByName(album, mbid, BuildConfig.API_KEY)
            _albumDetailsFlow.emit(
                AlbumDetails(
                    response.album.name,
                    response.album.artist,
                    response.album.imageList,
                    response.album.wiki,
                    response.album.tags
                )
            )
        } catch (e: Exception) {
            _albumDetailsFlow.emit(AlbumDetails(exception = e.localizedMessage))
        }
    }
}


data class AlbumDetails(
    val name: String? = null,
    val artist: String? = null,
    val imageList: List<ImageArt>? = null,
    val wiki: Wiki? = null,
    val tagList: TagList? = null,
    val exception: String? = null
) {
    fun toAlbumDetailsUI(): AlbumDetailsUI = AlbumDetailsUI(
        this.name!!, this.artist!!, this.imageList!!, this.wiki!!, this.tagList!!
    )

    fun isNotNull() = name != null && artist != null && imageList != null && wiki != null
}