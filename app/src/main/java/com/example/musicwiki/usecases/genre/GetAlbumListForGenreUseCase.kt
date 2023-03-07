package com.example.musicwiki.usecases.genre

import com.example.musicwiki.BuildConfig
import com.example.musicwiki.data.remote.ApiService
import com.example.musicwiki.data.remote.model.album.Album
import com.example.musicwiki.data.remote.model.tags.Tag
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class GetAlbumListForGenreUseCase(private val apiService: ApiService) {

    private val _albumListForGenre = MutableStateFlow(AlbumListForGenreUI())
    val albumListForGenre: StateFlow<AlbumListForGenreUI> = _albumListForGenre

    suspend fun execute(tag: Tag) {
        try {
            val response = apiService.getAlbumListFromTag(tag.name, BuildConfig.API_KEY)
            _albumListForGenre.emit(AlbumListForGenreUI(response.albums.album))
        } catch (e: Exception) {
            _albumListForGenre.emit(AlbumListForGenreUI(exception = e.localizedMessage))
        }
    }

}

data class AlbumListForGenreUI(
    val albumList: List<Album>? = null,
    val exception: String? = null
)