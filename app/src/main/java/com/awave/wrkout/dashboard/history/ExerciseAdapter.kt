package com.awave.wrkout.dashboard.history

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.awave.wrkout.R
import com.awave.wrkout.base.BaseAdapter
import com.awave.wrkout.db.DbInjection
import com.awave.wrkout.db.SetExercise
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ExerciseAdapter(val userId: Int, val workoutId: Int, val recordingId: Int)
    : BaseAdapter<SetExercise, ExerciseAdapter.ExerciseViewHolder>() {
    class ExerciseViewHolder(view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHolder {
        return ExerciseViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.card_exercise, parent, false))
    }

    @SuppressLint("CheckResult")
    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
        val exerciseName = holder.itemView.findViewById<TextView>(R.id.exerciseName)
        val setList = holder.itemView.findViewById<RecyclerView>(R.id.exerciseSetList)
        val adapter = SetAdapter()

        exerciseName.text = items[position].name

        setList.layoutManager = LinearLayoutManager(holder.itemView.context)
        setList.adapter = adapter

        DbInjection.provideSetDao(holder.itemView.context)
                .getCompletedSets(
                        userId = userId,
                        recordingId = recordingId,
                        workoutId = workoutId,
                        exerciseId = items[position].exerciseId
                )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { adapter.items = it }
    }
}