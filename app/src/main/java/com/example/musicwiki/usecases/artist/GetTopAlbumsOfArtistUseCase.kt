package com.example.musicwiki.usecases.artist

import com.example.musicwiki.BuildConfig
import com.example.musicwiki.data.remote.ApiService
import com.example.musicwiki.data.remote.model.album.Album
import com.example.musicwiki.data.remote.model.artist.Artist
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class GetTopAlbumsOfArtistUseCase(private val apiService: ApiService) {

    private val _albumListForArtist = MutableStateFlow(AlbumListForArtistUI())
    val albumListForArtist: StateFlow<AlbumListForArtistUI> = _albumListForArtist

    suspend fun execute(artist: Artist) {
        try {
            val response = apiService.getTopAlbumsOfArtistByName(artist.name, BuildConfig.API_KEY)
            _albumListForArtist.emit(AlbumListForArtistUI(response.topAlbums.album))
        } catch (e: Exception) {
            _albumListForArtist.emit(AlbumListForArtistUI(exception = e.localizedMessage))
        }
    }

}

data class AlbumListForArtistUI(
    val albumList: List<Album>? = null,
    val exception: String? = null
)