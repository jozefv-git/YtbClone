package com.jozefv.ytbclone.presentation.subscriptions.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jozefv.ytbclone.presentation.subscriptions.SubscriptionAction
import com.jozefv.ytbclone.presentation.subscriptions.SubscriptionState
import com.jozefv.ytbclone.presentation.subscriptions.mappers.VideoResultUiParcelize

@Composable
fun SubscriptionList(
    state: SubscriptionState,
    onAction: (SubscriptionAction) -> Unit,
    onItemClick: (VideoResultUiParcelize) -> Unit
) {
    val listState = rememberLazyListState(initialFirstVisibleItemIndex = state.itemPosition)
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        state = listState
    ) {
        itemsIndexed(state.listOfSubscriptions) { index, video -> // TODO: Check keys here
            VideoItem(
                videoResultUiParcelize = video,
                onCLick = {
                    // When navigating back from the detail screen, persist index of our item - so
                    //  it will be displayed on the top
                    onAction(SubscriptionAction.OnItemClicked(index))
                    // navigate to detail screen with provided content
                    onItemClick(video)
                }
            )
        }
    }
}