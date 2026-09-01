package br.com.heiderlopes.passwordmanager.domain.repository

import br.com.heiderlopes.passwordmanager.domain.model.Nps

interface NpsRepository {

    suspend fun getCurrentNps(): Nps?

    suspend fun getNpsById(
        id: Long
    ): Nps

    suspend fun sendResponse(
        id: Long,
        score: Int,
        comment: String?
    )
}