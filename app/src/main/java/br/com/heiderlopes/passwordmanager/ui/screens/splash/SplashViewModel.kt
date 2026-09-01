package br.com.heiderlopes.passwordmanager.ui.screens.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.heiderlopes.passwordmanager.domain.repository.OnboardingRepository
import br.com.heiderlopes.passwordmanager.navigation.Routes
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SplashViewModel(
    private val onboardingRepository: OnboardingRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(SplashUiState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            onboardingRepository.skipOnboarding.collect { skipOnboarding ->
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        destination = if (skipOnboarding) {
                            Routes.Home.route
                        } else {
                            Routes.Onboarding.route
                        }
                    )
                }
            }
        }
    }
}
