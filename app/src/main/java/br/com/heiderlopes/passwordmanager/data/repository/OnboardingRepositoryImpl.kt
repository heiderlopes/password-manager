package br.com.heiderlopes.passwordmanager.data.repository

import br.com.heiderlopes.passwordmanager.data.local.datastore.OnboardingPreferences
import br.com.heiderlopes.passwordmanager.domain.repository.OnboardingRepository
import kotlinx.coroutines.flow.Flow

class OnboardingRepositoryImpl(
    private val preferences: OnboardingPreferences
) : OnboardingRepository {

    override val skipOnboarding: Flow<Boolean>
        get() = preferences.skipOnboarding

    override suspend fun setSkipOnboarding(
        skip: Boolean
    ) {
        preferences.setSkipOnboarding(skip)
    }
}