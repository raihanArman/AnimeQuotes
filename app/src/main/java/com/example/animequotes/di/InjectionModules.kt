package com.example.animequotes.di

import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.animequotes.data.local.QuoteDatabase
import com.example.animequotes.data.local.datasource.QuoteLocalDataSource
import com.example.animequotes.data.local.datasource.QuoteLocalDataSourceImpl
import com.example.animequotes.data.network.QuoteApiService
import com.example.animequotes.data.network.datasource.QuoteNetworkDataSource
import com.example.animequotes.data.network.datasource.QuoteNetworkDataSourceImpl
import com.example.animequotes.data.repository.QuoteRepositoryImpl
import com.example.animequotes.di.NetworkModule.network
import com.example.animequotes.domain.repository.QuoteRepository
import com.example.animequotes.domain.usecase.AddFavoriteQuoteUseCase
import com.example.animequotes.domain.usecase.DeleteFavoriteQuoteUseCase
import com.example.animequotes.domain.usecase.GetFavoriteQuotesUseCase
import com.example.animequotes.domain.usecase.GetRandomQuoteUseCase
import com.example.animequotes.ui.favorite.FavoriteViewModel
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
    fun getModules() = listOf(network, dataSource, database, repository, useCases, viewModels)

    private val dataSource = module {
        single<QuoteLocalDataSource> { QuoteLocalDataSourceImpl(get()) }
        single<QuoteNetworkDataSource> { QuoteNetworkDataSourceImpl(get()) }
    }

    private val database = module {
        single { get<QuoteDatabase>().quotesDao() }
        single { QuoteDatabase.create(androidContext()) }
    }

    private val repository = module {
        single<QuoteRepository> { QuoteRepositoryImpl(get(), get()) }
    }

    private val useCases = module {
        single { AddFavoriteQuoteUseCase(get(), dispatcher = Dispatchers.IO) }
        single { GetRandomQuoteUseCase(get(), dispatcher = Dispatchers.IO) }
        single { GetFavoriteQuotesUseCase(get(), dispatcher = Dispatchers.IO) }
        single { DeleteFavoriteQuoteUseCase(get(), dispatcher = Dispatchers.IO) }
    }

    private val viewModels = module {
        single { HomeViewModel(get(), get()) }
        single { FavoriteViewModel(get(), get()) }
    }

}