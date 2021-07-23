package com.example.project.ui.LoginOrRegister

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.example.project.MainAct
import com.example.project.R

class LoginOrRegisterFragment : Fragment() {

    companion object {
        fun newInstance() = LoginOrRegisterFragment()
    }

    private lateinit var viewModel: LoginOrRegisterViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.login_or_register_fragment, container, false)

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(LoginOrRegisterViewModel::class.java)
//        MainAct.toolbar.title =null
//
//        val  textviewToolbar: TextView =  requireActivity().findViewById(R.id.toolbar_title)
//        textviewToolbar.text  = ""
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val loginBtn: Button = view.findViewById(R.id.loginBtn)
        val registerBtn: Button = view.findViewById(R.id.registerBtn)




        loginBtn.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_loginOrRegister_to_navigation_login)
        }
        registerBtn.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_loginOrRegister_to_navigation_register)
        }

    }

}