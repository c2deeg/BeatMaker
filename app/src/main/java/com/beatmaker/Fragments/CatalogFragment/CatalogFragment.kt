package com.beatmaker.Fragments.CatalogFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.beatmaker.Adapter.NewFilterRecycleAdapter
import com.beatmaker.Adapter.ResumeRecyclerAdapter
import com.beatmaker.R

class CatalogFragment : Fragment() {
    lateinit var resumerecyclerview: RecyclerView
    lateinit var newrecyclerview: RecyclerView
    lateinit var resumeRecyclerAdapter: ResumeRecyclerAdapter
    lateinit var newFilterRecycleAdapter: NewFilterRecycleAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_catalog, container, false)
        init(view)
        listeners(view)
        resumeRecyclerAdapter = ResumeRecyclerAdapter(activity as FragmentActivity)
        resumerecyclerview?.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        resumerecyclerview?.adapter = resumeRecyclerAdapter

        newFilterRecycleAdapter = NewFilterRecycleAdapter(activity as FragmentActivity)
        newrecyclerview?.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        newrecyclerview.adapter = newFilterRecycleAdapter
        return view
    }

    private fun init(view: View?) {
        resumerecyclerview = view?.findViewById(R.id.resumerecyclerview)!!
        newrecyclerview = view?.findViewById(R.id.newrecyclerview)!!


    }

    private fun listeners(view: View?) {

    }

}