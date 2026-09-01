package br.com.heiderlopes.passwordmanager.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.heiderlopes.passwordmanager.R
import br.com.heiderlopes.passwordmanager.ui.theme.PasswordManagerTheme

@Composable
fun ServiceLogo(
    serviceName: String,
    modifier: Modifier = Modifier
) {
    val logoRes = getServiceLogo(serviceName)
    Image(
        painter = painterResource(
            id = logoRes
        ),
        contentDescription = "Logo $serviceName",
        contentScale = ContentScale.Fit,
        modifier = modifier
            .padding(4.dp)
    )
}

@DrawableRes
private fun getServiceLogo(
    serviceName: String
): Int {

    val service = serviceName
        .trim()
        .lowercase()

    return when {

        "google" in service ||
                "gmail" in service ->
            R.drawable.ic_gmail

        "netflix" in service ->
            R.drawable.ic_netflix

        "amazon" in service ||
                "prime video" in service ->
            R.drawable.ic_amazon

        "github" in service ->
            R.drawable.ic_github

        "instagram" in service ->
            R.drawable.ic_instagram

        else ->
            R.drawable.logo_app
    }
}

@Preview
@Composable
private fun ServiceLogoPreview() {
    PasswordManagerTheme() {
        ServiceLogo("netflix")
    }

}