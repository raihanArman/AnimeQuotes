package com.example.animequotes.base.arch

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlin.coroutines.CoroutineContext

/**
 * @author Raihan Arman
 * @date 14/07/2022
 */
abstract class BaseViewModel: ViewModel(), CoroutineScope {
    private val parentJob = SupervisorJob()
    override val coroutineContext: CoroutineContext = parentJob + Dispatchers.Main

    override fun onCleared() {
        super.onCleared()
        parentJob.cancel()
    }
}