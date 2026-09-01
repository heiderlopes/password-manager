package br.com.heiderlopes.passwordmanager.data.repository

import br.com.heiderlopes.passwordmanager.data.local.room.dao.PasswordDao
import br.com.heiderlopes.passwordmanager.data.local.room.mapper.toDomain
import br.com.heiderlopes.passwordmanager.data.local.room.mapper.toEntity
import br.com.heiderlopes.passwordmanager.domain.model.Password
import br.com.heiderlopes.passwordmanager.domain.repository.PasswordRepository

class PasswordRepositoryImpl(
    private val passwordDao: PasswordDao
) : PasswordRepository {

    override suspend fun save(password: Password): Long {
        val entity = password.toEntity()

        return if (entity.id == 0L) {
            passwordDao.insert(entity)
        } else {
            passwordDao.update(entity)
            entity.id
        }
    }

    override suspend fun findById(id: Long): Password? {
        return passwordDao.findById(id)?.toDomain()
    }

    override suspend fun getAll(): List<Password> {
        return passwordDao.getAll().map { entity ->
            entity.toDomain()
        }
    }

    override suspend fun delete(password: Password) {
        passwordDao.delete(password.toEntity())
    }

    override suspend fun deleteById(id: Long) {
        passwordDao.deleteById(id)
    }
}