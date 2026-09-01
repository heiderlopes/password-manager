package br.com.heiderlopes.passwordmanager.ui.screens.password.create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.heiderlopes.passwordmanager.domain.repository.PasswordRepository

class CreatePasswordViewModelFactory(
    private val repository: PasswordRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CreatePasswordViewModel::class.java)) {
            return CreatePasswordViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}