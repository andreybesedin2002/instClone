package com.example.project.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.project.R
import com.example.project.RecyclerAdapteSearch
import com.example.project.RecyclerAdapterComments
import java.util.*

class SearchFragment : Fragment() {

    companion object {
        fun newInstance() = SearchFragment()
    }

    private lateinit var viewModel: SearchViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_search, container, false)

        val recyclerView_: RecyclerView = root.findViewById(R.id.recycler_search)
        recyclerView_.layoutManager = LinearLayoutManager(context)
        recyclerView_.adapter = RecyclerAdapteSearch(fillList())

        return root
    }

    private fun fillList(): ArrayList<People> {
        val people = ArrayList<People>()
        (0..5).forEach {
            people.add(People("https://avatars.githubusercontent.com/u/7534778?v=4", "Andrey", "2001", "Moscow"))
        }
        return people
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SearchViewModel::class.java)

    }

}

data class People(
    val photo_url: String,
    val name_user: String,
    val year_user: String,
    val location_user: String
)