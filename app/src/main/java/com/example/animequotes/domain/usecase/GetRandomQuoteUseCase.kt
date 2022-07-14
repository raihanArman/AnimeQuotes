package com.example.animequotes.domain.usecase

import com.example.animequotes.base.arch.BaseUseCase
import com.example.animequotes.base.wrapper.DataResource
import com.example.animequotes.base.wrapper.ViewResource
import com.example.animequotes.domain.mapper.QuoteMapper.mapToViewParam
import com.example.animequotes.domain.repository.QuoteRepository
import com.example.animequotes.domain.viewparams.Quote
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

/**
 * @author Raihan Arman
 * @date 14/07/2022
 */
class GetRandomQuoteUseCase(
    private val repository: QuoteRepository,
    private val dispatcher: CoroutineDispatcher
): BaseUseCase<Any, Quote>(dispatcher) {
    override suspend fun execute(param: Any?): Flow<ViewResource<Quote>> =
        repository.getRandomQuote().map {resultNetwork ->
            when(resultNetwork){
                is DataResource.Success ->{
                    ViewResource.Success(
                        resultNetwork.data.mapToViewParam()
                    )

                }else ->{
                    ViewResource.Error(resultNetwork.exception)
                }
            }
        }.onStart { emit(ViewResource.Loading()) }


}