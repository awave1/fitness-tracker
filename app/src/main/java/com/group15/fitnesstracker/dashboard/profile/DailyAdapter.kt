package com.group15.fitnesstracker.dashboard.profile

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.group15.fitnesstracker.R
import com.group15.fitnesstracker.R.layout.daily_item

class DailyAdapter<T>(val view: Int): RecyclerView.Adapter<DailyAdapter.DailyViewHolder>(){
    class DailyViewHolder (val textView: TextView) : RecyclerView.ViewHolder(textView)
    var items = mutableListOf<T>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyViewHolder {
        return DailyViewHolder()
    }

    override fun onBindViewHolder(holder: DailyViewHolder, position: Int) {
        when(view){
            R.layout.daily_item ->{
                val calories = items[0] as Double
                val protein = items[1] as Double
                val carbohydrate = items[2] as Double
                val fat = items[3] as Double

                val cal = holder.itemView.findViewById<TextView>(R.id.dailyCalories)
                val pro = holder.itemView.findViewById<TextView>(R.id.dailyProtein)
                val car = holder.itemView.findViewById<TextView>(R.id.dailyCarbs)
                val fa = holder.itemView.findViewById<TextView>(R.id.dailyFat)

                cal.text = calories.toString()
                pro.text = protein.toString()
                car.text = carbohydrate.toString()
                fa.text = fat.toString()
            }
        }
    }

    override fun getItemCount() = items.size
}