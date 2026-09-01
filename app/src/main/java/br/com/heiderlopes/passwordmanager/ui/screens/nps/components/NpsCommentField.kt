package br.com.heiderlopes.passwordmanager.ui.screens.nps.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.com.heiderlopes.passwordmanager.ui.theme.PasswordManagerTheme

@Composable
fun NpsCommentField(
    comment: String,
    onCommentChange: (String) -> Unit,
    placeholder: String? = null,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = comment,
        onValueChange = onCommentChange,
        placeholder = { Text(placeholder ?: "") },
        modifier = modifier.fillMaxWidth()
    )
}

@Preview
@Composable
private fun NpsCommentFieldPreview() {

    PasswordManagerTheme() {
        NpsCommentField("", placeholder = "Conte-nos o motivo de sua nota", onCommentChange = {})
    }
}