package br.com.heiderlopes.passwordmanager.domain.repository

import br.com.heiderlopes.passwordmanager.domain.model.Password

interface PasswordRepository {
    suspend fun save(password: Password): Long

    suspend fun findById(id: Long): Password?

    suspend fun getAll(): List<Password>

    suspend fun delete(password: Password)

    suspend fun deleteById(id: Long)
}