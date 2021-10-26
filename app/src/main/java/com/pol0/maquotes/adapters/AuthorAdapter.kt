package com.pol0.maquotes.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.pol0.maquotes.databinding.ItemAuthorBinding
import com.pol0.maquotes.model.AuthorPresentation

class AuthorAdapter (private val onClickListener: OnClickListener) :
    PagingDataAdapter<AuthorPresentation, AuthorAdapter.AuthorViewHolder>(AuthorComparator()) {
    override fun onBindViewHolder(holder: AuthorViewHolder, position: Int) {
        val author = getItem(position)
        holder.bind(author,onClickListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AuthorViewHolder {
        return AuthorViewHolder.create(parent)
    }

    class AuthorComparator : DiffUtil.ItemCallback<AuthorPresentation>() {
        override fun areItemsTheSame(
            oldItem: AuthorPresentation,
            newItem: AuthorPresentation
        ): Boolean {
            return oldItem.id == newItem.id

        }

        override fun areContentsTheSame(
            oldItem: AuthorPresentation,
            newItem: AuthorPresentation
        ): Boolean {
            return oldItem == newItem
        }

    }

    class OnClickListener(val onClickListener: (author: AuthorPresentation) -> Unit) {
        fun onClick(author: AuthorPresentation) = onClickListener(author)
    }

    class AuthorViewHolder(private val binding: ItemAuthorBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(author: AuthorPresentation?, onClickListener: OnClickListener) {
            author?.let {
                binding.author = it
                binding.executePendingBindings()
                binding.root.setOnClickListener {
                    onClickListener.onClick(author)
                }
            }
        }

        companion object {
            fun create(parent: ViewGroup): AuthorViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemAuthorBinding.inflate(layoutInflater, parent, false)

                return AuthorViewHolder(binding)
            }
        }

    }


}