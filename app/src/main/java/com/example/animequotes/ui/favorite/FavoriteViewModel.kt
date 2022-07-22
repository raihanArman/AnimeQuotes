package com.example.animequotes.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.animequotes.base.arch.BaseViewModel
import com.example.animequotes.base.wrapper.ViewResource
import com.example.animequotes.domain.viewparams.Quote
import com.example.animequotes.domain.usecase.DeleteFavoriteQuoteUseCase
import com.example.animequotes.domain.usecase.GetFavoriteQuotesUseCase
import com.example.animequotes.util.Event
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * @author Raihan Arman
 * @date 20/07/2022
 */
class FavoriteViewModel(
    val getFavoriteQuotesUseCase: GetFavoriteQuotesUseCase,
    val deleteFavoriteQuoteUseCase: DeleteFavoriteQuoteUseCase
): BaseViewModel() {

    val observeQuotes: LiveData<Event<ViewResource<MutableList<Quote>>>>
        get() = _observeQuotes
    private val _observeQuotes = MutableLiveData<Event<ViewResource<MutableList<Quote>>>>()

    val observeRemoveQuote: LiveData<ViewResource<Pair<Quote?, Int>>>
        get() = _observeRemoveQuote
    private val _observeRemoveQuote = MutableLiveData<ViewResource<Pair<Quote?, Int>>>()

    fun getFavoriteQuotes() {
        viewModelScope.launch {
            getFavoriteQuotesUseCase().collectLatest {
                delay(300)
                _observeQuotes.postValue(Event(it))
            }
        }
    }

    fun deleteFavorite(quote: Quote, position: Int){
        viewModelScope.launch {
            deleteFavoriteQuoteUseCase(DeleteFavoriteQuoteUseCase.Param(quote, position)).collect{
                _observeRemoveQuote.postValue(it)
            }
        }
    }

}