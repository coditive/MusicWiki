package com.example.musicwiki.ui.screens.welcome

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicwiki.ui.model.UIState
import com.example.musicwiki.usecases.welcome.GetTopTagsListUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class WelcomeViewModel(
    private val getTopTagsListUseCase: GetTopTagsListUseCase
): ViewModel() {

    val welcomeUIState: StateFlow<UIState<WelcomeUI>> = getTopTagsListUseCase.tagListFlow.map {tagList ->
        if(tagList.exception != null) {
            UIState.Error(tagList.exception)
        } else if (tagList.tagList != null) {
            UIState.Success(tagList.toWelcomeUI())
        } else {
            UIState.Loading
        }
    }.stateIn(viewModelScope, SharingStarted.Lazily, UIState.Loading)

    fun getTopTagList() {
        viewModelScope.launch(Dispatchers.IO) {
            getTopTagsListUseCase.execute()
        }
    }

}