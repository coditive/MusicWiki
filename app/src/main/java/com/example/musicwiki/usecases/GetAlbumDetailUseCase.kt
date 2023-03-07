package com.example.musicwiki.usecases

import com.example.musicwiki.BuildConfig
import com.example.musicwiki.data.remote.ApiService
import com.example.musicwiki.data.remote.model.ImageArt
import com.example.musicwiki.data.remote.model.album.Album
import com.example.musicwiki.data.remote.model.wiki.Wiki
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class GetAlbumDetailUseCase(private val apiService: ApiService) {

    private val _albumDetailsFlow = MutableStateFlow(AlbumDetailUI())
    val albumDetailFlow: StateFlow<AlbumDetailUI> = _albumDetailsFlow

    suspend fun execute(album: Album) {
        try {
            val response = apiService.getAlbumInfoByName(album.name, BuildConfig.API_KEY)
            _albumDetailsFlow.emit(
                AlbumDetailUI(
                    response.album.name,
                    response.album.artist,
                    response.album.imageList,
                    response.album.wiki
                )
            )
        } catch (e: Exception) {
            _albumDetailsFlow.emit(AlbumDetailUI(exception = e))
        }
    }
}


data class AlbumDetailUI(
    val name: String? = null,
    val artist: String? = null,
    val imageList: List<ImageArt>? = null,
    val wiki: Wiki? = null,
    val exception: Exception? = null
)