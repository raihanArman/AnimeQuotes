package com.example.animequotes.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.animequotes.base.arch.BaseViewModel
import com.example.animequotes.base.wrapper.ViewResource
import com.example.animequotes.domain.usecase.GetRandomQuoteUseCase
import com.example.animequotes.domain.viewparams.Quote
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/**
 * @author Raihan Arman
 * @date 14/07/2022
 */
class HomeViewModel(
    private val getRandomQuoteUseCase: GetRandomQuoteUseCase
): BaseViewModel() {

    val observeQuote: LiveData<ViewResource<Quote>>
        get() = _observeQuote
    private val _observeQuote = MutableLiveData<ViewResource<Quote>>()

    var cardColor = "#546E7A"
    var currentQuote : Quote? = null

    fun getRandomQuote(){
        viewModelScope.launch {
            getRandomQuoteUseCase().collect {
                _observeQuote.value = it
            }
        }
    }

}