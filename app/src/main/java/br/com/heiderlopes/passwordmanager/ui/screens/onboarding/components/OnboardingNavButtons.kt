package br.com.heiderlopes.passwordmanager.ui.screens.onboarding.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.heiderlopes.passwordmanager.ui.components.HorizontalAnimatedContent
import br.com.heiderlopes.passwordmanager.ui.theme.PasswordManagerTheme

@Composable
fun OnboardingNavButtons(
    currentPage: Int,
    pageCount: Int,
    onBack: () -> Unit,
    onNext: () -> Unit,
    onFinish: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        if (currentPage > 0) {
            TextButton(
                onClick = onBack
            ) { Text("Voltar") }
        } else {
            Spacer(modifier = Modifier.width(1.dp))
        }

        if (currentPage < pageCount - 1) {
            TextButton(
                onClick = onNext
            ) {
                Text("Avançar")
            }
        } else {
            TextButton(
                onClick = onFinish
            ) {
                Text("Concluir")
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun OnboardingNavButtonsPreview() {
    PasswordManagerTheme {
        OnboardingNavButtons(
            currentPage = 1,
            pageCount = 3,
            onBack = {},
            onNext = {},
            onFinish = {}
        )
    }
}