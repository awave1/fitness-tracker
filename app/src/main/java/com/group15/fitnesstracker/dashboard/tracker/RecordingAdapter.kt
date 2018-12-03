package com.group15.fitnesstracker.dashboard.tracker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.group15.fitnesstracker.R
import com.group15.fitnesstracker.db.BodyMeasureRecording
import com.group15.fitnesstracker.util.Utils

class RecordingAdapter<T>(val view: Int): RecyclerView.Adapter<RecordingAdapter.RecordingViewHolder>() {
    var items = mutableListOf<T>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    class RecordingViewHolder(view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecordingViewHolder {
        return RecordingViewHolder(LayoutInflater.from(parent.context)
                .inflate(view, parent, false))
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: RecordingViewHolder, position: Int) {
        when (view) {
            R.layout.item_recording_body -> {
                val record = items[position] as BodyMeasureRecording

                val date = holder.itemView.findViewById<TextView>(R.id.body_item_date)
                val fat = holder.itemView.findViewById<TextView>(R.id.body_item_body_fat)
                val weight = holder.itemView.findViewById<TextView>(R.id.body_item_weight)

                date.text = Utils.formatDate(date = record.date)
                fat.text = record.bodyFat.toString()
                weight.text = record.weight.toString()
            }
        }
    }

}