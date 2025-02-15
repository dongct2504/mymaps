package com.example.mymaps.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mymaps.models.UserMap

private const val TAG = "MapsAdapter"

class MapsAdapter(
    val context: Context,
    val userMaps: List<UserMap>,
    val onClickListener: OnClickListener
) : RecyclerView.Adapter<MapsAdapter.ViewHolder>() {

    interface OnClickListener {
        fun onItemClick(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context)
            .inflate(android.R.layout.simple_list_item_1, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return userMaps.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val userMap: UserMap = userMaps[position]

        holder.itemView.setOnClickListener {
            Log.i(TAG, "Tapped on the position $position")
            onClickListener.onItemClick(position) // pass to the parent
        }

        val textViewTitle: TextView = holder.itemView.findViewById<TextView>(android.R.id.text1)
        textViewTitle.text = userMap.title
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
}
