package br.com.heiderlopes.passwordmanager.ui.screens.home

import br.com.heiderlopes.passwordmanager.domain.model.Nps

data class HomeUiState(
    val passwordStats: PasswordStatsUiState = PasswordStatsUiState(),
    val nps: HomeNpsUiState = HomeNpsUiState()

)

data class PasswordStatsUiState(
    val totalPasswords: Int = 0,
    val reusedPasswords: Int = 0,
    val isLoading: Boolean = true,
    val errorMessage: String? = null
)

data class HomeNpsUiState(
    val nps: Nps? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)