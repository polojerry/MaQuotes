package com.pol0.maquotes.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.pol0.domain.models.Quote
import com.pol0.maquotes.databinding.ItemQuoteBinding

class QuoteAdapter : PagingDataAdapter<Quote, QuoteAdapter.QuoteViewHolder>(QuoteComparator()) {
    override fun onBindViewHolder(holder: QuoteViewHolder, position: Int) {
        val quoteItem = getItem(position)
        holder.bind(quoteItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuoteViewHolder {
        return QuoteViewHolder.create(parent)
    }

    class QuoteComparator : DiffUtil.ItemCallback<Quote>() {
        override fun areItemsTheSame(oldItem: Quote, newItem: Quote): Boolean {
            return oldItem.id == newItem.id

        }

        override fun areContentsTheSame(oldItem: Quote, newItem: Quote): Boolean {
            return oldItem == newItem
        }

    }

    class QuoteViewHolder(private val binding: ItemQuoteBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(quoteItem: Quote?) {
            quoteItem?.let {
                binding.quote = it
                binding.executePendingBindings()
            }
        }

        companion object {
            fun create(parent: ViewGroup): QuoteViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemQuoteBinding.inflate(layoutInflater, parent, false)

                return QuoteViewHolder(binding)
            }
        }

    }


}