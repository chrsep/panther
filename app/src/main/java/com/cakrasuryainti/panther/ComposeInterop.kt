package com.cakrasuryainti.panther

import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.ActivityResultRegistryOwner
import androidx.activity.result.contract.ActivityResultContract
import androidx.compose.runtime.*
import androidx.compose.runtime.savedinstancestate.rememberSavedInstanceState
import androidx.compose.ui.platform.AmbientContext
import androidx.core.app.ActivityOptionsCompat
import timber.log.Timber
import java.util.*

// This is probably going to be part of compose. https://issuetracker.google.com/issues/172690553?pli=1
// TODO: Clean this up when available on compose core.
@Composable
fun <I, O> registerForActivityResult(
    contract: ActivityResultContract<I, O>,
    onResult: (O) -> Unit
): ActivityResultLauncher<I> {
    // First, find the ActivityResultRegistry by casting the Context
    // (which is actually a ComponentActivity) to ActivityResultRegistryOwner
    // FIXME: Line below crashes the preview window since AmbientContext is not an activity.
    val owner = AmbientContext.current as ActivityResultRegistryOwner
    val activityResultRegistry = owner.activityResultRegistry

    // Keep track of the current onResult listener
    val currentOnResult = rememberUpdatedState(onResult)

    // It doesn't really matter what the key is, just that it is unique
    // and consistent across configuration changes
    var key = rememberSavedInstanceState { UUID.randomUUID().toString() }

    // Since we don't have a reference to the real ActivityResultLauncher
    // until we register(), we build a layer of indirection so we can
    // immediately return an ActivityResultLauncher
    // (this is the same approach that Fragment.registerForActivityResult uses)
    val realLauncher = mutableStateOf<ActivityResultLauncher<I>?>(null)
    val returnedLauncher = remember {
        object : ActivityResultLauncher<I>() {
            override fun launch(input: I, options: ActivityOptionsCompat?) {
                realLauncher.value?.launch(input, options)
            }

            override fun unregister() {
                realLauncher.value?.unregister()
            }

            override fun getContract() = contract
        }
    }

    Timber.d("outside")
    // DisposableEffect ensures that we only register once
    // and that we unregister when the composable is disposed
    DisposableEffect(activityResultRegistry, key, contract) {
        Timber.d("effect called")
        realLauncher.value = activityResultRegistry.register(key, contract) {
            Timber.d("Contract called")
            currentOnResult.value(it)
        }
        onDispose {
            // FIXME: Wasn't able to get disposable working, Try fixing it on next compose update.
//            realLauncher.value?.unregister()
            Timber.d("Disposed $key")
        }
    }
    return returnedLauncher
}
