package com.jozefv.ytbclone

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.jozefv.ytbclone.presentation.login.LoginScreenRoot
import com.jozefv.ytbclone.presentation.profile.ProfileScreenRoot
import com.jozefv.ytbclone.presentation.subscriptions.SubscriptionScreenRoot
import kotlinx.serialization.Serializable

@Serializable
object Login

@Serializable
object Profile

@Serializable
object Subscriptions


@Composable
fun NavigationRoot(navHostController: NavHostController, isLoggedIn: Boolean) {
    NavHost(navController = navHostController, startDestination = if(isLoggedIn) Subscriptions else Login) {
        composable<Login> {
            LoginScreenRoot(
                onLoginSuccess = {
                    navHostController.navigate(Subscriptions){
                        popUpTo(Login){
                            inclusive = true
                        }
                    }
                }
            )
        }
        composable<Profile> {
            ProfileScreenRoot(
                onLogoutClick = {
                navHostController.navigate(Login){
                    popUpTo(Subscriptions){
                        inclusive = true
                    }
                }
            })
        }
        composable<Subscriptions> {
            SubscriptionScreenRoot(
                onProfileClick = {
                    navHostController.navigate(Profile){
                        popUpTo(Profile){
                            inclusive = true
                        }
                    }
                }
            )
        }
    }

}