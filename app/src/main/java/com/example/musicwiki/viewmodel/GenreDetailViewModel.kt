package com.example.musicwiki.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicwiki.usecases.GetGenreDetailUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GenreDetailViewModel(
    private val getGenreDetailUseCase: GetGenreDetailUseCase
): ViewModel() {

    val genreInfoFlow = getGenreDetailUseCase.genreInfoFlow

    fun getGenreDetailsFromTag(tag: String) {
        viewModelScope.launch(Dispatchers.IO) {
            getGenreDetailUseCase.execute(tag)
        }
    }

}