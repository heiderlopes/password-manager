package br.com.heiderlopes.passwordmanager.ui.screens.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.heiderlopes.passwordmanager.ui.theme.PasswordManagerTheme

enum class PasswordHealthStatus {
    Good,
    Warning,
    Error
}

@Composable
fun PasswordHealthItem(
    value: Int,
    title: String,
    description: String,
    status: PasswordHealthStatus,
    modifier: Modifier = Modifier
) {

    val statusColor = when (status) {

        PasswordHealthStatus.Good ->
            MaterialTheme.colorScheme.tertiary

        PasswordHealthStatus.Warning ->
            MaterialTheme.colorScheme.secondary

        PasswordHealthStatus.Error ->
            MaterialTheme.colorScheme.error
    }

    Column(
        modifier = modifier.padding(horizontal = 8.dp)
    ) {

        Text(
            text = value.toString(),
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            color = statusColor
        )

        Spacer(
            modifier = Modifier.height(2.dp)
        )

        Text(
            text = title,
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurface
        )

        Spacer(
            modifier = Modifier.height(2.dp)
        )

        Text(
            text = description,
            style = MaterialTheme.typography.labelSmall,
            color = statusColor
        )

        Spacer(
            modifier = Modifier.height(10.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PasswordHealthItemPreview() {
    PasswordManagerTheme {
        PasswordHealthItem(
            value = 12,
            title = "Senhas reutilizadas",
            description = "Evite usar a mesma senha em vários serviços.",
            status = PasswordHealthStatus.Good
        )
    }
}