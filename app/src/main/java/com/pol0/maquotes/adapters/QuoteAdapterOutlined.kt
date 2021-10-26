package com.pol0.maquotes.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.pol0.maquotes.databinding.ItemQuoteOutlinedBinding
import com.pol0.maquotes.model.QuotePresentation

class QuoteAdapterOutlined(private val favouriteClickListener: OnClickListener) :
    PagingDataAdapter<QuotePresentation, QuoteAdapterOutlined.QuoteOutlinedViewHolder>(
        QuoteComparator()
    ) {
    override fun onBindViewHolder(holder: QuoteOutlinedViewHolder, position: Int) {
        val quoteItem = getItem(position)
        holder.bind(quoteItem, favouriteClickListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuoteOutlinedViewHolder {
        return QuoteOutlinedViewHolder.create(parent)
    }

    class QuoteComparator : DiffUtil.ItemCallback<QuotePresentation>() {
        override fun areItemsTheSame(
            oldItem: QuotePresentation,
            newItem: QuotePresentation
        ): Boolean {
            return oldItem.id == newItem.id

        }

        override fun areContentsTheSame(
            oldItem: QuotePresentation,
            newItem: QuotePresentation
        ): Boolean {
            return oldItem == newItem
        }

    }

    class OnClickListener(val onClickListener: (quote: QuotePresentation) -> Unit) {
        fun onClick(quote: QuotePresentation) = onClickListener(quote)
    }

    class QuoteOutlinedViewHolder(private val binding: ItemQuoteOutlinedBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(quoteItem: QuotePresentation?, favouriteClickListener: OnClickListener) {
            quoteItem?.let { quote ->
                binding.quote = quote
                binding.imageViewFavourite.setOnClickListener {
                    favouriteClickListener.onClick(quote)
                }
                binding.executePendingBindings()
            }
        }

        companion object {
            fun create(parent: ViewGroup): QuoteOutlinedViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemQuoteOutlinedBinding.inflate(layoutInflater, parent, false)

                return QuoteOutlinedViewHolder(binding)
            }
        }

    }


}