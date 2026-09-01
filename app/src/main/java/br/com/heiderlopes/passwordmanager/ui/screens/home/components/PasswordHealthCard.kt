package br.com.heiderlopes.passwordmanager.ui.screens.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Security
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.heiderlopes.passwordmanager.R
import br.com.heiderlopes.passwordmanager.ui.theme.PasswordManagerTheme

@Composable
fun PasswordHealthCard(
    totalPasswords: Int,
    reusedPasswords: Int,
    modifier: Modifier = Modifier,
) {

    HomeCard(
        modifier = modifier
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Top
        ) {

            Icon(
                imageVector = Icons.Default.Security,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )

            Spacer(
                modifier = Modifier.width(8.dp)
            )

            Column(
                modifier = Modifier.weight(1f)
            ) {

                Text(
                    text = stringResource(R.string.password_health_title),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = stringResource(R.string.password_health_summary),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

        }

        Spacer(
            modifier = Modifier.height(20.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            PasswordHealthItem(
                value = totalPasswords,
                title = stringResource(R.string.saved_passwords),
                description = stringResource(R.string.saved_passwords_good),
                status = PasswordHealthStatus.Good,
                modifier = Modifier.weight(1f)
            )

            VerticalDivider(
                modifier = Modifier.height(80.dp),
                color = MaterialTheme.colorScheme.outlineVariant.copy(
                    alpha = 0.35f
                )
            )

            PasswordHealthItem(
                value = reusedPasswords,
                title = stringResource(R.string.reused_passwords),
                description = stringResource(R.string.reused_passwords_bad),
                status = PasswordHealthStatus.Error,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PasswordHealthCardPreview() {
    PasswordManagerTheme {
        PasswordHealthCard(
            totalPasswords = 12,
            reusedPasswords = 3
        )
    }
}