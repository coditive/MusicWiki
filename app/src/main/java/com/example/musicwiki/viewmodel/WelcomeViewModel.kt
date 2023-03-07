package com.example.musicwiki.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicwiki.usecases.GetTopTagsListUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WelcomeViewModel(
    private val getTopTagsListUseCase: GetTopTagsListUseCase
): ViewModel() {

    val tagList = getTopTagsListUseCase.tagListFlow

    fun getTopTagList() {
        viewModelScope.launch(Dispatchers.IO) {
            getTopTagsListUseCase.execute()
        }
    }

}