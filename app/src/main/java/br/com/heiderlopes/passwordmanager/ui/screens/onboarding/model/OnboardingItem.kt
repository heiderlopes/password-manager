package br.com.heiderlopes.passwordmanager.ui.screens.onboarding.model

import androidx.annotation.RawRes

data class OnboardingItem(
    @param:RawRes
    val resourceId: Int,
    val title: String,
    val subtitle: String
)