package com.example.animequotes.base.middleware

import kotlinx.coroutines.CoroutineDispatcher

/**
 * @author Raihan Arman
 * @date 14/07/2022
 */
interface SchedulerProvider {
    fun io(): CoroutineDispatcher
    fun ui(): CoroutineDispatcher
    fun default(): CoroutineDispatcher
}