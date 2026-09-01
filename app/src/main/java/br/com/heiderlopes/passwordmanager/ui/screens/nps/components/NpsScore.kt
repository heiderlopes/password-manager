package br.com.heiderlopes.passwordmanager.ui.screens.nps.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.heiderlopes.passwordmanager.ui.theme.AppTheme
import br.com.heiderlopes.passwordmanager.ui.theme.PasswordManagerTheme

@Composable
fun NpsScore(
    score: Int,
    selected: Boolean,
    modifier: Modifier = Modifier
) {

    val scoreColor = when (score) {
        in 0..6 -> AppTheme.colors.nps.detractor
        in 7..8 -> AppTheme.colors.nps.passive
        else -> AppTheme.colors.nps.promoter
    }

    val containerColor = if (selected) {
        scoreColor
    } else {
        MaterialTheme.colorScheme.surfaceContainerHighest
    }

    val contentColor = if (selected) {
        Color.White
    } else {
        MaterialTheme.colorScheme.onSurface
    }

    val borderColor = if (selected) {
        scoreColor
    } else {
        MaterialTheme.colorScheme.outlineVariant
    }

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(containerColor)
            .border(
                width = if (selected) 2.dp else 1.dp,
                color = borderColor,
                shape = RoundedCornerShape(12.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = score.toString(),
            color = contentColor,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = if (selected) {
                FontWeight.Bold
            } else {
                FontWeight.Medium
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun NpsScorePreview() {
    PasswordManagerTheme {
        NpsScore(
            score = 9,
            selected = true
        )
    }
}