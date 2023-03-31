package com.material3.jetsurvey.ui.signin_signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.material3.jetsurvey.MainActivity
import com.material3.jetsurvey.data.UserRepository
import com.material3.jetsurvey.data.UserRepository.user

class WelcomeViewModel(private val userRepository: UserRepository) : ViewModel() {
    fun handleContinue(
        email: String,
        onNavigateToSignIn: (email: String) -> Unit,
        onNavigateToSignUp: (email: String) -> Unit,
    ) {
        if (userRepository.isKnownUserEmail(email)) {
            onNavigateToSignIn(email)
        } else {
            onNavigateToSignUp(email)
        }

    }

    fun signInAsGuest(
        onSignInComplete: () -> Unit
    ) {
        userRepository.signInAsGuest()
        onSignInComplete()
    }

    /*
  * A companion object helps us by having a single instance of an object that is used by everyone
  * without needing to create a new instance of an expensive object. This is an implementation detail,
  *  and separating it lets us make changes without impacting other parts of the app's code.
  */
    companion object{
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                WelcomeViewModel(UserRepository)
            }
        }
    }

}

//class WelcomeViewModelFactory : ViewModelProvider.Factory {
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(WelcomeViewModel::class.java))
//            return WelcomeViewModel(UserRepository) as T
//        throw IllegalArgumentException("Unknown ViewModel")
//    }
//}
//
