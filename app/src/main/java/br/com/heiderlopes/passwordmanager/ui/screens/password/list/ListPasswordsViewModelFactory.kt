package br.com.heiderlopes.passwordmanager.ui.screens.password.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.heiderlopes.passwordmanager.domain.repository.PasswordRepository

class ListPasswordsViewModelFactory(
    private val repository: PasswordRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ListPasswordsViewModel::class.java)) {
            return ListPasswordsViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}