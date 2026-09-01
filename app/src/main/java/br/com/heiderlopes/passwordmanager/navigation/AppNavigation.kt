package br.com.heiderlopes.passwordmanager.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import br.com.heiderlopes.passwordmanager.ui.screens.home.HomeScreen
import br.com.heiderlopes.passwordmanager.ui.screens.nps.NpsScreen
import br.com.heiderlopes.passwordmanager.ui.screens.onboarding.OnboardingScreen
import br.com.heiderlopes.passwordmanager.ui.screens.password.create.CreatePasswordScreen
import br.com.heiderlopes.passwordmanager.ui.screens.splash.SplashScreen

@Composable
fun AppNavigation(
    navController: NavHostController =
        rememberNavController()
) {
    NavHost(
        navController = navController, startDestination
        = Routes.Splash.route
    ) {
        composable(Routes.Splash.route) {
            SplashScreen(onNavigate = {
                navController.navigate(Routes.Onboarding.route) {
                    popUpTo(Routes.Splash.route) { inclusive = true }
                }
            })
        }
        composable(Routes.Onboarding.route) {
            OnboardingScreen(onFinish = {
                navController.navigate(Routes.Home.route) {
                    popUpTo(Routes.Onboarding.route) { inclusive = true }

                }
            })
        }

        composable(Routes.Home.route) {
            HomeScreen(
                onCreatePassword = {
                    navController.navigate(Routes.CreatePassword.route)
                },
                onEditPassword = { passwordId ->
                    navController.navigate(Routes.EditPassword.createRoute(passwordId))
                },
                onNpsClick = { surveyId ->
                    navController.navigate(Routes.NPS.createRoute(surveyId))
                }
            )
        }

        composable(
            route = Routes.CreatePassword.route,
        ) {
            CreatePasswordScreen(
                passwordId = null, onNavigateBack = {
                    navController.popBackStack()
                })
        }

        composable(
            route = Routes.EditPassword.route,
            arguments = listOf(
                navArgument("passwordId") {
                    type = NavType.LongType
                }),
        ) { backStackEntry ->

            val passwordId = backStackEntry.arguments?.getLong("passwordId")
            CreatePasswordScreen(
                passwordId = passwordId, onNavigateBack = {
                    navController.popBackStack()
                })
        }

        composable(
            route = Routes.NPS.routeWithArgs,
            arguments = listOf(
                navArgument(Routes.NPS.SURVEY_ID_ARG) {
                    type = NavType.LongType
                    defaultValue = -1L
                }
            ),
            deepLinks = listOf(
                navDeepLink {uriPattern = "https://passwordmanager.heiderlopes.com.br/nps/{surveyId}"},
                navDeepLink {uriPattern = "passwordmanager://nps/{surveyId}"}
            )
        ) { backStackEntry ->

            val surveyId = backStackEntry.arguments
                ?.getLong(Routes.NPS.SURVEY_ID_ARG)
                ?.takeIf { it != -1L }

            NpsScreen(
                surveyId = surveyId,
                onBack = { navController.popBackStack() },
                onDone = {
                    navController.popBackStack()
                }
            )
        }
    }
}