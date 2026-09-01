package br.com.heiderlopes.passwordmanager.ui.screens.password.list

data class PasswordListItemUiState(
    val id: Long,
    val username: String,
    val serviceName: String,
    val password: String,
    val isPasswordVisible: Boolean = false
)

data class ListPasswordsUiState(
    val items: List<PasswordListItemUiState> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val isEmpty: Boolean = true,
    val pendingDeleteId: Long? = null,
    val snackbarMessage: String? = null,
    val isRefreshing: Boolean = false,
)