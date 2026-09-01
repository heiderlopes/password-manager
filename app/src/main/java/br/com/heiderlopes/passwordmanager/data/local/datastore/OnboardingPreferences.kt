package br.com.heiderlopes.passwordmanager.data.local.datastore

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(
    name = "password_manager_preferences"
)

class OnboardingPreferences(
    private val context: Context
) {

    companion object {
        private val SKIP_ONBOARDING =
            booleanPreferencesKey("skip_onboarding")
    }

    val skipOnboarding: Flow<Boolean>
        get() = context.dataStore.data.map { preferences ->
            preferences[SKIP_ONBOARDING] ?: false
        }

    suspend fun setSkipOnboarding(
        skip: Boolean
    ) {
        context.dataStore.edit { preferences ->
            preferences[SKIP_ONBOARDING] = skip
        }
    }
}