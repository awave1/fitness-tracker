package com.group15.fitnesstracker.dashboard.profile

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.group15.fitnesstracker.R

class DailyAdapter(val view: Double): RecyclerView.Adapter<DailyAdapter.DailyViewHolder>(){
    class DailyViewHolder (val textView: TextView) : RecyclerView.ViewHolder(textView)
    var items = mutableListOf<T>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyViewHolder {
        return DailyViewHolder(LayoutInflater.from(parent.context).inflate(view, parent, false))
    }

    override fun onBindViewHolder(holder: DailyViewHolder, position: Int) {
        when(view){
            R.layout.daily_item ->{
                val calories = items[0] as Double
                val protein = items[1] as Double
                val carbohydrate = items[2] as Double
                val fat = items[3] as Double
            }
        }
    }

    override fun getItemCount() = items.size
}