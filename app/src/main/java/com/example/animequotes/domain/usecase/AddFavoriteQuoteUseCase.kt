package com.example.animequotes.domain.usecase

import com.example.animequotes.base.arch.BaseUseCase
import com.example.animequotes.base.wrapper.DataResource
import com.example.animequotes.base.wrapper.ViewResource
import com.example.animequotes.domain.mapper.QuoteMapper.toEntity
import com.example.animequotes.domain.repository.QuoteRepository
import com.example.animequotes.domain.viewparams.Quote
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

/**
 * @author Raihan Arman
 * @date 20/07/2022
 */
class AddFavoriteQuoteUseCase(
    val repository: QuoteRepository,
    val dispatcher: CoroutineDispatcher
) : BaseUseCase<Quote, Quote?>(dispatcher)  {
    override suspend fun execute(param: Quote?): Flow<ViewResource<Quote?>> {
        val quote = param?.apply { isFavorite = true }
        return repository.addFavorite(quote.toEntity()).map {
            when (it) {
                is DataResource.Success -> {
                    ViewResource.Success(quote)
                }

                is DataResource.Error -> {
                    ViewResource.Error(it.exception)
                }
            }
        }.onStart { emit(ViewResource.Loading()) }
    }
}