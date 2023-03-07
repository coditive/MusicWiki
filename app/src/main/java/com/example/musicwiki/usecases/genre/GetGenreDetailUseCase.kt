package com.example.musicwiki.usecases.genre

import com.example.musicwiki.BuildConfig
import com.example.musicwiki.data.remote.ApiService
import com.example.musicwiki.data.remote.model.tags.Tag
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class GetGenreDetailUseCase(
    private val apiService: ApiService
) {

    private val _genreInfoFlow = MutableStateFlow(GenreDetails())
    val genreInfoFlow: StateFlow<GenreDetails> = _genreInfoFlow

    suspend fun execute(tag: Tag) {
        try {
            val response = apiService.getTagInfo(tag.name, BuildConfig.API_KEY)
            _genreInfoFlow.emit(GenreDetails(response.name, response.wiki.summary, response.wiki.content))
        } catch (e: Exception) {
            _genreInfoFlow.emit(GenreDetails(exception = e.localizedMessage))
        }
    }
}


data class GenreDetails(
    val tagName: String? = null,
    val summary: String? = null,
    val content: String? = null,
    val exception: String? = null
) {
    fun isNotNull() = tagName != null && summary != null && content != null
}