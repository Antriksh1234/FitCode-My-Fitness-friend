package com.atandroidlabs.fitcode_myfitnessfriend

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView

class BmiHistoryAdapter(var context: Context, var timePeriod: ArrayList<String>, var bmiList: ArrayList<String>) : RecyclerView.Adapter<BmiHistoryAdapter.BmiHistoryViewHolder>() {

    override fun onBindViewHolder(holder: BmiHistoryViewHolder, position: Int) {
        holder.bmiTextView.text = bmiList[position]
        holder.timeTextView.text = timePeriod[position]
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BmiHistoryViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.profile_recycler, parent, false)
        return BmiHistoryViewHolder(view)
    }

    override fun getItemCount(): Int {
        return timePeriod.size
    }

    class BmiHistoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var timeTextView: TextView = itemView.findViewById(R.id.title)
        var bmiTextView: TextView = itemView.findViewById(R.id.description)
    }
}