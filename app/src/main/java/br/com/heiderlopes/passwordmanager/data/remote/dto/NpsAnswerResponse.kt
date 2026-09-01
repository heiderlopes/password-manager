package br.com.heiderlopes.passwordmanager.data.remote.dto

data class NpsAnswerResponse(
    val id: Long,
    val surveyId: Long,
    val score: Int,
    val createdAt: String
)