package br.com.heiderlopes.passwordmanager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import br.com.heiderlopes.passwordmanager.navigation.AppNavigation
import br.com.heiderlopes.passwordmanager.ui.screens.home.HomeScreen
import br.com.heiderlopes.passwordmanager.ui.screens.nps.NpsScreen
import br.com.heiderlopes.passwordmanager.ui.screens.password.create.CreatePasswordScreen
import br.com.heiderlopes.passwordmanager.ui.theme.PasswordManagerTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.milliseconds

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        // Ativa a SplashScreen nativa usando a API androidx.core.splashscreen.SplashScreen.
        // Essa chamada exibe a splash automaticamente, usando o estilo definido.
        val splashscreen = installSplashScreen()

        // Cria uma flag que será usada para manter a splash visível por um tempo manualmente.
        // Estado que será observado pela splash
        var keepSplashVisible = true

        // Diz à API da SplashScreen para continuar mostrando a splash enquanto essa condição for true.
        // A splash não sai da tela até que keepSplashScreen seja false.
        splashscreen.setKeepOnScreenCondition {
            keepSplashVisible
        }

        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            delay(2000.milliseconds)
            keepSplashVisible = false
        }

        enableEdgeToEdge()
        setContent {
            PasswordManagerTheme {
                AppNavigation()
            }
        }
    }
}