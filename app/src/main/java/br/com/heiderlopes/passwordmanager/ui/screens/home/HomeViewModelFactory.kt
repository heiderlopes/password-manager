package br.com.heiderlopes.passwordmanager.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.heiderlopes.passwordmanager.domain.repository.PasswordRepository

class HomeViewModelFactory(
    private val passwordRepository: PasswordRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(passwordRepository) as T
        }

        throw IllegalArgumentException(
            "ViewModel desconhecida: ${modelClass.name}"
        )
    }
}