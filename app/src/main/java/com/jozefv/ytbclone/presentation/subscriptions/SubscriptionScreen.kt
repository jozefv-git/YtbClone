package com.jozefv.ytbclone.presentation.subscriptions

import CustomScaffold
import CustomToolBar
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.AnimatedPane
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffold
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.jozefv.ytbclone.presentation.subscriptions.components.SubscriptionList
import com.jozefv.ytbclone.presentation.subscriptions.components.VideoDetail
import com.jozefv.ytbclone.presentation.subscriptions.mappers.VideoResultUiParcelize
import org.koin.androidx.compose.koinViewModel


@Composable
fun SubscriptionScreenRoot(
    viewModel: SubscriptionViewModel = koinViewModel(),
    onProfileClick: () -> Unit
) {
    SubscriptionScreen(
        subscriptionState = viewModel.state,
        onProfileClick = { onProfileClick() })
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3AdaptiveApi::class)
@Composable
private fun SubscriptionScreen(subscriptionState: SubscriptionState, onProfileClick: () -> Unit) {
    val navigator = rememberListDetailPaneScaffoldNavigator<VideoResultUiParcelize>()
    CustomScaffold(
        topAppBar = {
            CustomToolBar(
                title = "Subscription list",
                trailingContent = {
                    IconButton(onClick = { onProfileClick() }) {
                        Icon(
                            imageVector = Icons.Default.AccountCircle,
                            contentDescription = Icons.Default.AccountCircle.name
                        )
                    }
                })
        },
        content = { paddingValues ->
            ListDetailPaneScaffold(
                modifier = Modifier.fillMaxSize(),
                directive = navigator.scaffoldDirective,
                value = navigator.scaffoldValue,
                listPane = {
                    AnimatedPane {
                        SubscriptionList(
                            state = SubscriptionState(), // TODO: This must be a real implementation
                            onItemClick = { videoResult ->
                                navigator.navigateTo(
                                    pane = ListDetailPaneScaffoldRole.Detail,
                                    content = videoResult
                                )
                            }
                        )
                    }
                },
                detailPane = {
                    AnimatedPane {
                        // If item exists, show detail pane
                        navigator.currentDestination?.content?.let { videoResult ->
                            VideoDetail(videoResultUiParcelize = videoResult)
                        }
                    }
                }
            )
        }
    )
}

@Preview
@Composable
private fun SubscriptionScreenPreview() {
    MaterialTheme {
        SubscriptionScreen(SubscriptionState(), onProfileClick = {})
    }
}