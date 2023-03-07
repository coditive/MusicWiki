package com.example.musicwiki.ui.model

sealed class UIState<out T> {

    data class Success<T> (val data: T): UIState<T>()

    data class Error(val message: String): UIState<Nothing>()

    object Loading: UIState<Nothing>()


    val isLoading get() = this is Loading
    val isSuccessful get() = this is Success
    val isError get() = this is Error

    fun handleSuccess(action: (T) -> Unit): UIState<T> {
        if (this is Success) {
            action(data)
        }
        return this
    }

    fun handleError(action: (String) -> Unit): UIState<T> {
        if (this is Error) {
            action(message)
        }
        return this
    }

    fun handleLoading(action: () -> Unit): UIState<T> {
        if (this is Loading) {
            action()
        }
        return this
    }

}