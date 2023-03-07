package com.example.musicwiki.usecases

import com.example.musicwiki.BuildConfig
import com.example.musicwiki.data.remote.ApiService
import com.example.musicwiki.data.remote.model.artist.Artist
import com.example.musicwiki.data.remote.model.artist.ArtistBio
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class GetArtistDetailUseCase(private val apiService: ApiService) {

    private val _artistDetailFlow = MutableStateFlow(ArtistDetailUI())
    val artistDetailFlow: StateFlow<ArtistDetailUI> = _artistDetailFlow

    suspend fun execute(artist: Artist) {
        try {
            val response = apiService.getArtistInfoByName(artist.name, BuildConfig.API_KEY)
            _artistDetailFlow.emit(ArtistDetailUI(response.name, response.bio, response.stats.playcount, response.stats.listeners))
        } catch (e: Exception) {
            _artistDetailFlow.emit(ArtistDetailUI(exception = e))
        }
    }
}


data class ArtistDetailUI(
    val name: String? = null,
    val bio: ArtistBio? = null,
    val playCount: Long? = null,
    val followers: Long? = null,
    val exception: Exception? = null
)