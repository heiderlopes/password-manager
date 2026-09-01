package br.com.heiderlopes.passwordmanager.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

@Immutable
data class NpsColors(
    val detractor: Color,
    val passive: Color,
    val promoter: Color,
    val content: Color
)

val LightNpsColors = NpsColors(
    detractor = NpsDetractorLight,
    passive = NpsPassiveLight,
    promoter = NpsPromoterLight,
    content = NpsContentLight
)

val DarkNpsColors = NpsColors(
    detractor = NpsDetractorDark,
    passive = NpsPassiveDark,
    promoter = NpsPromoterDark,
    content = NpsContentDark
)