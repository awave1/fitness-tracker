package com.awave.wrkout.dashboard.history

import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.awave.wrkout.R
import com.awave.wrkout.base.BaseAdapter
import com.awave.wrkout.db.Set

class SetAdapter: BaseAdapter<Set, SetAdapter.SetViewHolder>() {
    class SetViewHolder(view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SetViewHolder {
        return SetViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.item_exercise_set, parent, false))
    }

    override fun onBindViewHolder(holder: SetViewHolder, position: Int) {
        holder.itemView.findViewById<CheckBox>(R.id.isSetComplete).visibility = View.GONE
        val setReps = holder.itemView.findViewById<EditText>(R.id.setReps)
        val setWeight = holder.itemView.findViewById<EditText>(R.id.setWeight)
        val setNumber = holder.itemView.findViewById<TextView>(R.id.setNumber)

        setReps.inputType = InputType.TYPE_NULL
        setWeight.inputType = InputType.TYPE_NULL

        setNumber.text = (position + 1).toString()
        setReps.hint = items[position].reps.toString()
        setWeight.hint = items[position].weight.toString()
    }
}