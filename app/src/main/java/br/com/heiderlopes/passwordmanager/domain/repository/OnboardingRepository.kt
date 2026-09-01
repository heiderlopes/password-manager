package br.com.heiderlopes.passwordmanager.domain.repository

import kotlinx.coroutines.flow.Flow

interface OnboardingRepository {
    val skipOnboarding: Flow<Boolean>
    suspend fun setSkipOnboarding(skip: Boolean)
}