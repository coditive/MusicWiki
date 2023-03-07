package com.example.musicwiki.usecases

import com.example.musicwiki.BuildConfig
import com.example.musicwiki.data.remote.ApiService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class GetGenreDetailUseCase(
    private val apiService: ApiService
) {

    private val _genreInfoFlow = MutableStateFlow(GenreDetailUI())
    val genreInfoFlow: StateFlow<GenreDetailUI> = _genreInfoFlow

    suspend fun execute(tag: String) {
        try {
            val response = apiService.getTagInfo(tag, BuildConfig.API_KEY)
            _genreInfoFlow.emit(GenreDetailUI(response.name, response.wiki.summary, response.wiki.content))
        } catch (e: Exception) {
            _genreInfoFlow.emit(GenreDetailUI(exception = e))
        }
    }
}


data class GenreDetailUI(
    val tagName: String? = null,
    val summary: String? = null,
    val content: String? = null,
    val exception: Exception? = null
)