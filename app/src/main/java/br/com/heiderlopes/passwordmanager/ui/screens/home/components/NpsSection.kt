package br.com.heiderlopes.passwordmanager.ui.screens.home.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.com.heiderlopes.passwordmanager.domain.model.Nps
import br.com.heiderlopes.passwordmanager.ui.screens.home.HomeNpsUiState
import br.com.heiderlopes.passwordmanager.ui.theme.PasswordManagerTheme

@Composable
fun NpsSection(
    modifier: Modifier = Modifier,
    homeNpsUiState: HomeNpsUiState,
    onClick: (Long?) -> Unit
) {
    homeNpsUiState.nps?.let { nps ->
        NpsCard(
            onClick = { onClick(homeNpsUiState.nps.id) }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun NpsSectionPreview() {
    PasswordManagerTheme {
        NpsSection(
            homeNpsUiState = HomeNpsUiState(
                nps = Nps(
                    id = 1L,
                    question = "Qual sua avaliação sobre o app?"
                ),
                isLoading = false,
                errorMessage = null
            ),
            onClick = {}
        )
    }
}
