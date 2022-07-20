package com.example.animequotes.ui.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.animequotes.databinding.ItemQuoteBinding
import com.example.animequotes.domain.viewparams.Quote

/**
 * @author Raihan Arman
 * @date 20/07/2022
 */
class FavoriteAdapter(
    private val itemClick: (Quote) -> Unit
): ListAdapter<Quote, FavoriteAdapter.QuoteViewHolder>(DiffCallback()) {
    inner class QuoteViewHolder(
        private val binding: ItemQuoteBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindView(item: Quote) {
            with(item) {
                binding.tvQuotesAuthor.text = this.anime
                binding.tvQuotesText.text = this.quote
                itemView.setOnClickListener { itemClick(this) }
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuoteViewHolder {
        val binding = ItemQuoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return QuoteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: QuoteViewHolder, position: Int) {
        val data = getItem(position)
        holder.bindView(data)
    }

    class DiffCallback: DiffUtil.ItemCallback<Quote>(){
        override fun areItemsTheSame(oldItem: Quote, newItem: Quote): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Quote, newItem: Quote): Boolean {
            return oldItem == newItem
        }

    }

}