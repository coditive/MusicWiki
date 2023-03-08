package com.example.musicwiki.usecases.welcome

import android.util.Log
import com.example.musicwiki.BuildConfig
import com.example.musicwiki.data.remote.ApiService
import com.example.musicwiki.data.remote.model.tags.Tag
import com.example.musicwiki.ui.screens.welcome.WelcomeUI
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class GetTopTagsListUseCase @Inject constructor(private val apiService: ApiService) {

    private val _tagListFlow = MutableStateFlow(TagListModel())
    val tagListFlow: StateFlow<TagListModel> = _tagListFlow

    suspend fun execute() {
        Log.e("welcome","execute Called!!")
        try {
            val response = apiService.getTopTagListFromChart(BuildConfig.API_KEY)
            _tagListFlow.emit(TagListModel(tagList = response.tags.tag))
        } catch (e: Exception) {
            Log.e("welcome",e.message!!)
            _tagListFlow.emit(TagListModel(exception = e.localizedMessage))
        }
    }
}


data class TagListModel(
    val tagList: List<Tag>? = null,
    val exception: String? = null
) {
    fun toWelcomeUI(): WelcomeUI = WelcomeUI(tagList!!)
}