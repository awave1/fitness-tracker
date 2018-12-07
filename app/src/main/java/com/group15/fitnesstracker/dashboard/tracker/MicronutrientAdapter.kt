package com.group15.fitnesstracker.dashboard.tracker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.group15.fitnesstracker.R
import com.group15.fitnesstracker.db.Goal
import com.group15.fitnesstracker.db.MicronutrientRecording
import com.group15.fitnesstracker.util.Utils

class MicronutrientAdapter<T>(val view: Int): RecyclerView.Adapter<MicronutrientAdapter.MicronutrientViewHolder>() {
    var items = mutableListOf<T>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    class MicronutrientViewHolder(view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MicronutrientViewHolder {
        return MicronutrientViewHolder(LayoutInflater.from(parent.context)
                .inflate(view, parent, false))
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: MicronutrientViewHolder, position: Int) {
        when(view) {
            R.layout.item_micronutrient -> {
                val micronutrient = items[position] as MicronutrientRecording

                val name = holder.itemView.findViewById<TextView>(R.id.micronutrient_item_name)
                val amount = holder.itemView.findViewById<TextView>(R.id.micronutrient_item_amt)

                name.text = micronutrient.name
                amount.text = micronutrient.amount.toString()
            }
        }
    }
}