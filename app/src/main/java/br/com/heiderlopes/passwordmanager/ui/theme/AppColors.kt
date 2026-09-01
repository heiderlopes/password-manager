package br.com.heiderlopes.passwordmanager.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf

@Immutable
data class AppColors(
    val nps: NpsColors
)

val LightAppColors = AppColors(
    nps = LightNpsColors
)

val DarkAppColors = AppColors(
    nps = DarkNpsColors
)

val LocalAppColors = staticCompositionLocalOf {
    LightAppColors
}

object AppTheme {

    val colors: AppColors
        @Composable
        @ReadOnlyComposable
        get() = LocalAppColors.current
}