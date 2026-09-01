package br.com.heiderlopes.passwordmanager.ui.screens.home.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import br.com.heiderlopes.passwordmanager.R
import br.com.heiderlopes.passwordmanager.ui.theme.PasswordManagerTheme

@Composable
fun SecurityTipSection(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    SecurityTipCard(
        title = stringResource(R.string.security_tip_title),
        description = stringResource(R.string.security_tip_description)
    )
}

@Preview
@Composable
private fun SecurityTipSectionPreview() {
    PasswordManagerTheme() {
        SecurityTipSection() {

        }
    }
}