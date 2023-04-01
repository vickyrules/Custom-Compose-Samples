package com.material3.jetsurvey.ui.signin_signup

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.material3.jetsurvey.R
import com.material3.jetsurvey.ui.theme.stronglyDeemphasizedAlpha

@Composable
fun WelcomeScreen(
    onSignInSignUp: (email: String) -> Unit,
    onSignInAsGuest: () -> Unit

) {
    var showBranding by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(
            modifier = Modifier
                .weight(1f, fill = showBranding)
                .animateContentSize()
        )
        AnimatedVisibility(
            showBranding,
            Modifier.fillMaxWidth()
        ) {
            Branding()
        }

        Spacer(
            modifier = Modifier
                .weight(1f, fill = showBranding)
                .animateContentSize()
        )

        SignInCreateAccount(
            onSignInSignUp = onSignInSignUp,
            onSignInAsGuest = onSignInAsGuest,
            onFocusChange = { focused -> showBranding = !focused },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        )

    }
}

@Composable
private fun Branding(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .wrapContentHeight(align = Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Logo(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(horizontal = 76.dp)
        )

        Text(
            text = stringResource(R.string.app_tagline),
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(top = 24.dp)
                .fillMaxWidth()
        )

    }
}

@Composable
private fun Logo(
    modifier: Modifier = Modifier,
    lightTheme: Boolean = !isSystemInDarkTheme()
) {
    Image(
        painter = painterResource(
            id = if (lightTheme) R.drawable.ic_logo_light else R.drawable.ic_logo_dark
        ),
        modifier = modifier,
        contentDescription = "Jet-survey logo"
    )
}


@Composable
private fun SignInCreateAccount(
    onSignInSignUp: (email: String) -> Unit,
    onSignInAsGuest: () -> Unit,
    onFocusChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    val emailState by rememberSaveable(stateSaver = EmailStateSaver) {
        mutableStateOf(EmailState())
    }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.sign_in_create_account),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = stronglyDeemphasizedAlpha),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 64.dp, bottom = 12.dp)
        )
        val onSubmit = {
            if (emailState.isValid) {
                onSignInSignUp(emailState.text)
            } else {
                emailState.enableShowErrors()
            }
        }

        onFocusChange(emailState.isFocused)
        Email(emailState = emailState, imeAction = ImeAction.Done, onImeAction = onSubmit)
        Button(
            onClick = onSubmit,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 28.dp, bottom = 3.dp)
        ) {

            Text(
                text = stringResource(R.string.user_continue),
                style = MaterialTheme.typography.titleSmall
            )

        }
        OrSignInAsGuest(
            onSignInAsGuest = onSignInAsGuest,
            modifier = Modifier.fillMaxWidth()
        )
    }

}
