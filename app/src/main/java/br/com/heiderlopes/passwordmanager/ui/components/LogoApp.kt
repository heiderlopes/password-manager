package br.com.heiderlopes.passwordmanager.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import br.com.heiderlopes.passwordmanager.R
import br.com.heiderlopes.passwordmanager.ui.theme.PasswordManagerTheme

@Composable
fun LogoApp(modifier: Modifier = Modifier) {
    Image(
        // Carrega uma imagem local do diretório res/drawable.
        painter = painterResource(id = R.drawable.logo_app),
        // Descrição para acessibilidade (ex: leitores de tela).
        contentDescription = stringResource(R.string.app_name),
        // modifier permite configurar a aparência da imagem:
        modifier = modifier
            // Define um tamanho fixo (largura e altura de 150dp).
            .size(dimensionResource(R.dimen.logo_size))
            // Aplica um fundo colorido no formato de um círculo atrás da imagem. onBackground é uma cor do tema atual (contraste com o fundo).
            .background(
                MaterialTheme.colorScheme.onBackground,
                shape = CircleShape
            ),
        // A imagem será cortada para preencher completamente os 150×150dp, sem distorcer.
        contentScale = ContentScale.Crop
    )

}

@Preview
@Composable
private fun LogoAppPreview() {
    PasswordManagerTheme {
        LogoApp()
    }

}