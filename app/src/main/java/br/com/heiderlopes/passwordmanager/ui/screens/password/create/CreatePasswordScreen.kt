package br.com.heiderlopes.passwordmanager.ui.screens.password.create

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import br.com.heiderlopes.passwordmanager.R
import br.com.heiderlopes.passwordmanager.ui.theme.PasswordManagerTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreatePasswordScreen() {

    val context = LocalContext.current

    fun copyPassword(context: Context, password: String) {
        val clipboardManager = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager

        val clip = ClipData.newPlainText("Senha", password)
        clipboardManager.setPrimaryClip(clip)
        Toast.makeText(context, "Senha copiada!", Toast.LENGTH_SHORT).show()
    }

    fun generatePassword(): String {
        val upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
        val lower = "abcdefghijklmnopqrstuvwxyz"
        val numbers = "0123456789"
        val symbols = "!@#\$%&*"
        val chars = upper+lower+numbers+symbols
        return (1..12)
            .map { chars.random() }
            .joinToString("")
    }

    var password by rememberSaveable { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(stringResource(R.string.app_name))
                }, colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary

                )
            )
        }

    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(dimensionResource(R.dimen.padding_medium))
            // Podemos adicionar o padding hardcoded ou utilizar o do dimens.xml
            //.padding(16.dp)

        ) {
            Box(
                modifier = Modifier
                    // Faz o Box ocupar o espaço vertical disponível entre outros elementos dentro de uma Column.
                    // O valor 1f significa: "pegue todo o espaço que sobrar".
                    .weight(1f)
                    // O Box ocupará 100% da largura disponível.
                    .fillMaxWidth()
                    // Permite que o conteúdo dentro do Box roleverticalmente, caso ultrapasse a altura visível.
                    // rememberScrollState() guarda a posição de rolagem.
                    // Isso é fundamental para formularios longos, listas, ou conteúdo extenso.
                    .verticalScroll(rememberScrollState())
                    // Adiciona espaço interno de 8dp em todos os lados do Box.
                    .padding(dimensionResource(R.dimen.padding_small))
            ) {
                Column {
                    // Conteúdo do formulário

                    Image(
                        // Carrega uma imagem local do diretório res/drawable.
                        painter = painterResource(id = R.drawable.logo_app),
                        // Descrição para acessibilidade (ex: leitores de tela).
                        contentDescription = stringResource(R.string.app_name),
                        // modifier permite configurar a aparência da imagem:
                        modifier = Modifier
                            // Define um tamanho fixo (largura e altura de 150dp).
                            .size(dimensionResource(R.dimen.logo_size))
                            // Aplica um fundo colorido no formato de um círculo atrás da imagem. onBackground é uma cor do tema atual (contraste com o fundo).
                            .background(
                                MaterialTheme.colorScheme.onBackground,
                                shape = CircleShape
                            )
                            // Centraliza a imagem horizontalmente dentro de um Column ou outro layout que aceite alinhamento de filhos. Só tem efeito se usada dentro de layouts como Column ou Box com alinhamento.
                            .align(Alignment.CenterHorizontally),
                        // A imagem será cortada para preencher completamente os 150×150dp, sem distorcer.
                        contentScale = ContentScale.Crop
                    )

                    Text(
                        // Define o conteúdo textual que será mostrado na tela.
                        text = stringResource(R.string.create_password_title),
                        // Esse estilo define tamanho da fonte, peso e espaçamento padrão para títulos.
                        style = MaterialTheme.typography.headlineMedium,
                        // Aplica a cor primária do tema atual — adaptável a claro/escuro.
                        color = MaterialTheme.colorScheme.primary,
                        // Deixa o texto semi-negrito (mais forte que Normal, mais leve que Bold).
                        fontWeight = FontWeight.Bold,
                        // Limita o texto a no máximo 2 linhas.
                        maxLines = 2,
                        textAlign = TextAlign.Center,
                        // Se o texto for maior, ele será cortado e aparecerá ... no fim.
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = dimensionResource(R.dimen.padding_medium))
                    )

                    Text(
                        text = stringResource(R.string.create_password_subtitle),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(dimensionResource(R.dimen.space_large)))

                    OutlinedTextField(
                        value = password,
                        // atualiza o valor quando o usuário digita.
                        onValueChange = { password = it },
                        // Mostra o título do campo (flutua acima do campo quando começa a digitar).
                        label = {
                            Text(stringResource(R.string.password_generated_label))
                        },
                        // O campo vai ocupar toda a largura disponível.
                        modifier = Modifier.fillMaxWidth(),
                        // leadingIcon: coloca um ícone no lado esquerdo do campo.
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Lock,
                                contentDescription =
                                    stringResource(R.string.password_generated_label)
                            )

                        },
                        // trailingIcon: coloca um ícone no lado direito do campo.
                        trailingIcon = {
                            if (password.isNotEmpty()) {
                                Icon(
                                    imageVector = Icons.Filled.ContentCopy,
                                    contentDescription =
                                        stringResource(R.string.copy_password),
                                    modifier = Modifier.clickable {
                                        copyPassword(context, password)
                                    })
                            }
                        }
                    )
                    Spacer(modifier = Modifier.height(dimensionResource(R.dimen.space_large)))


                }
            }

            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.space_large)))

            Button(onClick = {
                password = generatePassword()
            }, modifier = Modifier.fillMaxWidth()) {
                Text(stringResource(R.string.generate_password))
            }

            Text(
                // Define o conteúdo textual que será mostrado na tela.
                // No exemplo está sendo aplicado Negrito somente nome do app no rodapé
                text = buildAnnotatedString {
                    append(stringResource(R.string.developed_by))
                    append(" ")
                    withStyle(
                        style = SpanStyle(
                            fontWeight =
                                FontWeight.ExtraBold
                        )
                    ) {
                        append(stringResource(R.string.app_name))
                    }
                },
                //Define o tamanho da fonte
                //fontSize = 12.dp,
                // Define o estilo da fonte
                style = MaterialTheme.typography.bodySmall,
                // Define a fonte como normal (poderia ser Italic).
                fontStyle = FontStyle.Italic,
                // Alinha o texto
                textAlign = TextAlign.Center,
                modifier = Modifier
                    // O texto ocupa toda a largura disponível do layout pai.
                    .fillMaxWidth()
            )

        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CreatePasswordScreenPreview() {
    PasswordManagerTheme {
        CreatePasswordScreen()
    }
}