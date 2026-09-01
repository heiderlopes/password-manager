package br.com.heiderlopes.passwordmanager.ui.screens.nps

data class NpsUiState(
    val question: String = "",
    val selectedScore: Int? = null,
    val comment: String? = null,
    val isLoading: Boolean = false,
    val isSending: Boolean = false,
    val isSuccess: Boolean = false,
    val errorMessage: String? = null
)