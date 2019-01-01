package com.awave.wrkout.dashboard.tracker

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.awave.wrkout.R
import com.awave.wrkout.db.MicronutrientRecording

class MicronutrientAdapter<T> : RecyclerView.Adapter<MicronutrientAdapter.MicronutrientViewHolder>() {
    var items = mutableListOf<T>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    class MicronutrientViewHolder(view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MicronutrientViewHolder {
        return MicronutrientViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.item_micronutrient, parent, false))
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: MicronutrientViewHolder, position: Int) {
        val micronutrient = items[position] as MicronutrientRecording

        val name = holder.itemView.findViewById<TextView>(R.id.micronutrientName)
        val amount = holder.itemView.findViewById<TextView>(R.id.micronutrientAmount)

        name.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                micronutrient.name = s.toString()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })

        amount.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                micronutrient.amount = s.toString().toDouble()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })

    }
}