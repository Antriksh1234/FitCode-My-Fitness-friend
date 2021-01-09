package com.atandroidlabs.fitcode_myfitnessfriend

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ProfileAdapter(var context: Context, var titleList: ArrayList<String>, var descriptionList: ArrayList<String>) : RecyclerView.Adapter<ProfileAdapter.ProfileViewHolder>() {

    override fun getItemCount(): Int {
        return titleList.size
    }

    override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {
        holder.titleTextView.text = titleList[position]
        holder.descriptionTextView.text = descriptionList[position]
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.profile_recycler, parent, false)
        return ProfileViewHolder(view)
    }

    class ProfileViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var titleTextView: TextView = itemView.findViewById(R.id.title)
        var descriptionTextView: TextView = itemView.findViewById(R.id.description)
    }
}