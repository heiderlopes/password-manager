package br.com.heiderlopes.passwordmanager.ui.screens.password.create

import br.com.heiderlopes.passwordmanager.domain.model.PasswordType

data class CreatePasswordUiState(
    val id: Long = 0L,
    val username: String = "",
    val password: String = "",
    val serviceName: String = "",
    val passwordType: PasswordType = PasswordType.PIN,
    val canEditPasswordManually: Boolean = true,
    val maxCharacters: Int = 18,
    val includeUppercase: Boolean = true,
    val includeLowercase: Boolean = true,
    val includeNumbers: Boolean = true,
    val includeSymbols: Boolean = false,
    val isSaving: Boolean = false,
    val isSaved: Boolean = false,
    val errorMessage: String? = null,
    val isPasswordVisible: Boolean = false,
    val successMessage: String? = null

    )