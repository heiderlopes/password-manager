package br.com.heiderlopes.passwordmanager.ui.screens.onboarding

import androidx.lifecycle.ViewModel
import br.com.heiderlopes.passwordmanager.domain.repository.OnboardingRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class OnboardingViewModel(
    private val repository: OnboardingRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(
        OnboardingUiState()
    )

    val uiState = _uiState.asStateFlow()

    fun nextPage() {
        _uiState.update {
            it.copy(
                currentPage = it.currentPage + 1
            )
        }
    }

    fun previousPage() {
        _uiState.update {
            it.copy(
                currentPage = it.currentPage - 1
            )
        }
    }

    fun onSkipOnboardingChange(
        checked: Boolean
    ) {
        _uiState.update {
            it.copy(
                skipOnboarding = checked
            )
        }
    }

    suspend fun finishOnboarding(): Result<Unit> {

        _uiState.update {
            it.copy(
                isLoading = true
            )
        }

        return try {

            repository.setSkipOnboarding(
                _uiState.value.skipOnboarding
            )

            _uiState.update {
                it.copy(
                    isLoading = false
                )
            }

            Result.success(Unit)

        } catch (e: Exception) {

            _uiState.update {
                it.copy(
                    isLoading = false
                )
            }

            Result.failure(e)
        }
    }
}