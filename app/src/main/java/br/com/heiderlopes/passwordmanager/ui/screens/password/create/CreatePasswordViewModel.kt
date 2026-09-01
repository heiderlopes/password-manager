package br.com.heiderlopes.passwordmanager.ui.screens.password.create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

import br.com.heiderlopes.passwordmanager.domain.generator.PasswordGenerator
import br.com.heiderlopes.passwordmanager.domain.generator.PinPasswordGenerator
import br.com.heiderlopes.passwordmanager.domain.generator.StandardPasswordGenerator
import br.com.heiderlopes.passwordmanager.domain.model.Password
import br.com.heiderlopes.passwordmanager.domain.model.PasswordType
import br.com.heiderlopes.passwordmanager.domain.repository.PasswordRepository

class CreatePasswordViewModel(
    private val repository: PasswordRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(CreatePasswordUiState())
    val uiState: StateFlow<CreatePasswordUiState> = _uiState

    fun onUsernameChange(value: String) {
        _uiState.update { it.copy(username = value) }
    }

    fun onPasswordChange(value: String) {
        val currentState = _uiState.value

        if (!currentState.canEditPasswordManually) return

        if (value.length <= currentState.maxCharacters) {
            _uiState.update { it.copy(password = value) }
        }
    }

    fun onServiceNameChange(value: String) {
        _uiState.update { it.copy(serviceName = value) }
    }

    fun onPasswordTypeChange(type: PasswordType) {
        _uiState.update { currentState ->
            val adjustedMax = if (type == PasswordType.PIN) {
                minOf(currentState.maxCharacters, 6)
            } else {
                currentState.maxCharacters
            }

            currentState.copy(
                passwordType = type,
                maxCharacters = adjustedMax,
                includeUppercase = if (type == PasswordType.PIN) false else currentState.includeUppercase,
                includeLowercase = if (type == PasswordType.PIN) false else currentState.includeLowercase,
                includeSymbols = if (type == PasswordType.PIN) false else currentState.includeSymbols,
                includeNumbers = true,
                password = currentState.password.take(adjustedMax)
            )
        }
    }

    fun onCanEditPasswordManuallyChange(value: Boolean) {
        _uiState.update { it.copy(canEditPasswordManually = value) }
    }

    fun onTogglePasswordVisibility() {
        _uiState.update { currentState ->
            currentState.copy(
                isPasswordVisible = !currentState.isPasswordVisible
            )
        }
    }

    fun onMaxCharactersChange(value: Float) {
        val sliderValue = value.toInt()

        _uiState.update { currentState ->
            val adjustedMax = if (currentState.passwordType == PasswordType.PIN) {
                minOf(sliderValue, 6)
            } else {
                sliderValue
            }

            currentState.copy(
                maxCharacters = adjustedMax,
                password = currentState.password.take(adjustedMax)
            )
        }
    }

    fun onIncludeUppercaseChange(value: Boolean) {
        _uiState.update { it.copy(includeUppercase = value) }
    }

    fun onIncludeLowercaseChange(value: Boolean) {
        _uiState.update { it.copy(includeLowercase = value) }
    }

    fun onIncludeNumbersChange(value: Boolean) {
        _uiState.update { it.copy(includeNumbers = value) }
    }

    fun onIncludeSymbolsChange(value: Boolean) {
        _uiState.update { it.copy(includeSymbols = value) }
    }

    fun generatePassword(defaultLength: Int) {
        val currentState = _uiState.value

        val generator: PasswordGenerator = when (currentState.passwordType) {
            PasswordType.PIN -> PinPasswordGenerator()
            PasswordType.STANDARD -> StandardPasswordGenerator(
                includeUppercase = if (currentState.canEditPasswordManually) {
                    currentState.includeUppercase
                } else {
                    true
                },
                includeLowercase = if (currentState.canEditPasswordManually) {
                    currentState.includeLowercase
                } else {
                    true
                },
                includeNumbers = if (currentState.canEditPasswordManually) {
                    currentState.includeNumbers
                } else {
                    true
                },
                includeSymbols = if (currentState.canEditPasswordManually) {
                    currentState.includeSymbols
                } else {
                    true
                }
            )
        }

        val passwordLength = if (currentState.canEditPasswordManually) {
            currentState.maxCharacters
        } else {
            defaultLength
        }

        val generatedPassword = generator.generate(passwordLength)

        _uiState.update {
            it.copy(password = generatedPassword)
        }
    }

    fun save() {
        viewModelScope.launch {
            val currentState = _uiState.value

            val password = Password(
                id = currentState.id,
                username = currentState.username,
                password = currentState.password,
                serviceName = currentState.serviceName
            )

            repository.save(password)

            _uiState.update {
                it.copy(
                    isSaved = true,
                    successMessage = "Senha salva com sucesso"
                )
            }
        }
    }

    fun onSuccessMessageShown() {
        _uiState.update { currentState ->
            currentState.copy(successMessage = null)
        }
    }

    fun loadPasswordById(passwordId: Long) {
        viewModelScope.launch {
            val password = repository.findById(passwordId) ?: return@launch

            _uiState.update { currentState ->
                currentState.copy(
                    id = password.id,
                    username = password.username,
                    password = password.password,
                    serviceName = password.serviceName
                )
            }
        }
    }
}