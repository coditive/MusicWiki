package com.example.musicwiki.ui.screens.genre

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicwiki.data.remote.model.tags.Tag
import com.example.musicwiki.ui.model.UIState
import com.example.musicwiki.usecases.genre.GetAlbumListForGenreUseCase
import com.example.musicwiki.usecases.genre.GetArtistListForGenreUseCase
import com.example.musicwiki.usecases.genre.GetGenreDetailUseCase
import com.example.musicwiki.usecases.genre.GetTracksListForGenreUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class GenreDetailViewModel(
    private val getGenreDetailUseCase: GetGenreDetailUseCase,
    private val getArtistListForGenreUseCase: GetArtistListForGenreUseCase,
    private val getAlbumListForGenreUseCase: GetAlbumListForGenreUseCase,
    private val getTracksListForGenreUseCase: GetTracksListForGenreUseCase
): ViewModel() {

    private val genreInfoFlow = getGenreDetailUseCase.genreInfoFlow
    private val artistListFlow = getArtistListForGenreUseCase.artistListForGenre
    private val trackListFlow = getTracksListForGenreUseCase.trackListForGenre
    private val albumListFlow = getAlbumListForGenreUseCase.albumListForGenre


    val genreDetailsUIState: StateFlow<UIState<GenreDetailsUI>> = combine(
        genreInfoFlow,
        artistListFlow,
        trackListFlow,
        albumListFlow
    ) { genreInfo, artistList, trackList, albumList ->

        if (genreInfo.exception is String || artistList.exception is String || trackList.exception is String || albumList.exception is String) {
            if (genreInfo.exception is String) UIState.Error(genreInfo.exception)
            else if (artistList.exception is String) UIState.Error(artistList.exception)
            else if (trackList.exception is String) UIState.Error(trackList.exception)
            else UIState.Error(albumList.exception!!)
        } else if (genreInfo.isNotNull() && artistList.artistList != null && trackList.trackList != null && albumList.albumList != null) {
            UIState.Success(
                GenreDetailsUI(
                    genreInfo.tagName!!,
                    genreInfo.summary!!,
                    genreInfo.content!!,
                    artistList.artistList,
                    trackList.trackList,
                    albumList.albumList
                )
            )
        } else {
            UIState.Loading
        }
    }.stateIn(viewModelScope, SharingStarted.Lazily, UIState.Loading)


    fun getGenreDetailsFromTag(tag: Tag) {
        viewModelScope.launch(Dispatchers.IO) {
            getGenreDetailUseCase.execute(tag)
            getArtistListForGenreUseCase.execute(tag)
            getTracksListForGenreUseCase.execute(tag)
            getAlbumListForGenreUseCase.execute(tag)
        }
    }

}