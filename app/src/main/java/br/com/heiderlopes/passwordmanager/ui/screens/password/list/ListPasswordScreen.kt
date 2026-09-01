package br.com.heiderlopes.passwordmanager.ui.screens.password.list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import br.com.heiderlopes.passwordmanager.R
import br.com.heiderlopes.passwordmanager.data.local.room.database.AppDatabase
import br.com.heiderlopes.passwordmanager.data.repository.PasswordRepositoryImpl
import br.com.heiderlopes.passwordmanager.ui.components.AppTopBar
import br.com.heiderlopes.passwordmanager.ui.screens.password.list.components.PasswordCard

@Composable
fun ListPasswordsScreen(
    showTopBar: Boolean = true,
    onNavigateBack: () -> Unit,
    onPasswordClick: (Long) -> Unit
) {
    val context = LocalContext.current

    val repository = remember {
        PasswordRepositoryImpl(AppDatabase.getInstance(context).passwordDao())
    }

    val factory = remember {
        ListPasswordsViewModelFactory(repository)
    }

    val viewModel: ListPasswordsViewModel = viewModel(factory = factory)
    val uiState by viewModel.uiState.collectAsState()

    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(uiState.snackbarMessage) {
        uiState.snackbarMessage?.let { message ->
            snackbarHostState.showSnackbar(message)
            viewModel.onSnackbarShown()
        }
    }

    Scaffold(
        topBar = {
            if (showTopBar) {
                AppTopBar(
                    title = "Minhas senhas",
                    onBackClick = onNavigateBack
                )
            }
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
    ) { innerPadding ->
        when {
            uiState.isLoading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    //.padding(innerPadding),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            uiState.errorMessage != null -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = uiState.errorMessage ?: "Erro ao carregar senhas",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }

            uiState.isEmpty -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Nenhuma senha cadastrada ainda.",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }

            else -> {
                PullToRefreshBox(
                    isRefreshing = uiState.isRefreshing,
                    onRefresh = viewModel::loadPasswords,
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        contentPadding = PaddingValues(
                            horizontal = 16.dp,
                            vertical = if (showTopBar) 16.dp else 0.dp
                        )
                    ) {
                        itemsIndexed(
                            items = uiState.items,
                            key = { _, item -> item.id }
                        ) { index, item ->

                            PasswordCard(
                                item = item,
                                onToggleVisibility = viewModel::onTogglePasswordVisibility,
                                onDeleteClick = viewModel::onDeleteClick,
                                onItemClick = onPasswordClick
                            )

                            if (index < uiState.items.lastIndex) {
                                HorizontalDivider(
                                    modifier = Modifier.padding(
                                        start = 80.dp
                                    ),
                                    thickness = 1.dp,
                                    color = MaterialTheme.colorScheme.outlineVariant.copy(
                                        alpha = 0.5f
                                    )
                                )
                            }
                        }
                    }
                }

            }
        }

        if (uiState.pendingDeleteId != null) {
            AlertDialog(
                onDismissRequest = viewModel::onDismissDeleteDialog,
                title = { Text(stringResource(R.string.delete_password_title)) },
                text = { Text(stringResource(R.string.delete_password_message)) },
                confirmButton = {
                    TextButton(onClick = viewModel::onConfirmDelete) {
                        Text(stringResource(R.string.delete_password_action))
                    }
                },
                dismissButton = {
                    TextButton(onClick = viewModel::onDismissDeleteDialog) {
                        Text(stringResource(R.string.cancel))
                    }
                }
            )
        }
    }
}