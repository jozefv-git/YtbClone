package com.jozefv.ytbclone.presentation.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jozefv.ytbclone.presentation.common.EventObserver
import com.jozefv.ytbclone.presentation.common.SpacerVerL
import com.jozefv.ytbclone.presentation.common.ui.theme.Typography
import org.koin.androidx.compose.koinViewModel

@Composable
fun LoginScreenRoot(
    viewModel: LoginScreenViewModel = koinViewModel(),
    onLoginSuccess: () -> Unit
) {

    EventObserver(flow = viewModel.channel) { event ->
        if (event is LoginEvent.OnLoginSuccess) {
            onLoginSuccess()
        }
    }

    LoginScreen(
        state = viewModel.state,
        onLogin = { action ->
            if (action is LoginAction.OnLoginCLicked) {
                viewModel.onAction(action)
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
        Modifier.fillMaxSize().padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (state.isLogging) {
            Text(style = Typography.headlineMedium, text = "Logging in...")
            SpacerVerL()
            CircularProgressIndicator(Modifier.size(40.dp))
        } else {
            Text(style = Typography.headlineLarge, text = "YtbClone")
            SpacerVerL()
            Button(modifier = Modifier.fillMaxWidth(), onClick = { onLogin(LoginAction.OnLoginCLicked) }) {
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