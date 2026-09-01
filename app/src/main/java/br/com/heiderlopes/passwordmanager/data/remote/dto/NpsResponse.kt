package br.com.heiderlopes.passwordmanager.data.remote.dto

data class NpsResponse(
    val id: Long,
    val question: String,
    val active: Boolean,
    val imageUrlDark: String,
    val imageUrlLight: String,
    val startsAt: String?,
    val endsAt: String?
)