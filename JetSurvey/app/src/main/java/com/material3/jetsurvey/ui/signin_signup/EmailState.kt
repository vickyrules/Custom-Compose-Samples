package com.material3.jetsurvey.ui.signin_signup

import java.util.regex.Pattern

private const val EMAIL_VALIDATION_REGEX = "^(.+)@(.+)(.com)\$"

class EmailState(val email: String? = null):
//:: converts a Kotlin function into a lambda.
    TextFieldState(validator = ::isEmailValid, errorFor = ::emailValidationError )
    {
        init {
            email?.let {
                text = it
            }
        }
    }



private fun emailValidationError(email: String): String {
    return " Invalid email : $email"
}

private fun isEmailValid(email: String): Boolean {
    return Pattern.matches(EMAIL_VALIDATION_REGEX, email)
}

val EmailStateSaver = textFieldStateSaver(EmailState())