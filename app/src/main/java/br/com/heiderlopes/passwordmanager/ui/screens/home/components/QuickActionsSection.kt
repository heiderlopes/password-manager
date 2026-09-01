package br.com.heiderlopes.passwordmanager.ui.screens.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.FileDownload
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.heiderlopes.passwordmanager.R
import br.com.heiderlopes.passwordmanager.ui.components.SectionTitle
import br.com.heiderlopes.passwordmanager.ui.theme.PasswordManagerTheme

@Composable
fun QuickActionsSection(
    onNewPasswordClick: () -> Unit,
    onGeneratePasswordClick: () -> Unit,
    onPasswordsClick: () -> Unit,
    onImportClick: () -> Unit
) {

    Column {

        SectionTitle(
            title = stringResource(R.string.quick_actions),
            actionText = stringResource(R.string.view_more)
        )

        Spacer(
            modifier = Modifier.height(4.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            QuickActionItem(
                title = stringResource(R.string.new_password),
                icon = Icons.Default.Add,
                onClick = onNewPasswordClick,
                modifier = Modifier.weight(1f)
            )

            QuickActionItem(
                title = stringResource(R.string.generate_password_action),
                icon = Icons.Default.Bolt,
                onClick = onGeneratePasswordClick,
                modifier = Modifier.weight(1f)
            )

            QuickActionItem(
                title = stringResource(R.string.view_passwords),
                icon = Icons.Default.Lock,
                onClick = onPasswordsClick,
                modifier = Modifier.weight(1f)
            )

            QuickActionItem(
                title = stringResource(R.string.import_label),
                icon = Icons.Default.FileDownload,
                onClick = onImportClick,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun QuickActionsSectionPreview() {
    PasswordManagerTheme {
        QuickActionsSection(
            onNewPasswordClick = {},
            onGeneratePasswordClick = {},
            onPasswordsClick = {},
            onImportClick = {}
        )
    }
}