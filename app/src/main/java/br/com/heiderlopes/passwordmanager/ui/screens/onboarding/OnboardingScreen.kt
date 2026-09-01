package br.com.heiderlopes.passwordmanager.ui.screens.onboarding

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import br.com.heiderlopes.passwordmanager.R
import br.com.heiderlopes.passwordmanager.data.local.datastore.OnboardingPreferences
import br.com.heiderlopes.passwordmanager.data.repository.OnboardingRepositoryImpl
import br.com.heiderlopes.passwordmanager.ui.components.CheckboxOption
import br.com.heiderlopes.passwordmanager.ui.components.HorizontalAnimatedContent
import br.com.heiderlopes.passwordmanager.ui.components.PageIndicator
import br.com.heiderlopes.passwordmanager.ui.screens.onboarding.components.OnboardingNavButtons
import br.com.heiderlopes.passwordmanager.ui.screens.onboarding.components.OnboardingPage
import br.com.heiderlopes.passwordmanager.ui.screens.onboarding.model.OnboardingItem
import br.com.heiderlopes.passwordmanager.ui.theme.PasswordManagerTheme
import kotlinx.coroutines.launch

@Composable
fun OnboardingScreen(
    onFinish: () -> Unit
) {

    val context = LocalContext.current

    val repository = remember {
        OnboardingRepositoryImpl(
            OnboardingPreferences(context)
        )
    }

    val factory = remember {
        OnboardingViewModelFactory(repository)
    }

    val viewModel: OnboardingViewModel = viewModel(
        factory = factory
    )

    val uiState by viewModel.uiState.collectAsState()

    val scope = rememberCoroutineScope()

    val onboardingItems = listOf(

        OnboardingItem(
            resourceId = R.raw.onboarding_1,
            title = stringResource( R.string.onboarding_title_1),
            subtitle = stringResource(R.string.onboarding_subtitle_1)
        ),

        OnboardingItem(
            resourceId = R.raw.onboarding_2,
            title = stringResource(R.string.onboarding_title_2),
            subtitle = stringResource(R.string.onboarding_subtitle_2)
        ),

        OnboardingItem(
            resourceId = R.raw.onboarding_3,
            title = stringResource(R.string.onboarding_title_3),
            subtitle = stringResource(R.string.onboarding_subtitle_3)
        )
    )

    Scaffold { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {

            HorizontalAnimatedContent(
                currentPage = uiState.currentPage,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) { page ->

                OnboardingPage(
                    item = onboardingItems[page]
                )
            }

            PageIndicator(
                pageCount = onboardingItems.size,
                currentPage = uiState.currentPage,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                contentAlignment = Alignment.Center
            ) {
                if (uiState.currentPage == onboardingItems.lastIndex) {
                    CheckboxOption(
                        text = "Não mostrar novamente",
                        checked = uiState.skipOnboarding,
                        onCheckedChange = viewModel::onSkipOnboardingChange
                    )
                }
            }

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            OnboardingNavButtons(
                currentPage = uiState.currentPage,
                pageCount = onboardingItems.size,
                onBack = viewModel::previousPage,
                onNext = viewModel::nextPage,
                onFinish = {
                    scope.launch {
                        viewModel
                            .finishOnboarding()
                            .onSuccess {
                                onFinish()
                            }
                            .onFailure {

                            }
                    }
                }
            )
        }
    }
}

@Preview
@Composable
private fun OnboardingScreenPreview() {
    PasswordManagerTheme() {
        OnboardingScreen {  }
    }

}