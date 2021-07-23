package com.example.project.ui.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.project.R
import com.example.project.RecyclerAdapteSearch
import java.util.*
import androidx.lifecycle.Observer
import com.example.project.MainAct


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


        return root
    }

    private fun fillList(e:String): ArrayList<People> {
        val people = ArrayList<People>()
        (0..5).forEach {
            people.add(
                People(
                    "https://avatars.githubusercontent.com/u/7534778?v=4",
                    e,
                    "2001",
                    "Moscow"
                )
            )
        }
        return people
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(SearchViewModel::class.java)

//        MainAct.toolbar.title =null
//        val  textviewToolbar: TextView =  requireActivity().findViewById(R.id.toolbar_title)
//        textviewToolbar.text  = "Research"

        val search_input_field: EditText = view.findViewById(R.id.search_input_field)

        val recyclerView_: RecyclerView = view.findViewById(R.id.recycler_search)
        recyclerView_.layoutManager = LinearLayoutManager(context)
        recyclerView_.adapter = RecyclerAdapteSearch(fillList("Andrey"))

        viewModel.search_field.observe(viewLifecycleOwner,
            { e->
                recyclerView_.adapter = RecyclerAdapteSearch(fillList(e))
            }
        )

        val afterTextChangedListener = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) = Unit

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) = Unit

            override fun afterTextChanged(s: Editable) {
                viewModel.searchDataChanged(
                    search_input_field.text.toString()
                )
            }
        }
        search_input_field.addTextChangedListener(afterTextChangedListener)


    }

}

