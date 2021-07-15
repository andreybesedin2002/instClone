package com.example.project.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.project.MainAct
import com.example.project.R
import com.example.project.RecyclerAdapterPhotosList


class ProfileOtherUserFragment : Fragment() {

    companion object {
        fun newInstance() = ProfileOtherUserFragment()
    }

    private lateinit var viewModel: ProfileOtherUserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.profile_other_user_fragment, container, false)
        val recyclerView_: RecyclerView = root.findViewById(R.id.recycler_oter_profile)
        recyclerView_.layoutManager = LinearLayoutManager(context)
        recyclerView_.adapter = RecyclerAdapterPhotosList(fillList1())

        val message_btn: Button = root.findViewById(R.id.message_btn)
        message_btn.setOnClickListener {
//            Navigation.findNavController(root)
            val bundle = Bundle()
            bundle.putString("arg1", "value1")
            bundle.putInt("arg2", 2)
           // bundle.putSerializable("USER", user) // Serializable Object
            MainAct.navController
                .navigate(R.id.action_navigation_other_profile_to_navigation_message, bundle)
        }


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