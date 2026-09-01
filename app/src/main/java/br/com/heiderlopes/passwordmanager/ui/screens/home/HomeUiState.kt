package br.com.heiderlopes.passwordmanager.ui.screens.home

data class HomeUiState(
    val passwordStats: PasswordStatsUiState = PasswordStatsUiState()
)

data class PasswordStatsUiState(
    val totalPasswords: Int = 0,
    val reusedPasswords: Int = 0,
    val isLoading: Boolean = true,
    val errorMessage: String? = null
)