package com.example.animequotes.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.animequotes.base.arch.BaseViewModel
import com.example.animequotes.base.wrapper.ViewResource
import com.example.animequotes.domain.viewparams.Quote
import com.example.animequotes.domain.usecase.DeleteFavoriteQuoteUseCase
import com.example.animequotes.domain.usecase.GetFavoriteQuotesUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * @author Raihan Arman
 * @date 20/07/2022
 */
class FavoriteViewModel(
    val getFavoriteQuotesUseCase: GetFavoriteQuotesUseCase,
    val deleteFavoriteQuoteUseCase: DeleteFavoriteQuoteUseCase
): BaseViewModel() {

    val observeQuotes: LiveData<ViewResource<MutableList<Quote>>>
        get() = _observeQuotes
    private val _observeQuotes = MutableLiveData<ViewResource<MutableList<Quote>>>()

    fun getFavoriteQuotes() {
        viewModelScope.launch {
            getFavoriteQuotesUseCase().collect {
                delay(300)
                _observeQuotes.value = it
            }
        }
    }

}