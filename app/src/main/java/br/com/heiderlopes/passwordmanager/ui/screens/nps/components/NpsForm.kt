package br.com.heiderlopes.passwordmanager.ui.screens.nps.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Recommend
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import br.com.heiderlopes.passwordmanager.R
import br.com.heiderlopes.passwordmanager.ui.screens.nps.NpsUiState

@Composable
fun NpsForm(
    uiState: NpsUiState,
    onScoreSelected: (Int) -> Unit,
    onCommentChange: (String) -> Unit,
    onSubmit: () -> Unit,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {

        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {

            Icon(
                imageVector = Icons.Outlined.Recommend,
                contentDescription = stringResource(R.string.nps_title),
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(120.dp)
            )
        }

        Spacer(
            modifier = Modifier.height(32.dp)
        )

        Text(
            text = uiState.question,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(
            modifier = Modifier.height(32.dp)
        )

        NpsScale(
            selected = uiState.selectedScore,
            onSelect = onScoreSelected
        )

        Spacer(
            modifier = Modifier.height(32.dp)
        )

        when (val score = uiState.selectedScore) {

            null -> {

                Text(
                    text = stringResource(R.string.nps_select_score),
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }

            in 0..6 -> {

                Text(
                    text = stringResource(R.string.nps_feedback_low)
                )

                Spacer(
                    modifier = Modifier.height(16.dp)
                )

                NpsCommentField(
                    comment = uiState.comment?: "",
                    onCommentChange = onCommentChange
                )
            }

            in 7..8 -> {

                Text(
                    text = stringResource(R.string.nps_feedback_mid)
                )

                Spacer(
                    modifier = Modifier.height(16.dp)
                )

                NpsCommentField(
                    comment = uiState.comment?:"",
                    onCommentChange = onCommentChange
                )
            }

            in 9..10 -> {

                Text(
                    text = stringResource(R.string.nps_feedback_high)
                )

                Spacer(
                    modifier = Modifier.height(16.dp)
                )

                NpsCommentField(
                    comment = uiState.comment?: "",
                    onCommentChange = onCommentChange
                )
            }
        }

        Spacer(
            modifier = Modifier.height(32.dp)
        )

        Button(
            modifier = Modifier.fillMaxWidth(),
            enabled =
                uiState.selectedScore != null &&
                        !uiState.isSending,
            onClick = onSubmit
        ) {

            if (uiState.isSending) {

                CircularProgressIndicator(
                    modifier = Modifier.size(20.dp),
                    strokeWidth = 2.dp
                )

                Spacer(
                    modifier = Modifier.width(8.dp)
                )

                Text(
                    text = stringResource(R.string.nps_sending)
                )

            } else {

                Text(
                    text = stringResource(R.string.nps_submit)
                )
            }
        }
    }
}