package com.github.aayman93.passwordy.feature_password.presentation.passwords

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.aayman93.passwordy.databinding.ItemPasswordBinding
import com.github.aayman93.passwordy.feature_password.domain.models.PasswordInfo

class PasswordsAdapter(
    private val onCopyButtonClickListener: (String) -> Unit
) : ListAdapter<PasswordInfo, PasswordsAdapter.ViewHolder>(Differ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemPasswordBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

    inner class ViewHolder(private val binding: ItemPasswordBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.copyButton.setOnClickListener {
                val item = getItem(adapterPosition)
                item?.let {
                    onCopyButtonClickListener.invoke(it.password)
                }
            }
        }

        fun bind(passwordInfo: PasswordInfo) {
            binding.item = passwordInfo
        }
    }

    companion object Differ : DiffUtil.ItemCallback<PasswordInfo>() {
        override fun areItemsTheSame(oldItem: PasswordInfo, newItem: PasswordInfo): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: PasswordInfo, newItem: PasswordInfo): Boolean {
            return oldItem == newItem
        }
    }
}