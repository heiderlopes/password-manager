package br.com.heiderlopes.passwordmanager.ui.screens.home.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.heiderlopes.passwordmanager.ui.screens.home.PasswordStatsUiState
import br.com.heiderlopes.passwordmanager.ui.theme.PasswordManagerTheme

@Composable
fun PasswordHealthSection(
    uiState: PasswordStatsUiState
) {
    when {
        uiState.isLoading -> {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        uiState.errorMessage != null -> {
            Text(
                text = "Erro ao carregar estatísticas",
                color = MaterialTheme.colorScheme.error
            )
        }

        else -> {
            PasswordHealthCard(
                totalPasswords = uiState.totalPasswords,
                reusedPasswords = uiState.reusedPasswords
            )
        }
    }
}
@Preview(showBackground = true)
@Composable
private fun PasswordHealthSectionPreview() {
    PasswordManagerTheme {
        PasswordHealthSection(
            uiState = PasswordStatsUiState(
                totalPasswords = 12,
                reusedPasswords = 3,
                isLoading = false,
                errorMessage = null
            )
        )
    }
}