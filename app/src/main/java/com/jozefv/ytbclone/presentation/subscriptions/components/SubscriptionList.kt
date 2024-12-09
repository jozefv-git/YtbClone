package com.jozefv.ytbclone.presentation.subscriptions.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.jozefv.ytbclone.presentation.subscriptions.SubscriptionState
import com.jozefv.ytbclone.presentation.subscriptions.mappers.VideoResultUiParcelize

@Composable
fun SubscriptionList(
    state: SubscriptionState,
    onItemClick: (VideoResultUiParcelize) -> Unit
) {
    // When coming back from the detail screen, show the clicked item at the top of our list
    var clickedItemIndex by remember {
        mutableIntStateOf(0)
    }
    val listState = rememberLazyListState(initialFirstVisibleItemIndex = clickedItemIndex)
    LazyColumn(modifier = Modifier.fillMaxSize(), state = listState) {
        itemsIndexed(state.listOfSubscriptions) { index, video -> // TODO: Check keys here
            clickedItemIndex = index
            VideoItem(
                videoResultUiParcelize = video,
                onCLick = {
                    // navigate to detail screen with provided content
                    onItemClick(video)
                }
            )
        }
    }
}