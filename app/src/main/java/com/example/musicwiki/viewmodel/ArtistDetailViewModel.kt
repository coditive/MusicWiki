package com.example.musicwiki.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicwiki.data.remote.model.artist.Artist
import com.example.musicwiki.usecases.GetArtistDetailUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ArtistDetailViewModel(
    private val getArtistDetailUseCase: GetArtistDetailUseCase
): ViewModel() {

    val artistDetail = getArtistDetailUseCase.artistDetailFlow

    fun getArtistDetails(artist: Artist) {
        viewModelScope.launch(Dispatchers.IO) {
            getArtistDetailUseCase.execute(artist)
        }
    }

}