package br.com.heiderlopes.passwordmanager.data.repository

import br.com.heiderlopes.passwordmanager.data.remote.api.NpsApi
import br.com.heiderlopes.passwordmanager.data.remote.dto.NpsAnswerRequest
import br.com.heiderlopes.passwordmanager.data.remote.mapper.toDomain
import br.com.heiderlopes.passwordmanager.domain.model.Nps
import br.com.heiderlopes.passwordmanager.domain.repository.NpsRepository
import retrofit2.HttpException

class NpsRepositoryImpl(
    private val api: NpsApi
) : NpsRepository {

    override suspend fun getCurrentNps(): Nps? {
        return try {

            api.getCurrentNps()
                .toDomain()

        } catch (exception: HttpException) {

            if (exception.code() == 404) {
                null
            } else {
                throw exception
            }
        }
    }

    override suspend fun getNpsById(
        id: Long
    ): Nps {

        return api
            .getNpsById(id)
            .toDomain()
    }

    override suspend fun sendResponse(
        id: Long,
        score: Int,
        comment: String?
    ) {

        api.sendResponse(
            id = id,
            request = NpsAnswerRequest(
                score = score,
                comment = comment
            )
        )
    }
}