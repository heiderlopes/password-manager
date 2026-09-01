package br.com.heiderlopes.passwordmanager.ui.screens.nps

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.heiderlopes.passwordmanager.domain.repository.NpsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class NpsViewModel(
    private val repository: NpsRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(NpsUiState())

    val uiState = _uiState.asStateFlow()

    fun loadNps(surveyId: Long) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    isLoading = true,
                    errorMessage = null
                )
            }
            runCatching {
                repository.getNpsById(
                    surveyId
                )
            }.onSuccess { nps ->
                _uiState.update {
                    it.copy(
                        question = nps.question,
                        isLoading = false
                    )
                }
            }.onFailure { exception ->
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        errorMessage =
                            exception.message
                    )
                }
            }
        }
    }

    fun onScoreSelected(
        score: Int
    ) {

        _uiState.update {
            it.copy(
                selectedScore = score
            )
        }
    }

    fun onCommentChange(value: String) {

        _uiState.update {
            it.copy(
                comment = value
            )
        }
    }

    fun submit(surveyId: Long, comment: String?) {
        val score =
            _uiState.value.selectedScore
                ?: return

        viewModelScope.launch {

            _uiState.update {
                it.copy(
                    isSending = true,
                    errorMessage = null
                )
            }

            runCatching {

                repository.sendResponse(
                    id = surveyId,
                    score = score,
                    comment = comment
                )

            }.onSuccess {

                _uiState.update {
                    it.copy(
                        isSending = false,
                        isSuccess = true
                    )
                }

            }.onFailure { exception ->

                _uiState.update {
                    it.copy(
                        isSending = false,
                        errorMessage =
                            exception.message
                    )
                }
            }
        }
    }
}