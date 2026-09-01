package br.com.heiderlopes.passwordmanager.ui.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.com.heiderlopes.passwordmanager.ui.theme.PasswordManagerTheme

@Composable
fun HorizontalAnimatedContent(
    currentPage: Int, modifier: Modifier = Modifier, content: @Composable (Int) -> Unit
) {
    AnimatedContent(
        targetState = currentPage, transitionSpec = {

            if (targetState > initialState) {

                slideInHorizontally { width -> width } + fadeIn() togetherWith slideOutHorizontally { width ->
                    -width
                } + fadeOut()

            } else {

                slideInHorizontally { width ->
                    -width
                } + fadeIn() togetherWith slideOutHorizontally { width ->
                    width
                } + fadeOut()
            }.using(
                SizeTransform(
                    clip = false
                )
            )
        }, modifier = modifier
    ) { page ->

        content(page)
    }
}

@Preview(showBackground = true)
@Composable
private fun HorizontalAnimatedContentPreview() {
    PasswordManagerTheme {
        HorizontalAnimatedContent(
            currentPage = 0
        ) { page ->
            Text(
                text = "Página $page"
            )
        }
    }
}