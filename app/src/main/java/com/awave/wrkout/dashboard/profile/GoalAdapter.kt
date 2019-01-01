package com.awave.wrkout.dashboard.profile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.awave.wrkout.R
import com.awave.wrkout.db.Goal
import com.awave.wrkout.util.Utils

class GoalAdapter<T>(val view: Int): RecyclerView.Adapter<GoalAdapter.GoalViewHolder>() {
    var items = mutableListOf<T>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    class GoalViewHolder(view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GoalViewHolder {
        return GoalViewHolder(LayoutInflater.from(parent.context)
                .inflate(view, parent, false))
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: GoalViewHolder, position: Int) {
        when(view) {
            R.layout.goal_item -> {
                val goal = items[position] as Goal

                val date = holder.itemView.findViewById<TextView>(R.id.goal_item_date)
                val desc = holder.itemView.findViewById<TextView>(R.id.goal_item_desc)

                date.text = Utils.formatDate(date = goal.completionDate)
                desc.text = goal.goalDescription
            }
        }
    }
}