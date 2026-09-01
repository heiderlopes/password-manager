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
import androidx.compose.foundation.layout.Row
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
import androidx.compose.material.icons.filled.LockOpen
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.integerResource
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
import androidx.compose.ui.unit.dp
import br.com.heiderlopes.passwordmanager.R
import br.com.heiderlopes.passwordmanager.domain.generator.PasswordGenerator
import br.com.heiderlopes.passwordmanager.domain.generator.PinPasswordGenerator
import br.com.heiderlopes.passwordmanager.domain.generator.StandardPasswordGenerator
import br.com.heiderlopes.passwordmanager.domain.model.PasswordType
import br.com.heiderlopes.passwordmanager.ui.components.AppTopBar
import br.com.heiderlopes.passwordmanager.ui.components.CheckboxOption
import br.com.heiderlopes.passwordmanager.ui.components.LogoApp
import br.com.heiderlopes.passwordmanager.ui.theme.PasswordManagerTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreatePasswordScreen() {

    val defaultMinLength = integerResource(R.integer.weak_password_length)
    val defaultMaxLength = integerResource(R.integer.strong_password_length)
    var maxCharacters by rememberSaveable { mutableIntStateOf((defaultMaxLength + defaultMinLength) / 2) }

    val context = LocalContext.current

    var passwordType by rememberSaveable { mutableStateOf(PasswordType.PIN) }

    var isEditable by rememberSaveable { mutableStateOf(false) }

    // Checkboxes
    var includeUppercase by rememberSaveable { mutableStateOf(true) }

    var includeLowercase by rememberSaveable { mutableStateOf(true) }

    var includeNumbers by rememberSaveable { mutableStateOf(true) }

    var includeSymbols by rememberSaveable { mutableStateOf(false) }


    fun copyPassword(context: Context, password: String) {
        val clipboardManager =
            context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager

        val clip = ClipData.newPlainText("Senha", password)
        clipboardManager.setPrimaryClip(clip)
        Toast.makeText(context, "Senha copiada!", Toast.LENGTH_SHORT).show()
    }

    fun generatePassword(maxChar: Int): String {
        val generator: PasswordGenerator = when (passwordType) {
            PasswordType.PIN -> PinPasswordGenerator()
            PasswordType.STANDARD -> StandardPasswordGenerator(
                includeUppercase = if (isEditable)
                    includeUppercase else true,
                includeLowercase = if (isEditable)
                    includeLowercase else true,
                includeNumbers = if (isEditable) includeNumbers
                else true,
                includeSymbols = if (isEditable) includeSymbols
                else true
            )
        }
        return generator.generate(maxChar)
    }

    var password by rememberSaveable { mutableStateOf("") }

    Scaffold(
        topBar = {
            AppTopBar(title = stringResource(R.string.app_name))
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

                    LogoApp(
                        modifier = Modifier.align(
                            Alignment.CenterHorizontally
                        )
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
                        enabled = isEditable,
                        value = password,
                        // atualiza o valor quando o usuário digita.
                        onValueChange = {
                            if (it.length <= maxCharacters) password = it
                        },
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

                    Text(
                        text = "${password.length} / $maxCharacters",
                        style = MaterialTheme.typography.labelSmall,
                        modifier = Modifier
                            .align(Alignment.End)
                            .padding(end = 8.dp, top = 4.dp)
                    )

                    Spacer(modifier = Modifier.height(dimensionResource(R.dimen.space_small)))

                    Text(stringResource(R.string.password_type))

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()

                    ) {

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.weight(1f)
                        ) {
                            RadioButton(
                                selected = passwordType == PasswordType.PIN,
                                onClick = { passwordType = PasswordType.PIN })
                            Text("PIN")
                        }
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.weight(1f)
                        ) {
                            RadioButton(
                                selected = passwordType == PasswordType.STANDARD,
                                onClick = { passwordType = PasswordType.STANDARD })
                            Text("Senha padrão")
                        }
                    }

                    Spacer(modifier = Modifier.height(dimensionResource(R.dimen.space_large)))

                    HorizontalDivider(
                        thickness = dimensionResource(R.dimen.thickness_small),
                        color = MaterialTheme.colorScheme.outline,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(
                            vertical =
                                dimensionResource(R.dimen.space_small)
                        )
                    ) {

                        Icon(
                            imageVector = if (isEditable)
                                Icons.Default.LockOpen else Icons.Filled.Lock,
                            contentDescription = "Ícone de cadeado"
                        )

                        Text(
                            stringResource(R.string.allow_edit_password),
                            modifier = Modifier.padding(horizontal = 8.dp)
                        )

                        Spacer(modifier = Modifier.weight(1f))

                        Switch(
                            checked = isEditable,
                            onCheckedChange = { isEditable = it }
                        )
                    }

                    HorizontalDivider(
                        thickness = dimensionResource(R.dimen.thickness_small),
                        color = MaterialTheme.colorScheme.outline,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(dimensionResource(R.dimen.space_large)))

                    if (isEditable) {
                        Text(text = stringResource(R.string.password_length, maxCharacters))

                        Slider(
                            value = maxCharacters.toFloat(),
                            onValueChange = {
                                maxCharacters = it.toInt()
                                password = ""
                            },
                            valueRange = defaultMinLength.toFloat()..defaultMaxLength.toFloat(),
                            //steps = defaultMaxLength - defaultMinLength,
                            modifier = Modifier.fillMaxWidth()
                        )

                        if (passwordType != PasswordType.PIN) {
                            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.space_large)))

                            Text(stringResource(R.string.include_characters))
                            Spacer(
                                modifier =

                                    Modifier.height(dimensionResource(R.dimen.space_small))
                            )

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    CheckboxOption(
                                        text = stringResource(R.string.uppercase),
                                        checked = includeUppercase,
                                        modifier = Modifier.weight(1f),
                                        onCheckedChange = { includeUppercase = it })

                                    CheckboxOption(
                                        text = stringResource(R.string.lowercase),
                                        checked = includeLowercase,
                                        modifier = Modifier.weight(1f),
                                        onCheckedChange = { includeLowercase = it })
                                }
                            }

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 8.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(top = 8.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    CheckboxOption(
                                        text = stringResource(R.string.numbers),
                                        checked = includeNumbers,
                                        modifier = Modifier.weight(1f),
                                        onCheckedChange = { includeNumbers = it })
                                    CheckboxOption(
                                        text = stringResource(R.string.symbols),
                                        checked = includeSymbols,
                                        modifier = Modifier.weight(1f),
                                        onCheckedChange = { includeSymbols = it })
                                }
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.space_large)))

            Button(onClick = {
                password = generatePassword(
                    if (isEditable) maxCharacters
                    else ((defaultMaxLength + defaultMinLength) / 2)
                )
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