package com.example.animequotes.util

import androidx.lifecycle.Observer

/**
 * @author Raihan Arman
 * @date 22/07/2022
 */
class EventObserver<T>(private val onEventUnhandledContent: (T) -> Unit) : Observer<Event<T>> {
    override fun onChanged(event: Event<T>?) {
        event?.getContentIfNotHandled()?.let { value ->
            onEventUnhandledContent(value)
        }
    }
}