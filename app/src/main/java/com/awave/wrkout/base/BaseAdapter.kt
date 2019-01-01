package com.awave.wrkout.base

import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<T, V : RecyclerView.ViewHolder?>: RecyclerView.Adapter<V>() {
    var items = listOf<T>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount() = items.size
}