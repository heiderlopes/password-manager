package br.com.heiderlopes.passwordmanager.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.heiderlopes.passwordmanager.domain.repository.PasswordRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val passwordRepository: PasswordRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()

    init {
        loadHome()
    }

    private fun loadHome() {
        viewModelScope.launch {
            loadPasswordStats()
        }
    }

    private fun loadPasswordStats() {
        viewModelScope.launch {
            combine(
                passwordRepository.getTotalPasswords(),
                passwordRepository.getTotalReusedPasswords()
            ) { total, reused ->

                PasswordStatsUiState(
                    totalPasswords = total,
                    reusedPasswords = reused,
                    isLoading = false
                )

            }.catch { exception ->

                _uiState.update {
                    it.copy(
                        passwordStats = it.passwordStats.copy(
                            isLoading = false,
                            errorMessage = exception.message
                        )
                    )
                }

            }.collect { stats ->

                _uiState.update {
                    it.copy(
                        passwordStats = stats
                    )
                }
            }
        }
    }
}