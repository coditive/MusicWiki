package com.example.musicwiki.usecases.artist

import com.example.musicwiki.BuildConfig
import com.example.musicwiki.data.remote.ApiService
import com.example.musicwiki.data.remote.model.artist.Artist
import com.example.musicwiki.data.remote.model.track.Track
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class GetTopTracksOfArtistUseCase(private val apiService: ApiService) {

    private val _trackListForArtist = MutableStateFlow(TrackListForArtistUI())
    val trackListForArtist: StateFlow<TrackListForArtistUI> = _trackListForArtist

    suspend fun execute(artist: Artist) {
        try {
            val response = apiService.getTopTracksOfArtistByName(artist.name, BuildConfig.API_KEY)
            _trackListForArtist.emit(TrackListForArtistUI(response.topTracks.track))
        } catch (e: Exception) {
            _trackListForArtist.emit(TrackListForArtistUI(exception = e.localizedMessage))
        }
    }

}

data class TrackListForArtistUI(
    val trackList: List<Track>? = null,
    val exception: String? = null
)