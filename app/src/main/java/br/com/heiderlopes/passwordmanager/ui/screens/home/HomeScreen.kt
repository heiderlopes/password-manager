package br.com.heiderlopes.passwordmanager.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import br.com.heiderlopes.passwordmanager.R
import br.com.heiderlopes.passwordmanager.data.local.room.database.AppDatabase
import br.com.heiderlopes.passwordmanager.data.remote.ApiClient
import br.com.heiderlopes.passwordmanager.data.repository.NpsRepositoryImpl
import br.com.heiderlopes.passwordmanager.data.repository.PasswordRepositoryImpl
import br.com.heiderlopes.passwordmanager.ui.components.AppTopBar
import br.com.heiderlopes.passwordmanager.ui.screens.home.components.HomeContent
import br.com.heiderlopes.passwordmanager.ui.screens.password.list.ListPasswordsScreen

@Composable
fun HomeScreen(
    onCreatePassword: () -> Unit,
    onEditPassword: (Long) -> Unit,
    onNpsClick: (Long?) -> Unit
) {
    var selectedItem by rememberSaveable { mutableIntStateOf(0) }

    val items = listOf(
        BottomNavItem(title = "Home", icon = Icons.Default.Home),
        BottomNavItem(title = "Senhas", icon = Icons.Default.Lock),
        BottomNavItem(title = "Perfil", icon = Icons.Default.Person)
    )

    val context = LocalContext.current

    val passwordRepository = remember {
        PasswordRepositoryImpl(AppDatabase.getInstance(context).passwordDao())
    }

    val npsRepository = remember {
        NpsRepositoryImpl(
            api = ApiClient.npsApi
        )
    }

    val factory = remember {
        HomeViewModelFactory(passwordRepository, npsRepository)
    }

    val viewModel: HomeViewModel = viewModel(
        factory = factory
    )

    //val uiState by viewModel.uiState.collectAsState()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = { AppTopBar(stringResource(R.string.app_name)) },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onCreatePassword,
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary,
                shape = CircleShape,
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Adicionar senha"
                )
            }
        },
        bottomBar = {
            NavigationBar {
                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = selectedItem == index,
                        onClick = { selectedItem = index },
                        icon = {
                            Icon(
                                imageVector = item.icon,
                                contentDescription = item.title
                            )
                        },
                        label = {
                            Text(item.title)
                        }
                    )
                }
            }
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            when (selectedItem) {
                0 -> HomeContent(
                    uiState = uiState,
                    onNpsClick = { surveyId -> onNpsClick(surveyId)}
                )
                1 -> ListPasswordsScreen(
                    onNavigateBack = { },
                    onPasswordClick = onEditPassword,
                    showTopBar = false
                )
                2 -> Text("Tela de Perfil")
            }
        }
    }
}