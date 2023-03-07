package com.example.musicwiki.usecases.genre

import com.example.musicwiki.BuildConfig
import com.example.musicwiki.data.remote.ApiService
import com.example.musicwiki.data.remote.model.tags.Tag
import com.example.musicwiki.data.remote.model.track.Track
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class GetTracksListForGenreUseCase @Inject constructor(private val apiService: ApiService) {

    private val _trackListForGenre = MutableStateFlow(TrackListForGenreUI())
    val trackListForGenre: StateFlow<TrackListForGenreUI> = _trackListForGenre

    suspend fun execute(tag: Tag) {
        try {
            val response = apiService.getTrackListFromTag(tag.name, BuildConfig.API_KEY)
            _trackListForGenre.emit(TrackListForGenreUI(response.tracks.track))
        } catch (e: Exception) {
            _trackListForGenre.emit(TrackListForGenreUI(exception = e.localizedMessage))
        }
    }

}

data class TrackListForGenreUI(
    val trackList: List<Track>? = null,
    val exception: String? = null
)