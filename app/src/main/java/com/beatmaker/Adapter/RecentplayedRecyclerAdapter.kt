package com.beatmaker.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.beatmaker.R

class RecentplayedRecyclerAdapter(private val activity : FragmentActivity) : RecyclerView.Adapter<RecentplayedRecyclerAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view  = LayoutInflater.from(activity).inflate(R.layout.recentplayeditem,parent,false)
        return RecentplayedRecyclerAdapter.ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    }

    override fun getItemCount(): Int {
        return  10
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
//        var img_foryou: ImageView = itemView.findViewById(R.id.img_foryou)

    }
}