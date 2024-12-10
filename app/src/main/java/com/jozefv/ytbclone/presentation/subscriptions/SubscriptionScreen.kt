package com.jozefv.ytbclone.presentation.subscriptions

import CustomScaffold
import CustomToolBar
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.AnimatedPane
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffold
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jozefv.ytbclone.R
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
        onAction = { viewModel.onAction(it) },
        subscriptionState = viewModel.state,
        onProfileClick = { onProfileClick() })
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3AdaptiveApi::class)
@Composable
private fun SubscriptionScreen(
    onAction: (SubscriptionAction) -> Unit,
    subscriptionState: SubscriptionState,
    onProfileClick: () -> Unit
) {
    val navigator = rememberListDetailPaneScaffoldNavigator<VideoResultUiParcelize>()
    val animatedRotation by animateFloatAsState(
        targetValue = if (subscriptionState.ascendingOrder) 0f else 180f,
        label = "rotation"
    )
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    var isTrailingContentVisible by remember {
        mutableStateOf(true)
    }
    var isSearchVisible by remember {
        mutableStateOf(false)
    }
    // Handle back from the detail screen
    BackHandler(navigator.canNavigateBack()) {
        isTrailingContentVisible = !isTrailingContentVisible
        navigator.navigateBack()
    }
    // If search is visible and user pressed back button, firstly, hide the search
    // If user press back again, go back
    BackHandler(isSearchVisible) {
        isSearchVisible = !isSearchVisible
    }
    CustomScaffold(
        modifier = Modifier
            .nestedScroll(scrollBehavior.nestedScrollConnection)
            .padding(bottom = 16.dp),
        topAppBar = {
            CustomToolBar(
                scrollBehavior = scrollBehavior,
                title = "Subscription list",
                trailingContent = {
                    if (isTrailingContentVisible) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            IconButton(
                                onClick = {
                                    isSearchVisible = !isSearchVisible
                                }) {
                                Icon(
                                    imageVector = Icons.Default.Search,
                                    contentDescription = Icons.Default.Search.name
                                )
                            }
                            if (!isSearchVisible) {
                                IconButton(onClick = {
                                    onAction(SubscriptionAction.Order(subscriptionState.ascendingOrder))
                                }) {
                                    Icon(
                                        modifier = Modifier
                                            .graphicsLayer {
                                                rotationZ = animatedRotation
                                            },
                                        painter = painterResource(id = R.drawable.swap_vert_24),
                                        contentDescription = "Ordering"
                                    )
                                }

                                IconButton(onClick = { onProfileClick() }) {
                                    Icon(
                                        imageVector = Icons.Default.AccountCircle,
                                        contentDescription = Icons.Default.AccountCircle.name
                                    )
                                }
                            }
                        }
                    }
                })
        },
        content = { paddingValues ->
            Column(Modifier.padding(paddingValues)) {
                AnimatedVisibility(
                    visible = isSearchVisible
                ) {
                    Box(modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp)) {
                        OutlinedTextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = subscriptionState.query,
                            placeholder = { Text(text = "Eclipse...") },
                            onValueChange = {
                                onAction(SubscriptionAction.SearchQuery(it))
                            })
                    }
                }
                ListDetailPaneScaffold(
                    modifier = Modifier
                        .fillMaxSize(),
                    directive = navigator.scaffoldDirective,
                    value = navigator.scaffoldValue,
                    listPane = {
                        AnimatedPane {
                            SubscriptionList(
                                onAction = { onAction(it) },
                                state = subscriptionState,
                                onItemClick = { videoResult ->
                                    isSearchVisible = false
                                    isTrailingContentVisible = !isTrailingContentVisible
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
        }
    )
}

@Preview
@Composable
private fun SubscriptionScreenPreview() {
    MaterialTheme {
        SubscriptionScreen(
            onAction = {},
            subscriptionState = SubscriptionState(),
            onProfileClick = {})
    }
}