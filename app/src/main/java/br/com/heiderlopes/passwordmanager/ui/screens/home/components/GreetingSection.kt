package br.com.heiderlopes.passwordmanager.ui.screens.home.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import br.com.heiderlopes.passwordmanager.ui.theme.PasswordManagerTheme

@Composable
fun GreetingSection(
    title: String,
    message: String
) {
    GreetingCard(
        title = title,
        message = message
    )
}

@Preview
@Composable
private fun GreetingSectionPreview() {

    PasswordManagerTheme {
        GreetingSection(
            title = "Olá, Heider!",
            message = "Gerencie suas senhas"
        )
    }
}