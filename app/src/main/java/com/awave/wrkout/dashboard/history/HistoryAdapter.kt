package com.awave.wrkout.dashboard.history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.awave.wrkout.R
import com.awave.wrkout.db.Workout

class HistoryAdapter(private val presenter: HistoryContract.Presenter, private val userId: Int): RecyclerView.Adapter<HistoryViewHolder>() {
    var items: List<Workout> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        return HistoryViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.card_workout_routine, parent, false))
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        presenter.onBindViewAtPosition(position, holder, userId)
    }

}