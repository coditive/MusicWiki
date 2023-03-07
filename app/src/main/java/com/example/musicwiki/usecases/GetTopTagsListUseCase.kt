package com.example.musicwiki.usecases

import com.example.musicwiki.BuildConfig
import com.example.musicwiki.data.remote.ApiService
import com.example.musicwiki.data.remote.model.tags.Tag
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class GetTopTagsListUseCase(private val apiService: ApiService) {

    private val _tagListFlow = MutableStateFlow(TagListUI())
    val tagListFlow: StateFlow<TagListUI> = _tagListFlow

    suspend fun execute() {
        try {
            val response = apiService.getTopTagListFromChart(BuildConfig.API_KEY)
            _tagListFlow.emit(TagListUI(tagList = response.tags.tag))
        } catch (e: Exception) {
            _tagListFlow.emit(TagListUI(exception = e))
        }
    }
}


data class TagListUI(
    val tagList: List<Tag>? = null,
    val exception: Exception? = null
)