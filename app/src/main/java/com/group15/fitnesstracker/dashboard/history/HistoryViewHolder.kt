package com.group15.fitnesstracker.dashboard.history

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.group15.fitnesstracker.R

class HistoryViewHolder(view: View): RecyclerView.ViewHolder(view), HistoryContract.HistoryItemView {
    private val nameTextView = view.findViewById<TextView>(R.id.workoutName)
    private val descriptionTextView = view.findViewById<TextView>(R.id.workoutDescription)

    override fun showName(name: String) {
        nameTextView.text = name
    }

    override fun showDescription(description: String) {
        descriptionTextView.text = description
    }

}