package com.awave.wrkout.dashboard.workout.workoutProgress

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.awave.wrkout.R
import com.awave.wrkout.db.Set

class WorkoutProgressViewHolder(view: View): RecyclerView.ViewHolder(view), WorkoutProgressContract.ExerciseView {
    private val exerciseName: TextView = this.itemView.findViewById(R.id.exerciseName)
    private val setList: RecyclerView = this.itemView.findViewById(R.id.exerciseSetList)
    private lateinit var adapter: SetAdapter
    private var sets = mutableListOf<Set>()

    override fun showExerciseName(name: String) {
        exerciseName.text = name
    }

    override fun showSets(sets: MutableList<Set>) {
        this.sets = sets

        adapter = SetAdapter()

        setList.setHasFixedSize(true)
        setList.layoutManager = LinearLayoutManager(this.itemView.context)
        setList.adapter = adapter
        adapter.sets = sets
    }

    override fun getSets() = this.sets

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
        val set = sets[position]

        holder.itemView.findViewById<TextView>(R.id.setNumber).text = (position + 1).toString()

        val checkBox = holder.itemView.findViewById<CheckBox>(R.id.isSetComplete)
        val setReps = holder.itemView.findViewById<EditText>(R.id.setReps)
        val setWeight = holder.itemView.findViewById<EditText>(R.id.setWeight)

        checkBox.setOnCheckedChangeListener { _, isChecked ->
            set.isComplete = isChecked
            if (isChecked) {
                if (setReps.text.toString().isEmpty()) {
                    set.reps = 0;
                }else
                    set.reps = setReps.text.toString().toInt()

                if (setWeight.text.toString().isEmpty()) {
                    set.weight = 0.0;
                }else
                    set.weight = setWeight.text.toString().toDouble()

                setReps.inputType = EditorInfo.TYPE_NULL
                setWeight.inputType = EditorInfo.TYPE_NULL
            } else {
                setReps.inputType = EditorInfo.TYPE_NUMBER_FLAG_SIGNED
                setWeight.inputType = EditorInfo.TYPE_NUMBER_FLAG_DECIMAL
            }
        }
    }
}