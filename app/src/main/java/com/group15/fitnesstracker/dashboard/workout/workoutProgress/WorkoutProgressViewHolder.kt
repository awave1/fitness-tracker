package com.group15.fitnesstracker.dashboard.workout.workoutProgress

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.group15.fitnesstracker.R
import com.group15.fitnesstracker.db.Set
import com.group15.fitnesstracker.db.SetExercise

class WorkoutProgressViewHolder(view: View): RecyclerView.ViewHolder(view), WorkoutProgressContract.ExerciseView {
    private val exerciseName: TextView = this.itemView.findViewById(R.id.exerciseName)
    private val setList: RecyclerView = this.itemView.findViewById(R.id.exerciseSetList)
    private lateinit var adapter: SetAdapter

    override fun showExerciseName(name: String) {
        exerciseName.text = name
    }

    override fun showSets(sets: MutableList<Set>) {
        adapter = SetAdapter()

        setList.setHasFixedSize(true)
        setList.layoutManager = LinearLayoutManager(this.itemView.context)
        setList.adapter = adapter
        adapter.sets = sets
    }

    fun isComplete() = adapter.isComplete
}

class SetAdapter: RecyclerView.Adapter<SetAdapter.SetViewHolder>() {
    var sets = mutableListOf<Set>()
    var isComplete = false
        get() {
            val selected = sets.filter { it.isComplete }
            return selected.size == sets.size
        }

    class SetViewHolder(view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SetViewHolder {
        return SetViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.item_exercise_set, parent, false))
    }

    override fun getItemCount() = sets.size

    override fun onBindViewHolder(holder: SetViewHolder, position: Int) {
        val checkBox = holder.itemView.findViewById<CheckBox>(R.id.isSetComplete)
        val setReps = holder.itemView.findViewById<EditText>(R.id.setReps)
        val setWeight = holder.itemView.findViewById<EditText>(R.id.setWeight)

        checkBox.setOnCheckedChangeListener { _, isChecked -> sets[position].isComplete = isChecked }
    }

}