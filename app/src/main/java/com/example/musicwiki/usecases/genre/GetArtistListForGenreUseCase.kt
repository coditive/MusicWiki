package com.example.musicwiki.usecases.genre

import com.example.musicwiki.BuildConfig
import com.example.musicwiki.data.remote.ApiService
import com.example.musicwiki.data.remote.model.artist.Artist
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class GetArtistListForGenreUseCase @Inject constructor(private val apiService: ApiService) {

    private val _artistListForGenre = MutableStateFlow(ArtistListForGenreUI())
    val artistListForGenre: StateFlow<ArtistListForGenreUI> = _artistListForGenre

    suspend fun execute(tag: String) {
        try {
            val response = apiService.getArtistListFromTag(tag, BuildConfig.API_KEY)
            _artistListForGenre.emit(ArtistListForGenreUI(response.topartists.artist))
        } catch (e: Exception) {
            _artistListForGenre.emit(ArtistListForGenreUI(exception = e.localizedMessage))
        }
    }

}

data class ArtistListForGenreUI(
    val artistList: List<Artist>? = null,
    val exception: String? = null
)