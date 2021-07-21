package com.example.project.ui.Publications

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.project.R
import com.example.project.RecyclerAdapterPublications

class PublicationsFragment : Fragment() {

    companion object {
        fun newInstance() = PublicationsFragment()
    }

    private lateinit var viewModel: PublicationsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root =  inflater.inflate(R.layout.fragmet_publications, container, false)
        val recyclerView_: RecyclerView = root.findViewById(R.id.recyclerview_publication)
        recyclerView_.layoutManager = LinearLayoutManager(context)
        recyclerView_.adapter = RecyclerAdapterPublications(fillList() as ArrayList<String>)
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PublicationsViewModel::class.java)

    }

    private fun fillList(): MutableList<String> {
        val dat = mutableListOf<String>()
        (0..15).forEach { i ->
            dat.add("ds")
        }
        return dat
    }


}