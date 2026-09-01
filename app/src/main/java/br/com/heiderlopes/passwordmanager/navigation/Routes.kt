package br.com.heiderlopes.passwordmanager.navigation

sealed class Routes(val route: String) {
    object Splash : Routes("splash")
    object Onboarding : Routes("onboarding")
    object Home : Routes("home")
    object CreatePassword : Routes("create_password")
    object EditPassword :

        Routes("edit_password/{passwordId}") {
        fun createRoute(passwordId: Long): String {
            return "edit_password/$passwordId"
        }
    }

    object ListPassword : Routes("list_password")

    object NPS : Routes("nps") {

        const val SURVEY_ID_ARG = "surveyId"

        const val routeWithArgs =
            "nps?$SURVEY_ID_ARG={$SURVEY_ID_ARG}"

        fun createRoute(surveyId: Long? = null): String {
            return if (surveyId != null) {
                "nps?$SURVEY_ID_ARG=$surveyId"
            } else {
                "nps"
            }
        }
    }

}