package com.material3.jetsurvey.data

import androidx.annotation.StringRes
import androidx.compose.ui.res.stringResource
import com.material3.jetsurvey.R

enum class Destinations(@StringRes title: Int) {
     WelcomeRoute(title = R.string.Welcome),
     SignUpRoute(title = R.string.SignUp),
     SignInRoute(title = R.string.SignIn),
     SurveyRoute(title = R.string.Survey),
     SurveyResultRoute(title = R.string.SurveyResults),

}


