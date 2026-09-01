package br.com.heiderlopes.passwordmanager.ui.screens.home.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.com.heiderlopes.passwordmanager.ui.theme.PasswordManagerTheme

@Composable
fun PasswordHealthSection(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    PasswordHealthCard(
        10, 0
    )
}

@Preview
@Composable
private fun PasswordHealthSectionPreview() {
    PasswordManagerTheme {
        PasswordHealthSection() {

        }
    }
}