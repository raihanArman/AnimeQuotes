package com.example.animequotes.di

import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.animequotes.data.network.QuoteApiService
import com.example.animequotes.data.network.datasource.QuoteNetworkDataSource
import com.example.animequotes.data.network.datasource.QuoteNetworkDataSourceImpl
import com.example.animequotes.data.repository.QuoteRepositoryImpl
import com.example.animequotes.di.NetworkModule.network
import com.example.animequotes.domain.repository.QuoteRepository
import com.example.animequotes.domain.usecase.GetRandomQuoteUseCase
import com.example.animequotes.ui.home.HomeViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

/**
 * @author Raihan Arman
 * @date 14/07/2022
 */
object InjectionModules {
    fun getModules() = listOf(network, dataSource, repository, useCases, viewModels)

    private val dataSource = module {
        single<QuoteNetworkDataSource> { QuoteNetworkDataSourceImpl(get()) }
    }

    private val repository = module {
        single<QuoteRepository> { QuoteRepositoryImpl(get()) }
    }

    private val useCases = module {
        single { GetRandomQuoteUseCase(get(), dispatcher = Dispatchers.IO) }
    }

    private val viewModels = module {
        single { HomeViewModel(get()) }
    }

}