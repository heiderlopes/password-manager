package br.com.heiderlopes.passwordmanager.ui.screens.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Security
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.heiderlopes.passwordmanager.ui.theme.PasswordManagerTheme

@Composable
fun SecurityTipCard(
    title: String,
    description: String,
    modifier: Modifier = Modifier
) {

    HomeCard(
        modifier = modifier,
        contentPadding = PaddingValues(14.dp)
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {

            Surface(
                shape = RoundedCornerShape(12.dp),
                color = MaterialTheme.colorScheme.primaryContainer
            ) {

                Icon(
                    imageVector = Icons.Default.Security,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onPrimaryContainer,
                    modifier = Modifier
                        .padding(10.dp)
                        .size(28.dp)
                )
            }

            Spacer(
                modifier = Modifier.width(12.dp)
            )

            Column(
                modifier = Modifier.weight(1f)
            ) {

                Text(
                    text = title,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Spacer(
                    modifier = Modifier.height(2.dp)
                )

                Text(
                    text = description,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Icon(
                imageVector = Icons.Default.Lock,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.size(32.dp)
            )
        }
    }
}

@Preview
@Composable
private fun SecurityTipCardPreview() {

    PasswordManagerTheme {
        SecurityTipCard(
            "Dica de segurança",
            "Use senhas únicas para cada conta e ative a verificação em duas etapas sempre que possível."
        )
    }

}