package br.com.heiderlopes.passwordmanager.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.heiderlopes.passwordmanager.ui.theme.PasswordManagerTheme

@Composable
fun SectionTitle(
    modifier: Modifier = Modifier,
    title: String,
    actionText: String? = null,
    onActionClick: () -> Unit = {}
) {

    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            text = title,
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )

        actionText?.let {

            TextButton(
                onClick = onActionClick,
                contentPadding = PaddingValues(
                    horizontal = 4.dp
                )
            ) {

                Text(
                    text = it,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SectionTitlePreview() {
    PasswordManagerTheme {
        SectionTitle(
            title = "Senhas recentes",
            actionText = "Ver todas",
            onActionClick = {}
        )
    }
}