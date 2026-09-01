package br.com.heiderlopes.passwordmanager.ui.screens.nps

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.heiderlopes.passwordmanager.domain.repository.NpsRepository

class NpsViewModelFactory(
    private val npsRepository: NpsRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NpsViewModel::class.java)) {
            return NpsViewModel(npsRepository) as T
        }

        throw IllegalArgumentException(
            "ViewModel desconhecida: ${modelClass.name}"
        )
    }
}