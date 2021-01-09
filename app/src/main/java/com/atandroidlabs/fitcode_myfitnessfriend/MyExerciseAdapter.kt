package com.atandroidlabs.fitcode_myfitnessfriend

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyExerciseAdapter(var context: Context, var exerciseNameList: ArrayList<String?>, var durationList: ArrayList<String?>) : RecyclerView.Adapter<MyExerciseAdapter.ExerciseViewHolder>() {

    override fun getItemCount(): Int {
        return exerciseNameList.size
    }

    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
        holder.exerciseTitle.text = exerciseNameList[position]
        holder.durationTextView.text = durationList[position]
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.exercise_listview, parent, false)
        return ExerciseViewHolder(view)
    }

    class ExerciseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var exerciseTitle: TextView = itemView.findViewById(R.id.exerciseTitle)
        var durationTextView: TextView = itemView.findViewById(R.id.duration)
    }
}