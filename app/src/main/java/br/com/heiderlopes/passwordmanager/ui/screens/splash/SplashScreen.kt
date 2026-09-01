package br.com.heiderlopes.passwordmanager.ui.screens.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import br.com.heiderlopes.passwordmanager.R
import br.com.heiderlopes.passwordmanager.data.local.datastore.OnboardingPreferences
import br.com.heiderlopes.passwordmanager.data.repository.OnboardingRepositoryImpl
import br.com.heiderlopes.passwordmanager.navigation.Routes
import br.com.heiderlopes.passwordmanager.ui.components.LogoApp
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.milliseconds

@Composable
fun SplashScreen(
    onGoToOnboarding: () -> Unit,
    onGoToHome: () -> Unit
) {
    val context = LocalContext.current

    val repository = remember {
        OnboardingRepositoryImpl(
            OnboardingPreferences(context)
        )
    }

    val factory = remember {
        SplashViewModelFactory(repository)
    }

    val viewModel: SplashViewModel = viewModel(
        factory = factory
    )

    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(uiState.destination, uiState.isLoading) {
        if (!uiState.isLoading) {
            when (uiState.destination) {
                Routes.Onboarding.route -> onGoToOnboarding()
                Routes.Home.route -> onGoToHome()
                else -> Unit
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(R.drawable.logo_app),
            contentDescription = stringResource(R.string.app_name),
            modifier = Modifier.size(150.dp)
        )
    }
}