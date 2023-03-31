package com.material3.jetsurvey.ui.signin_signup

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.material3.jetsurvey.data.Destinations

@Composable
fun WelcomeRoute(
    onNavigateToSignIn : (email: String) -> Unit,
    OnNavigationToSignUp : (email: String) -> Unit,
    onSignInAsGuest: () -> Unit){

    val welcomeViewModel:WelcomeViewModel = viewModel(factory = WelcomeViewModel.Factory)
    WelcomeScreen(
        onSignInSignUp = {email ->
            welcomeViewModel.handleContinue(
                email = email,
                onNavigateToSignIn = onNavigateToSignIn,
                onNavigateToSignUp = OnNavigationToSignUp
            )
        },
        onSignInAsGuest = {
            welcomeViewModel.signInAsGuest(onSignInAsGuest)
        }
    )

}