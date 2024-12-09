package com.jozefv.ytbclone.presentation.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.jozefv.ytbclone.presentation.common.SpacerVerL
import org.koin.androidx.compose.koinViewModel

@Composable
fun LoginScreenRoot(
    viewModel: LoginScreenViewModel = koinViewModel(),
    onLogin: () -> Unit
) {

    LoginScreen(
        state = viewModel.state,
        onLogin = { action ->
            when (action) {
                is LoginAction.OnLoginCLicked -> {
                    viewModel.onAction(action)
                    onLogin()
                }
            }
        }
    )
}


@Composable
private fun LoginScreen(
    state: LoginScreenState,
    onLogin: (LoginAction) -> Unit
) {
    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (state.isLogging) {
            Text(style = MaterialTheme.typography.headlineMedium, text = "Logging in...")
            SpacerVerL()
            CircularProgressIndicator()
        } else {
            Text(style = MaterialTheme.typography.headlineLarge, text = "YtbClone")
            SpacerVerL()
            Button(onClick = { onLogin(LoginAction.OnLoginCLicked) }) {
                Text(text = "Login")
            }
        }
    }
}

@Preview
@Composable
private fun PreviewLoginScreen() {
    MaterialTheme {
        LoginScreen(
            state = LoginScreenState(isLogging = false),
            onLogin = {}
        )
    }
}