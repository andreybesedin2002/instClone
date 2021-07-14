package com.example.project

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.navigation.fragment.findNavController

class LoginOrRegisterFragment : Fragment() {

    companion object {
        fun newInstance() = LoginOrRegisterFragment()
    }

    private lateinit var viewModel: LoginOrRegisterViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.login_or_register_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(LoginOrRegisterViewModel::class.java)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val b_1: Button = view.findViewById(R.id.button_1)
        val b_2: Button = view.findViewById(R.id.button_2)
        val y: ImageView = view.findViewById(R.id.appCompatImageView)

        b_1.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_loginOrRegister_to_navigation_login)
        }
        b_2.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_loginOrRegister_to_navigation_register)
        }

    }

}