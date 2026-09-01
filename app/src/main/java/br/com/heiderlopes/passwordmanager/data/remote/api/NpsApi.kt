package br.com.heiderlopes.passwordmanager.data.remote.api

import br.com.heiderlopes.passwordmanager.data.remote.dto.NpsAnswerRequest
import br.com.heiderlopes.passwordmanager.data.remote.dto.NpsAnswerResponse
import br.com.heiderlopes.passwordmanager.data.remote.dto.NpsResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface NpsApi {

    @GET("api/nps/current")
    suspend fun getCurrentNps(): NpsResponse

    @GET("api/nps/{id}")
    suspend fun getNpsById(
        @Path("id") id: Long
    ): NpsResponse

    @POST("api/nps/{id}/responses")
    suspend fun sendResponse(
        @Path("id") id: Long,
        @Body request: NpsAnswerRequest
    ): NpsAnswerResponse
}