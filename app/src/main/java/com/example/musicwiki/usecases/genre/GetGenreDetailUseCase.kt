package com.example.musicwiki.usecases.genre

import com.example.musicwiki.BuildConfig
import com.example.musicwiki.data.remote.ApiService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class GetGenreDetailUseCase @Inject constructor(
    private val apiService: ApiService
) {

    private val _genreInfoFlow = MutableStateFlow(GenreDetails())
    val genreInfoFlow: StateFlow<GenreDetails> = _genreInfoFlow

    suspend fun execute(tag: String) {
        try {
            val response = apiService.getTagInfo(tag, BuildConfig.API_KEY)
            _genreInfoFlow.emit(GenreDetails(response.tag.name, response.tag.wiki.summary, response.tag.wiki.content))
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