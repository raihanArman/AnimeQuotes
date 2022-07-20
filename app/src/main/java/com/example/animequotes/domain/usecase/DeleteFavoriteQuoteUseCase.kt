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

class DeleteFavoriteQuoteUseCase(
    private val repository: QuoteRepository,
    private val dispatcher: CoroutineDispatcher
) : BaseUseCase<DeleteFavoriteQuoteUseCase.Param, Pair<Quote?, Int>>(dispatcher) {

    override suspend fun execute(param: Param?): Flow<ViewResource<Pair<Quote?, Int>>> {
        return repository.deleteFavoriteQuote(param?.quote.toEntity()).map {
            when (it) {
                is DataResource.Success -> {
                    ViewResource.Success(
                        Pair(
                            param?.quote?.apply { isFavorite = false },
                            param?.position ?: -1
                        )
                    )
                }
                is DataResource.Error -> {
                    ViewResource.Error(it.exception)
                }
            }
        }.onStart { emit(ViewResource.Loading()) }
    }

    data class Param(val quote: Quote?, val position: Int? = -1)
}