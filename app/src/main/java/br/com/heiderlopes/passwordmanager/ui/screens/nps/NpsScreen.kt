package br.com.heiderlopes.passwordmanager.ui.screens.nps

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Recommend
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.heiderlopes.passwordmanager.R
import br.com.heiderlopes.passwordmanager.ui.components.AppTopBar
import br.com.heiderlopes.passwordmanager.ui.screens.nps.components.NpsCommentField
import br.com.heiderlopes.passwordmanager.ui.screens.nps.components.NpsScale
import br.com.heiderlopes.passwordmanager.ui.theme.PasswordManagerTheme

@Composable
fun NpsScreen(
    modifier: Modifier = Modifier,
    surveyId: Long? = null,
    onBack: () -> Unit = {},
    onDone: () -> Unit = {}
) {
    Scaffold(
        topBar = { AppTopBar(stringResource(R.string.app_name), onBackClick = onBack) },
    ) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
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

            Spacer(modifier = Modifier.height(32.dp))


            val selected = remember { mutableIntStateOf(5) }
            val comment = remember { mutableStateOf("") }


            Text(
                text = "Qual sua nota?",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(modifier = Modifier.height(32.dp))

            NpsScale(
                selected = selected.value,
                onSelect = { selected.value = it }
            )

            Spacer(modifier = Modifier.height(32.dp))

            when (val score = selected.value) {
                null -> {
                    Text(
                        text = stringResource(R.string.nps_select_score),
                        modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center
                    )
                }

                in 0..6 -> {
                    Text(stringResource(R.string.nps_feedback_low))
                    Spacer(modifier = Modifier.height(16.dp))
                    NpsCommentField(
                        comment = comment.value,
                        onCommentChange = { comment.value = it }
                    )
                }

                in 7..8 -> {
                    Text(stringResource(R.string.nps_feedback_mid))
                    Spacer(modifier = Modifier.height(16.dp))
                    NpsCommentField(
                        comment = comment.value,
                        onCommentChange = { comment.value = it }
                    )
                }

                in 9..10 -> {
                    Text(stringResource(R.string.nps_feedback_high))
                    Spacer(modifier = Modifier.height(16.dp))
                    NpsCommentField(
                        comment = comment.value,
                        onCommentChange = { comment.value = it }
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    val score = selected.value
                    onDone()

                }
            ) {
                stringResource(R.string.nps_submit)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun NpsScreenPreview() {
    PasswordManagerTheme {
        NpsScreen(
            surveyId = 1L,
            onBack = {},
            onDone = {}
        )
    }
}