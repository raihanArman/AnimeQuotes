package com.example.animequotes.base.middleware

import kotlinx.coroutines.Dispatchers

/**
 * @author Raihan Arman
 * @date 14/07/2022
 */
class ApplicationDispatchersProvider : SchedulerProvider {
    override fun io() = Dispatchers.IO
    override fun ui() = Dispatchers.Main
    override fun default() = Dispatchers.Default
}