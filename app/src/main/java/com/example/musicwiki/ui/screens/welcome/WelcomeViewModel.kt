package com.example.musicwiki.ui.screens.welcome

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicwiki.ui.model.UIState
import com.example.musicwiki.usecases.welcome.GetTopTagsListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor(
    private val getTopTagsListUseCase: GetTopTagsListUseCase
): ViewModel() {

    private val expandTagListCheck = MutableStateFlow(false)

    val welcomeUIState: StateFlow<UIState<WelcomeUI>> =
        expandTagListCheck.combine(getTopTagsListUseCase.tagListFlow) { isExpanded, tagList ->
            if (tagList.exception != null) {
                UIState.Error(tagList.exception)
            } else if (tagList.tagList != null) {
                if (isExpanded) {
                    UIState.Success(tagList.toWelcomeUI())
                } else {
                    val tagListFiltered = tagList.tagList.subList(0, 9)
                    UIState.Success(WelcomeUI(tagListFiltered))
                }

            } else {
                UIState.Loading
            }
        }.stateIn(viewModelScope, SharingStarted.Lazily, UIState.Loading)

    fun getTopTagList() {
        viewModelScope.launch(Dispatchers.IO) {
            getTopTagsListUseCase.execute()
        }
    }
    fun expandTagList() {
        expandTagListCheck.value = !expandTagListCheck.value
    }

}