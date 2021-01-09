package com.atandroidlabs.fitcode_myfitnessfriend

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView


class VideoAdapter(
    var context: Context,
    var tipList: ArrayList<String>,
    var videoLinkList: ArrayList<String>
) : RecyclerView.Adapter<VideoAdapter.VideoViewHolder>() {

    override fun getItemCount(): Int {
        return tipList.size
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        holder.tipTextView.text = tipList[position]

        holder.itemView.setOnClickListener {
            val intent: Intent = Intent(Intent.ACTION_VIEW, Uri.parse(videoLinkList[position]));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setPackage("com.google.android.youtube");
            context.startActivity(intent)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.tips_item, parent, false)
        return VideoViewHolder(view)
    }

    class VideoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tipTextView: TextView = itemView.findViewById(R.id.tipTextView)
    }
}