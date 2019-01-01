package com.awave.wrkout.dashboard.tracker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.awave.wrkout.R
import com.awave.wrkout.base.BaseAdapter
import com.awave.wrkout.db.MicronutrientRecording

class MicronutrientRecordingAdapter: BaseAdapter<MicronutrientRecording, MicronutrientRecordingAdapter.MicronutrientViewHolder>() {
    class MicronutrientViewHolder(view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MicronutrientViewHolder {
        return MicronutrientViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_nutrition_micronutrient, parent, false))
    }

    override fun onBindViewHolder(holder: MicronutrientViewHolder, position: Int) {
        holder.itemView.findViewById<TextView>(R.id.microName).text = items[position].name
        holder.itemView.findViewById<TextView>(R.id.microAmount).text = items[position].amount.toString()
    }

}