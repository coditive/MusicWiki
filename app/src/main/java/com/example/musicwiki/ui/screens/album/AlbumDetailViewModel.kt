package com.example.musicwiki.ui.screens.album

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicwiki.ui.model.UIState
import com.example.musicwiki.usecases.album.GetAlbumDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AlbumDetailViewModel @Inject constructor(
    private val getAlbumDetailUseCase: GetAlbumDetailUseCase
): ViewModel() {

    val albumDetailsUIState: StateFlow<UIState<AlbumDetailsUI>> =
        getAlbumDetailUseCase.albumDetailFlow.map { albumDetails ->
            if (albumDetails.exception != null) {
                UIState.Error(albumDetails.exception)
            } else if(albumDetails.isNotNull()) {
                UIState.Success(albumDetails.toAlbumDetailsUI())
            } else {
                UIState.Loading
            }
        }.stateIn(viewModelScope, SharingStarted.Lazily, UIState.Loading)

    fun getAlbumDetails(album: String, mbid: String) {
        viewModelScope.launch(Dispatchers.IO) {
            getAlbumDetailUseCase.execute(album, mbid)
        }
    }
}