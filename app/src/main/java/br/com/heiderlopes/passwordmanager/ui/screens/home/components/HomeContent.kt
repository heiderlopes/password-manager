package br.com.heiderlopes.passwordmanager.ui.screens.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.heiderlopes.passwordmanager.ui.theme.PasswordManagerTheme

@Composable
fun HomeContent(
    modifier: Modifier = Modifier,
    onNpsClick: (Long?) -> Unit
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentPadding = PaddingValues(
            horizontal = 16.dp,
            vertical = 12.dp
        ),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        item {
            GreetingSection(
                title = "Olá,",
                message = "Mantenha suas conta protegidas"
            )
        }

        item {
            PasswordHealthSection { }
        }

        item {
            QuickActionsSection(
                onNewPasswordClick = {},
                onGeneratePasswordClick = {},
                onPasswordsClick = {},
                onImportClick = {}
            )
        }

        item {
            NpsSection {surveyId -> onNpsClick(surveyId) }
        }

        item {
            RecentPasswordsSection(
                passwords = listOf(
                    RecentPasswordUi(
                        id = 1,
                        service = "Google",
                        username = "heider@gmail.com",
                        showPassword = true
                    ),
                    RecentPasswordUi(
                        id = 2,
                        service = "Netflix",
                        username = "heider@gmail.com",
                        showPassword = false
                    ),
                    RecentPasswordUi(
                        id = 3,
                        service = "Amazon",
                        username = "heider@gmail.com",
                        showPassword = false,
                    )
                ),
                onSeeAllClick = { }
            )
        }

        item {
            SecurityTipSection { }
        }

// Adicione um espaço ao final da lista.
        item {
            Spacer(
                modifier = Modifier.height(80.dp)
            )
        }
    }
}

@Preview
@Composable
private fun HomeContentPreview() {
    PasswordManagerTheme {
        HomeContent() {}
    }
}