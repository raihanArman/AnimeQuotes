package com.example.animequotes.domain.mapper

import com.example.animequotes.data.network.model.QuoteResponse
import com.example.animequotes.domain.viewparams.Quote

/**
 * @author Raihan Arman
 * @date 14/07/2022
 */
object QuoteMapper {
    fun QuoteResponse?.mapToViewParam() = Quote(
        id = this?.id ?: 0,
        anime = this?.anime.orEmpty(),
        name = this?.name.orEmpty(),
        quote = this?.quote.orEmpty(),
    )
}