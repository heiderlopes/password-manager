package br.com.heiderlopes.passwordmanager.data.remote.mapper

import br.com.heiderlopes.passwordmanager.data.remote.dto.NpsResponse
import br.com.heiderlopes.passwordmanager.domain.model.Nps

fun NpsResponse.toDomain(): Nps {
    return Nps(
        id = id,
        question = question
    )
}