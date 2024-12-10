package com.jozefv.ytbclone

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.jozefv.ytbclone.presentation.subscriptions.SubscriptionScreenRoot
import kotlinx.serialization.Serializable

@Serializable
object Subscriptions

@Composable
fun NavigationRoot(navHostController: NavHostController){
    NavHost(navController = navHostController, startDestination = Subscriptions ){
        composable<Subscriptions> {
            SubscriptionScreenRoot(
                onProfileClick = {
                    // TODO: Navigation to profile
                }
            )
        }
    }
}