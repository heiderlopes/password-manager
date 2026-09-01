package br.com.heiderlopes.passwordmanager.ui.screens.nps

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Recommend
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import br.com.heiderlopes.passwordmanager.R
import br.com.heiderlopes.passwordmanager.data.remote.ApiClient
import br.com.heiderlopes.passwordmanager.data.repository.NpsRepositoryImpl
import br.com.heiderlopes.passwordmanager.ui.components.AppTopBar
import br.com.heiderlopes.passwordmanager.ui.screens.nps.components.NpsCommentField
import br.com.heiderlopes.passwordmanager.ui.screens.nps.components.NpsForm
import br.com.heiderlopes.passwordmanager.ui.screens.nps.components.NpsScale
import br.com.heiderlopes.passwordmanager.ui.theme.PasswordManagerTheme

@Composable
fun NpsScreen(
    modifier: Modifier = Modifier,
    surveyId: Long? = null,
    onBack: () -> Unit = {},
    onDone: () -> Unit = {}
) {

    val npsRepository = remember {
        NpsRepositoryImpl(
            api = ApiClient.npsApi
        )
    }

    val factory = remember {
        NpsViewModelFactory(
            npsRepository
        )
    }

    val viewModel: NpsViewModel = viewModel(
        factory = factory
    )

    val uiState by viewModel.uiState
        .collectAsStateWithLifecycle()

    LaunchedEffect(surveyId) {
        surveyId?.let {
            viewModel.loadNps(it)
        }
    }

    Scaffold(
        topBar = {
            AppTopBar(
                stringResource(R.string.app_name),
                onBackClick = onBack
            )
        }
    ) { innerPadding ->

        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {

            when {

                uiState.isSuccess -> {
                    Column(
                        modifier = modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                            .padding(16.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Icon(
                            imageVector = Icons.Outlined.Recommend,
                            contentDescription = stringResource(R.string.nps_title),
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(120.dp)
                        )
                        Spacer(modifier = Modifier
                            .height(32.dp)
                            .fillMaxWidth())
                        Text(
                            stringResource(R.string.nps_success_thanks),
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier
                            .height(32.dp)
                            .fillMaxWidth())
                        Button(onClick = onDone, modifier = Modifier.fillMaxWidth()) {
                            Text(stringResource(R.string.back))
                        }
                    }
                }

                uiState.isLoading -> {
                    CircularProgressIndicator(
                        modifier = Modifier.align(
                            Alignment.Center
                        )
                    )
                }

                uiState.errorMessage != null -> {
                    Text(
                        text = uiState.errorMessage
                            ?: stringResource(R.string.nps_error_loading),
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(16.dp),
                        textAlign = TextAlign.Center
                    )
                }

                else -> {
                    NpsForm(
                        uiState = uiState,
                        onScoreSelected = viewModel::onScoreSelected,
                        onCommentChange = viewModel::onCommentChange,
                        onSubmit = {
                            surveyId?.let {
                                viewModel.submit(it, uiState.comment)
                            }
                        }
                    )
                }
            }
        }
    }
}