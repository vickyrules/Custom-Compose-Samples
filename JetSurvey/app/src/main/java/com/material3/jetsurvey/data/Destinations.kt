package com.material3.jetsurvey.data

import androidx.annotation.StringRes
import androidx.compose.ui.res.stringResource
import com.material3.jetsurvey.R

//enum class Destinations(title: String) {
//     WelcomeRoute(title = stringResource(R.string.Welcome)),
//     SignUpRoute(title = stringResource( R.string.SignUp).toString() +"/{email}" ),
//     SignInRoute(title = stringResource(R.string.SignIn).toString() +"/{email}"),
//     SurveyRoute(title = stringResource(R.string.Survey)),
//     SurveyResultRoute(title = stringResource(R.string.SurveyResults)),
//
//}
//

object Destinations {
     const val WELCOME_ROUTE = "welcome"
     const val SIGN_UP_ROUTE = "signup/{email}"
     const val SIGN_IN_ROUTE = "signin/{email}"
     const val SURVEY_ROUTE = "survey"
     const val SURVEY_RESULTS_ROUTE = "surveyresults"
}