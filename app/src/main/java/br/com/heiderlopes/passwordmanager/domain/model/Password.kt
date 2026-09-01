package br.com.heiderlopes.passwordmanager.domain.model

data class Password(
    val id: Long = 0L,
    val username: String,
    val password: String,
    val serviceName: String
)