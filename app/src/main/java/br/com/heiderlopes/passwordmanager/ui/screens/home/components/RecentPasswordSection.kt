package br.com.heiderlopes.passwordmanager.ui.screens.home.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.heiderlopes.passwordmanager.R
import br.com.heiderlopes.passwordmanager.ui.components.SectionTitle
import br.com.heiderlopes.passwordmanager.ui.theme.PasswordManagerTheme

@Composable
fun RecentPasswordsSection(
    passwords: List<RecentPasswordUi>,
    modifier: Modifier = Modifier,
    onPasswordClick: (Long) -> Unit = {},
    onShowPasswordClick: (Long) -> Unit = {},
    onSeeAllClick: () -> Unit = {}
) {

    Column(
        modifier = modifier
    ) {
        SectionTitle(
            title = stringResource(R.string.recent_passwords),
            actionText = stringResource(R.string.see_all),
            onActionClick = onSeeAllClick
        )

        Spacer(
            modifier = Modifier.height(4.dp)
        )

        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor =
                    MaterialTheme.colorScheme.surfaceContainer
            ),
            border = BorderStroke(
                1.dp,
                MaterialTheme.colorScheme.outlineVariant.copy(
                    alpha = 0.35f
                )
            )
        ) {

            passwords.forEachIndexed { index, password ->

                RecentPasswordItem(
                    item = password,
                    onClick = {
                        onPasswordClick(password.id)
                    },
                    onShowPasswordClick = {
                        onShowPasswordClick(password.id)
                    }
                )

                if (index != passwords.lastIndex) {

                    HorizontalDivider(
                        modifier = Modifier.padding(
                            start = 64.dp
                        ),
                        color =
                            MaterialTheme.colorScheme.outlineVariant.copy(
                                alpha = 0.25f
                            )
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun RecentPasswordsSectionPreview() {
    PasswordManagerTheme {
        RecentPasswordsSection(
            passwords = listOf(
                RecentPasswordUi(
                    id = 1L,
                    service = "Google",
                    username = "heider@email.com",
                    showPassword = false
                ),
                RecentPasswordUi(
                    id = 2L,
                    service = "GitHub",
                    username = "heiderlopes",
                    showPassword = true
                )
            ),
            onPasswordClick = {},
            onShowPasswordClick = {},
            onSeeAllClick = {}
        )
    }
}