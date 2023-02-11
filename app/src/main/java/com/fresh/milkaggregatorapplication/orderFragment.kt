package com.fresh.milkaggregatorapplication


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class orderFragment : Fragment() {

    var db: CourseDatabase? = null

    private var coursesRV: RecyclerView? = null
    private var viewmodal: ViewModal? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_order, container, false)
        coursesRV = view.findViewById<View>(R.id.recyclerView) as RecyclerView
        coursesRV!!.layoutManager = LinearLayoutManager(activity)
        coursesRV!!.setHasFixedSize(true)
        val adapter = CourseRVAdapter()
        coursesRV!!.adapter = adapter
        viewmodal = ViewModelProviders.of(this)[ViewModal::class.java]
        viewmodal!!.allCourses.observe(requireActivity()) { models ->
            adapter.submitList(models)
        }

        return view
    }

}