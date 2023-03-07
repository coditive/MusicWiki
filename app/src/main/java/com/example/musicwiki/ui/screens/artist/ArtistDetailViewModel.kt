package com.example.musicwiki.ui.screens.artist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicwiki.data.remote.model.artist.Artist
import com.example.musicwiki.ui.model.UIState
import com.example.musicwiki.usecases.artist.GetArtistDetailUseCase
import com.example.musicwiki.usecases.artist.GetTopAlbumsOfArtistUseCase
import com.example.musicwiki.usecases.artist.GetTopTracksOfArtistUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArtistDetailViewModel @Inject constructor(
    private val getArtistDetailUseCase: GetArtistDetailUseCase,
    private val getTopAlbumsOfArtistUseCase: GetTopAlbumsOfArtistUseCase,
    private val getTopTracksOfArtistUseCase: GetTopTracksOfArtistUseCase
): ViewModel() {

    private val artistDetail = getArtistDetailUseCase.artistDetailFlow

    private val topAlbumList = getTopAlbumsOfArtistUseCase.albumListForArtist

    private val topTrackList = getTopTracksOfArtistUseCase.trackListForArtist


    val artistDetailModelState: StateFlow<UIState<ArtistDetailsUI>> = combine(
        artistDetail,
        topAlbumList,
        topTrackList
    ) { artistDetails, topAlbumList, topTrackList ->
        if (artistDetails.exception is String || topAlbumList.exception is String || topTrackList.exception is String) {
            if (artistDetails.exception is String) UIState.Error(artistDetails.exception)
            else if (topAlbumList.exception is String) UIState.Error(topAlbumList.exception)
            else UIState.Error(topTrackList.exception!!)
        } else if (artistDetails.isNotNull() && topAlbumList.albumList != null && topTrackList.trackList != null) {
            UIState.Success(
                ArtistDetailsUI(
                    artistDetails.name!!,
                    artistDetails.bio!!,
                    artistDetails.playCount!!,
                    artistDetails.followers!!,
                    topAlbumList.albumList,
                    topTrackList.trackList
                )
            )
        } else {
            UIState.Loading
        }
    }.stateIn(viewModelScope, SharingStarted.Lazily, UIState.Loading)

    fun getArtistDetails(artist: Artist) {
        viewModelScope.launch(Dispatchers.IO) {
            getArtistDetailUseCase.execute(artist)
            getTopAlbumsOfArtistUseCase.execute(artist)
            getTopTracksOfArtistUseCase.execute(artist)
        }
    }

}