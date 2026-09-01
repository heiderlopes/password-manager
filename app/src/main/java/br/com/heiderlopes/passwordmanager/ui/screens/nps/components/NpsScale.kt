package br.com.heiderlopes.passwordmanager.ui.screens.nps.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.SentimentVeryDissatisfied
import androidx.compose.material.icons.outlined.SentimentVerySatisfied
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.heiderlopes.passwordmanager.R
import br.com.heiderlopes.passwordmanager.ui.theme.AppTheme
import br.com.heiderlopes.passwordmanager.ui.theme.PasswordManagerTheme
import kotlinx.coroutines.launch

@Composable
fun NpsScale(
    selected: Int?,
    onSelect: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    fun selectScore(score: Int) {
        val newScore = score.coerceIn(0, 10)

        onSelect(newScore)

        coroutineScope.launch {
            listState.animateScrollToItem(newScore)
        }
    }

    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            // Diminuir nota
            IconButton(
                onClick = {
                    selectScore((selected ?: 1) - 1)
                },
                enabled = selected == null || selected > 0
            ) {
                Icon(
                    imageVector = Icons.Outlined.SentimentVeryDissatisfied,
                    contentDescription = stringResource(R.string.nps_decrease_score),
                    tint = if (selected == 0) {
                        MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f)
                    } else {
                        AppTheme.colors.nps.detractor
                    },
                    modifier = Modifier.size(28.dp)
                )
            }

            // Escala
            LazyRow(
                state = listState,
                modifier = Modifier
                    .weight(1f),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(horizontal = 4.dp)
            ) {
                items(
                    items = (0..10).toList(),
                    key = { it }
                ) { value ->

                    NpsScore(
                        score = value,
                        selected = selected == value,
                        modifier = Modifier
                            .size(48.dp)
                            .clickable {
                                selectScore(value)
                            }
                    )
                }
            }

            // Aumentar nota
            IconButton(
                onClick = {
                    selectScore((selected ?: -1) + 1)
                },
                enabled = selected == null || selected < 10
            ) {
                Icon(
                    imageVector = Icons.Outlined.SentimentVerySatisfied,
                    contentDescription = stringResource(R.string.nps_increase_score),
                    tint = if (selected == 10) {
                        MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f)
                    } else {
                        AppTheme.colors.nps.promoter
                    },
                    modifier = Modifier.size(28.dp)
                )
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(R.string.nps_not_likely),
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Text(
                text = stringResource(R.string.nps_very_likely),
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun NpsScalePreview() {
    PasswordManagerTheme {
        NpsScale(
            selected = 8,
            onSelect = {}
        )
    }
}