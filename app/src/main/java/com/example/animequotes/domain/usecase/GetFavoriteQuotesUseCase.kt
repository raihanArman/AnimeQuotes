package com.example.animequotes.domain.usecase

import com.example.animequotes.base.arch.BaseUseCase
import com.example.animequotes.base.wrapper.DataResource
import com.example.animequotes.base.wrapper.ViewResource
import com.example.animequotes.domain.mapper.QuoteMapper.mapToViewParams
import com.example.animequotes.domain.repository.QuoteRepository
import com.example.animequotes.domain.viewparams.Quote
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

class GetFavoriteQuotesUseCase(
    private val repository: QuoteRepository,
    private val dispatcher: CoroutineDispatcher
) : BaseUseCase<Any, MutableList<Quote>>(dispatcher) {
    override suspend fun execute(param: Any?): Flow<ViewResource<MutableList<Quote>>> {
        return repository.getFavorite().map {
            when (it) {
                is DataResource.Success -> {
                    if (it.data?.isEmpty() == true) {
                        ViewResource.Empty()
                    } else {
                        ViewResource.Success(it.data.mapToViewParams())
                    }
                }
                is DataResource.Error -> {
                    ViewResource.Error(it.exception)
                }
            }
        }.onStart { emit(ViewResource.Loading()) }
    }
}