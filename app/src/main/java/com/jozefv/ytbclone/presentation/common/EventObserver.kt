package com.jozefv.ytbclone.presentation.common

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

@Composable
fun <T> EventObserver(
    flow: Flow<T>, // Any kind of event flow from our VM
    key1: Any? = null, // Possible compose states
    key2: Any? = null,
    onEvent: (T) -> Unit // lam,bda which will trigger when we receive an event what we specified in our VM
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    // re execute launch effect when any key changes
    LaunchedEffect(
        flow,
        lifecycleOwner,
        key1,
        key2
    ) {
        // Flow will be collect only if app came to the foreground or is in foreground
        // So flow will be not called during onDestroy() state
        lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            // make sure the emission will be processed as soon as it appears - before possible view destruction
            withContext(Dispatchers.Main.immediate) {
                // when our VM send something to UI, onEvent will be executed with corresponding emission
                flow.collect { event ->
                    onEvent(event)
                }
            }
        }
    }
}