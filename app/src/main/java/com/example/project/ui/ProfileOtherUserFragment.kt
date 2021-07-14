package com.example.project.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.project.R
import com.example.project.RecyclerAdapterPhotosList
import com.example.project.RecyclerAdapterPublications

class ProfileOtherUserFragment : Fragment() {

    companion object {
        fun newInstance() = ProfileOtherUserFragment()
    }

    private lateinit var viewModel: ProfileOtherUserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       val  root =  inflater.inflate(R.layout.profile_other_user_fragment, container, false)
        val recyclerView_: RecyclerView = root.findViewById(R.id.recycler_oter_profile)
        recyclerView_.layoutManager = LinearLayoutManager(context)
        recyclerView_.adapter = RecyclerAdapterPhotosList(fillList1())


    return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ProfileOtherUserViewModel::class.java)
        // TODO: Use the ViewModel
    }
    private fun fillList1(): ArrayList<ArrayList<Int>> {
        val dat = ArrayList<ArrayList<Int>>()
        (0..5).forEach { i ->
            val d = ArrayList<Int>()
            d.add(R.drawable.ic_launcher_foreground)
            d.add(R.drawable.ic_launcher_foreground)
            d.add(R.drawable.ic_launcher_foreground)
            dat.add(d)
        }
        Log.i("TAG", "fillList1:sdf $dat ")
        return dat
    }
}