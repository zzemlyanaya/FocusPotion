package dev.zzemlyanaya.focuspotion.core.contract

interface BaseIntent {
    data object Back : BaseIntent
}

sealed class ScreenUiState<UiState>(val state: UiState) {
    class Error<UiState>(val error: String, state: UiState) : ScreenUiState<UiState>(state)
    class Data<UiState>(state: UiState) : ScreenUiState<UiState>(state)
}