package br.com.heiderlopes.passwordmanager.ui.screens.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.heiderlopes.passwordmanager.R
import br.com.heiderlopes.passwordmanager.ui.theme.PasswordManagerTheme

@Composable
fun GreetingCard(
    title: String,
    message: String,
    modifier: Modifier = Modifier
) {
    HomeCard(
        modifier = modifier,
        contentPadding = PaddingValues(
            horizontal = 16.dp,
            vertical = 14.dp
        )
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column(
                modifier = Modifier.weight(1f)
            ) {

                Text(
                    text = title,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Spacer(
                    modifier = Modifier.height(4.dp)
                )

                Text(
                    text = message,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Image(
                painter = painterResource(
                    id = R.drawable.protection
                ),
                contentDescription = null,
                modifier = Modifier.size(72.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun GreetingCardPreview() {
    PasswordManagerTheme {
        GreetingCard(
            title = "Olá, Heider! 👋",
            message = "Suas senhas estão protegidas e organizadas."
        )
    }
}