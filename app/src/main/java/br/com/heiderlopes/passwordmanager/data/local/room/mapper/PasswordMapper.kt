package br.com.heiderlopes.passwordmanager.data.local.room.mapper

import br.com.heiderlopes.passwordmanager.data.local.room.entity.PasswordEntity
import br.com.heiderlopes.passwordmanager.domain.model.Password

fun PasswordEntity.toDomain(): Password {
    return Password(
        id = id,
        username = username,
        password = password,
        serviceName = serviceName
    )
}

fun Password.toEntity(): PasswordEntity {
    return PasswordEntity(
        id = id,
        username = username,
        password = password,
        serviceName = serviceName
    )
}