package com.sandoval.thalesemployee.ui.employee_list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sandoval.thalesemployee.databinding.ItemEmployeeListBinding
import com.sandoval.thalesemployee.domain.models.employee_list.DData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EmployeeListAdapter :
    ListAdapter<EmployeeListItems, RecyclerView.ViewHolder>(EmployeeListDiffCallback()) {

    private val adapterScope = CoroutineScope(Dispatchers.Default)

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is EmployeeListItems.EmployeeItem -> 0
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            0 -> EmployeeItemViewHolder.from(parent)
            else -> throw ClassCastException("Unknown viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        when (holder) {
            is EmployeeItemViewHolder -> {
                val data = item as EmployeeListItems.EmployeeItem
                holder.bind(
                    data.data
                )
            }
        }
    }

    fun submitDataList(list: List<DData>) {
        adapterScope.launch {
            val items = list.map {
                EmployeeListItems.EmployeeItem(it)
            }
            withContext(Dispatchers.Main){
                submitList(null)
                submitList(items)
            }
        }
    }

    class EmployeeItemViewHolder private constructor(
        val binding: ItemEmployeeListBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(
            data: DData
        ) {
            binding.data = data
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): EmployeeItemViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemEmployeeListBinding.inflate(layoutInflater, parent, false)
                return EmployeeItemViewHolder(binding)
            }
        }
    }

}

class EmployeeListDiffCallback : DiffUtil.ItemCallback<EmployeeListItems>() {
    override fun areItemsTheSame(oldItem: EmployeeListItems, newItem: EmployeeListItems): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: EmployeeListItems,
        newItem: EmployeeListItems
    ): Boolean {
        return oldItem == newItem
    }

}

sealed class EmployeeListItems {
    data class EmployeeItem(val data: DData) : EmployeeListItems() {
        override val id = data.id.toString()
    }

    abstract val id: String
}