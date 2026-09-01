package br.com.heiderlopes.passwordmanager.ui.screens.onboarding

data class OnboardingUiState(
    val currentPage: Int = 0,
    val skipOnboarding: Boolean = false,
    val isLoading: Boolean = false
)