package com.jozefv.ytbclone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.jozefv.ytbclone.presentation.common.ui.theme.YtbCloneTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
    private val mainViewModel: MainViewModel by viewModel<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Must be before setContent
        installSplashScreen().apply {
            // As long as condition is true, we will keep showing the splash screen
            setKeepOnScreenCondition {
                mainViewModel.state.isCheckingAuth
            }
        }
        enableEdgeToEdge()
        setContent {
            YtbCloneTheme {
                if (!mainViewModel.state.isCheckingAuth) {
                    NavigationRoot(
                        navHostController = rememberNavController(),
                        isLoggedIn = mainViewModel.state.isLoggedIn
                    )
                }
            }
        }
    }
}