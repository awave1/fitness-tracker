package com.group15.fitnesstracker.dashboard.profile

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.group15.fitnesstracker.R
import com.group15.fitnesstracker.db.dao

class viewAdapter(private val myDataSet: Array<String>):
        RecyclerView.Adapter<viewAdapter.MyViewHolder>(){
    class MyViewHolder (val textView: TextView) : RecyclerView.ViewHolder(textView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val textView = LayoutInflater.from(parent.context)
                .inflate(R.layout.my_text_view, parent, false) as TextView
        return MyViewHolder(textView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        myDataSet[0] = getCalPrevDay();


        holder.textView.text = myDataSet[position]
    }

    override fun getItemCount() = myDataSet.size
}