package com.example.animequotes.ui.home

import com.example.animequotes.domain.viewparams.Quote

/**
 * @author Raihan Arman
 * @date 19/07/2022
 */
sealed class HomeEvent{
    object NavigateToFavorite: HomeEvent()
    data class RefreshData(val message: String): HomeEvent()
    data class AddFavoriteAction(val quote: Quote): HomeEvent()
    data class ShareQuoteAction(val quote: Quote): HomeEvent()
}