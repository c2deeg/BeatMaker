package com.beatmaker.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.beatmaker.R

class ResumeRecyclerAdapter(val activity: FragmentActivity) : RecyclerView.Adapter<ResumeRecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view  = LayoutInflater.from(activity).inflate(R.layout.resumeadapteritem,parent,false)
        return ResumeRecyclerAdapter.ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
      return 10
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
//        var img_foryou: ImageView = itemView.findViewById(R.id.img_foryou)

    }
}