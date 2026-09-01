package br.com.heiderlopes.passwordmanager.ui.screens.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.heiderlopes.passwordmanager.R
import br.com.heiderlopes.passwordmanager.ui.theme.PasswordManagerTheme

@Composable
fun NpsCard(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    val isDarkTheme = isSystemInDarkTheme()

    val imageRes = if (isDarkTheme) {
        R.drawable.nps_dark
    } else {
        R.drawable.nps_light
    }

    Image(
        painter = painterResource(imageRes),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(3f)
            .clip(RoundedCornerShape(16.dp))
            .clickable { onClick() }
    )
}

@Preview
@Composable
private fun NpsCardPreview() {
    PasswordManagerTheme() {
        NpsCard {}
    }
}