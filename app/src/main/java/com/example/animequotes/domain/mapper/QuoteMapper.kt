package com.example.animequotes.domain.mapper

import com.example.animequotes.data.local.entity.QuoteEntity
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


    fun Quote?.toEntity() = QuoteEntity(
        id = this?.id,
        anime = this?.anime,
        name = this?.name,
        quote = this?.quote,
        isFavorite = this?.isFavorite,
    )

    fun QuoteEntity?.mapToViewParam() = Quote(
        id = this?.id ?: 0,
        name = this?.name.orEmpty(),
        anime = this?.anime.orEmpty(),
        quote = this?.quote.orEmpty(),
        isFavorite = this?.isFavorite ?: false
    )

    fun List<QuoteEntity>?.mapToViewParams() = mutableListOf<Quote>().apply {
        addAll(this@mapToViewParams?.map {
            it.mapToViewParam()
        } ?: listOf())
    }

}