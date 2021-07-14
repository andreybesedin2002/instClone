package com.example.project.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.project.R

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

    return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ProfileOtherUserViewModel::class.java)
        // TODO: Use the ViewModel
    }

}