package com.material3.jetsurvey.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.material3.jetsurvey.data.Destinations
import com.material3.jetsurvey.ui.signin_signup.SignInRoute
import com.material3.jetsurvey.ui.signin_signup.SignUpRoute
import com.material3.jetsurvey.ui.signin_signup.WelcomeRoute

@Composable
fun JetSurveyNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Destinations.WELCOME_ROUTE
    ) {
        //welcome scr
        composable(route = Destinations.WELCOME_ROUTE) {
          WelcomeRoute(
              onNavigateToSignIn = {
                  navController.navigate("signin/${it}")
              },
              OnNavigationToSignUp = {
                  navController.navigate("signup/${it}")
              },
              onSignInAsGuest = {
                  navController.navigate(Destinations.SIGN_UP_ROUTE)
              }
          )
        }

        composable(Destinations.SIGN_IN_ROUTE) {
            val startingEmail = it.arguments?.getString("email")
            SignInRoute(
                email = startingEmail,
                onSignInSubmitted = {
                    navController.navigate(Destinations.SURVEY_ROUTE)
                },
                onSignInAsGuest = {
                    navController.navigate(Destinations.SURVEY_ROUTE)
                },
                onNavUp = navController::navigateUp,
            )
        }

        composable(Destinations.SIGN_UP_ROUTE) {
            val startingEmail = it.arguments?.getString("email")
            SignUpRoute(
                email = startingEmail,
                onSignUpSubmitted = {
                    navController.navigate(Destinations.SURVEY_ROUTE)
                },
                onSignInAsGuest = {
                    navController.navigate(Destinations.SURVEY_ROUTE)
                },
                onNavUp = navController::navigateUp,
            )
        }


    }
}