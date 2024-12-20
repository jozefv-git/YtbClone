package com.jozefv.ytbclone.presentation.subscriptions

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jozefv.ytbclone.DispatcherProvider
import com.jozefv.ytbclone.domain.repository.VideoRepository
import com.jozefv.ytbclone.presentation.subscriptions.mappers.toVideoUiParcelize
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.plus

@OptIn(FlowPreview::class)
class SubscriptionViewModel(
    private val videoRepository: VideoRepository,
    private val dispatcherProvider: DispatcherProvider
) : ViewModel() {
    var state by mutableStateOf(SubscriptionState())
        private set
    private val query = snapshotFlow { state.query }
        // Execute only if there are no emissions for 300ms - so we will not fire our request
        // every time when user input changes
        .debounce(300L)
        .onEach { query ->
            if (query.isNotEmpty()) {
                val videos = videoRepository.searchVideos(query)
                    .map { it.toVideoUiParcelize() }
                state = state.copy(listOfSubscriptions = videos)
            } else {
                // if query is empty, show a default list again
                state = state.copy(
                    listOfSubscriptions = videoRepository.getVideos()
                        .map { it.toVideoUiParcelize() })
            }
        }

    init {
        // Our init state
        val videos = videoRepository.getVideos()
            // provide mapping, so we don't break our layering
            .map { it.toVideoUiParcelize() }
        state = state.copy(listOfSubscriptions = videos)
        query.launchIn(viewModelScope.plus(dispatcherProvider.mainImmediate))
    }

    fun onAction(action: SubscriptionAction) {
        when (action) {
            is SubscriptionAction.Order -> {
                state = state.copy(ascendingOrder = !action.ascending)
                val videos = videoRepository.getVideos(ascendingOrder = state.ascendingOrder)
                    .map { it.toVideoUiParcelize() }
                state = state.copy(listOfSubscriptions = videos)
            }

            is SubscriptionAction.SearchQuery -> {
                state = state.copy(query = action.query)
            }

            is SubscriptionAction.OnItemClicked -> {
                state = state.copy(itemPosition = action.index)
            }
        }
    }
}