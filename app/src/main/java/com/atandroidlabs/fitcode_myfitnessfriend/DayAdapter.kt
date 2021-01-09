package com.atandroidlabs.fitcode_myfitnessfriend

import android.content.Context
import android.graphics.Color
import android.graphics.ColorFilter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class DayAdapter(var context: Context, var listOfDays: ArrayList<Day>) : RecyclerView.Adapter<DayAdapter.DayViewHolder>() {

    var day_number = context.getSharedPreferences("com.atandroidlabs.fitcode_myfitnessfriend", Context.MODE_PRIVATE).getInt("day_number", 1)

    override fun onBindViewHolder(holder: DayViewHolder, position: Int) {
        holder.dayNumberTextView.text = "Day ${position + 1}"

        if (position + 1 == day_number) {
            holder.relativeLayout.setBackgroundResource(R.drawable.today_bg)
            holder.dayNumberTextView.setTextColor(ContextCompat.getColor(context, R.color.white))
            holder.statusImageView.setColorFilter(Color.argb(255, 255, 255, 255))
        } else {
            holder.relativeLayout.setBackgroundResource(R.drawable.day_item_bg)
            holder.dayNumberTextView.setTextColor(ContextCompat.getColor(context, R.color.black))
            holder.statusImageView.setColorFilter(Color.parseColor("#FF000000"))
        }

        if (listOfDays[position].status.contentEquals("Yes")) {
            holder.statusImageView.setImageResource(R.drawable.ic_check_black_24dp)
        } else {
            if (listOfDays[position].status.contentEquals("Pending")) {
                holder.statusImageView.setImageResource(R.drawable.ic_baseline_access_time_24)
            } else {
                holder.statusImageView.setImageResource(R.drawable.ic_clear_black_24dp)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.day_item, parent, false)
        return DayViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listOfDays.size
    }

    class DayViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var dayNumberTextView: TextView = itemView.findViewById(R.id.dayTextView)
        var statusImageView: ImageView = itemView.findViewById(R.id.statusImageView)
        var relativeLayout: RelativeLayout = itemView.findViewById(R.id.day_item_relative)
    }
}