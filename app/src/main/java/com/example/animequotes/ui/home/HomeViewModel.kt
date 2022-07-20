package com.example.animequotes.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.animequotes.base.arch.BaseViewModel
import com.example.animequotes.base.wrapper.ViewResource
import com.example.animequotes.domain.usecase.AddFavoriteQuoteUseCase
import com.example.animequotes.domain.usecase.GetRandomQuoteUseCase
import com.example.animequotes.domain.viewparams.Quote
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

/**
 * @author Raihan Arman
 * @date 14/07/2022
 */
class HomeViewModel(
    private val getRandomQuoteUseCase: GetRandomQuoteUseCase,
    private val addFavoriteQuoteUseCase: AddFavoriteQuoteUseCase
): BaseViewModel() {

    private val homeEventChannel = Channel<HomeEvent>()
    val homeEvent = homeEventChannel.receiveAsFlow()

    val observeQuote: LiveData<ViewResource<Quote>>
        get() = _observeQuote
    private val _observeQuote = MutableLiveData<ViewResource<Quote>>()


    val observeAddFavoriteResult: LiveData<ViewResource<Quote?>>
        get() = _observeAddFavoriteResult
    private val _observeAddFavoriteResult = MutableLiveData<ViewResource<Quote?>>()

    var cardColor = "#546E7A"
    var currentQuote : Quote? = null

    private suspend fun getData(){
        getRandomQuoteUseCase().collect {
            _observeQuote.value = it
        }
    }

    fun getRandomQuote(){
        viewModelScope.launch {
            getData()
        }
    }

    fun onRefreshData(){
        viewModelScope.launch {
            getData()
            homeEventChannel.send(HomeEvent.RefreshData("Refresh data"))
        }
    }

    fun addFavoriteQuote(){
        viewModelScope.launch {
            currentQuote?.let {quote ->
                if(quote.isFavorite){
                    // Delete

                }else{
                    addFavoriteQuoteUseCase(quote).collect{
                        _observeAddFavoriteResult.value = it
                    }
                }
            }
        }
    }

    fun onNavigateToFavorite(){
        viewModelScope.launch {
            homeEventChannel.send(HomeEvent.NavigateToFavorite)
        }
    }

}