package com.example.project.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.project.DB.Messages.Message
import com.example.project.R
import com.example.project.RecyclerAdapterPublications

class BlankFragment : Fragment() {

    companion object {
        fun newInstance() = BlankFragment()
    }

    private lateinit var viewModel: BlankViewModel

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
        viewModel = ViewModelProvider(this).get(BlankViewModel::class.java)

    }

    private fun fillList(): MutableList<String> {
        val dat = mutableListOf<String>()
        (0..15).forEach { i ->
            dat.add("ds")
        }
        return dat
    }


}