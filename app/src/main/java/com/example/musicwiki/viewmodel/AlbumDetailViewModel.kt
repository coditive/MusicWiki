package com.example.musicwiki.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicwiki.data.remote.model.album.Album
import com.example.musicwiki.usecases.GetAlbumDetailUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AlbumDetailViewModel(
    private val getAlbumDetailUseCase: GetAlbumDetailUseCase
): ViewModel() {

    val albumDetails = getAlbumDetailUseCase.albumDetailFlow

    fun getAlbumDetails(album: Album) {
        viewModelScope.launch(Dispatchers.IO) {
            getAlbumDetailUseCase.execute(album)
        }
    }
}