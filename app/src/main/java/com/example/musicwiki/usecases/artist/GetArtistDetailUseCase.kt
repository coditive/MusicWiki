package com.example.musicwiki.usecases.artist

import com.example.musicwiki.BuildConfig
import com.example.musicwiki.data.remote.ApiService
import com.example.musicwiki.data.remote.model.artist.Artist
import com.example.musicwiki.data.remote.model.artist.ArtistBio
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class GetArtistDetailUseCase @Inject constructor(private val apiService: ApiService) {

    private val _artistDetailFlow = MutableStateFlow(ArtistDetailModel())
    val artistDetailFlow: StateFlow<ArtistDetailModel> = _artistDetailFlow

    suspend fun execute(artist: Artist) {
        try {
            val response = apiService.getArtistInfoByName(artist.name, BuildConfig.API_KEY)
            _artistDetailFlow.emit(ArtistDetailModel(response.name, response.bio, response.stats.playcount, response.stats.listeners))
        } catch (e: Exception) {
            _artistDetailFlow.emit(ArtistDetailModel(exception = e.localizedMessage))
        }
    }
}


data class ArtistDetailModel(
    val name: String? = null,
    val bio: ArtistBio? = null,
    val playCount: Long? = null,
    val followers: Long? = null,
    val exception: String? = null
) {
    fun isNotNull() = name != null && bio != null && playCount != null && followers != null
}