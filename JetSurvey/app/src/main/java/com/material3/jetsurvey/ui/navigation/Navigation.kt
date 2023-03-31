package com.material3.jetsurvey.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.material3.jetsurvey.data.Destinations
import com.material3.jetsurvey.ui.signin_signup.WelcomeRoute

@Composable
fun JetSurveyNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Destinations.WelcomeRoute.name
    ) {
        //welcome scr
        composable(route = Destinations.WelcomeRoute.name) {
          WelcomeRoute(
              onNavigateToSignIn = {
                  navController.navigate("signin/${it}")
              },
              OnNavigationToSignUp = {
                  navController.navigate("signup/${it}")
              },
              onSignInAsGuest = {
                  navController.navigate(Destinations.SurveyRoute.name)
              }
          )
        }


    }
}