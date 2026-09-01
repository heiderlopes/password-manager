package br.com.heiderlopes.passwordmanager.ui.screens.password.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.heiderlopes.passwordmanager.domain.model.Password
import br.com.heiderlopes.passwordmanager.domain.repository.PasswordRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ListPasswordsViewModel(
    private val repository: PasswordRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(ListPasswordsUiState(isLoading = true))
    val uiState: StateFlow<ListPasswordsUiState> = _uiState.asStateFlow()

    init {
        loadPasswords()
    }

    fun loadPasswords() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }

            runCatching {
                repository.getAll()
            }.onSuccess { passwords ->
                val items = passwords.map { password ->
                    password.toListItemUiState()
                }

                _uiState.update {
                    it.copy(
                        items = items,
                        isLoading = false,
                        isEmpty = items.isEmpty()
                    )
                }
            }.onFailure { throwable ->
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = throwable.message,
                        isEmpty = true
                    )
                }
            }
        }
    }

    fun onTogglePasswordVisibility(id: Long) {
        _uiState.update { currentState ->
            currentState.copy(
                items = currentState.items.map { item ->
                    if (item.id == id) {
                        item.copy(isPasswordVisible = !item.isPasswordVisible)
                    } else {
                        item
                    }
                }
            )
        }
    }

    fun onDeleteClick(id: Long) {
        _uiState.update { currentState ->
            currentState.copy(pendingDeleteId = id)
        }
    }

    fun onDismissDeleteDialog() {
        _uiState.update { currentState ->
            currentState.copy(pendingDeleteId = null)
        }
    }

    fun onConfirmDelete() {
        val id = _uiState.value.pendingDeleteId ?: return

        viewModelScope.launch {
            repository.deleteById(id)
            val updatedPasswords = repository.getAll().map { password ->
                password.toListItemUiState()
            }

            _uiState.update { currentState ->
                currentState.copy(
                    items = updatedPasswords,
                    isEmpty = updatedPasswords.isEmpty(),
                    pendingDeleteId = null,
                    snackbarMessage = "Senha excluída com sucesso"
                )
            }
        }
    }

    fun onSnackbarShown() {
        _uiState.update { currentState ->
            currentState.copy(snackbarMessage = null)
        }
    }

    private fun Password.toListItemUiState(): PasswordListItemUiState {
        return PasswordListItemUiState(
            id = id,
            serviceName = serviceName,
            password = password,
            username = username
        )
    }
}