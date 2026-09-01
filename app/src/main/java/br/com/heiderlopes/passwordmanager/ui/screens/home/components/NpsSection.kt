package br.com.heiderlopes.passwordmanager.ui.screens.home.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.com.heiderlopes.passwordmanager.ui.theme.PasswordManagerTheme

@Composable
fun NpsSection(
    modifier: Modifier = Modifier,
    onClick: (Long?) -> Unit
) {
    NpsCard(
        onClick = { onClick(1) }
    )
}

@Preview
@Composable
private fun NpsSectionPreview() {
    PasswordManagerTheme {
        NpsSection { }
    }
}