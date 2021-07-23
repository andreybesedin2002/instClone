package com.example.project.ui.login

import android.annotation.SuppressLint
import android.graphics.Typeface
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.project.MainAct

import com.example.project.R
import kotlinx.android.synthetic.main.activity_main2.*
import kotlinx.android.synthetic.main.fragment_item.view.*


class LoginFragment : Fragment() {

    private lateinit var loginViewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    @SuppressLint("InflateParams")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loginViewModel = ViewModelProvider(this, LoginViewModelFactory())
            .get(LoginViewModel::class.java)

        val v: View = layoutInflater.inflate(R.layout.activity_main_toolbar_mine_view, null)

//        val  textviewToolbar:TextView = v.findViewById(R.id.textview_toolbar)
//        MainAct.toolbar.removeAllViews()
//        MainAct.toolbar.addView(v)
//        MainAct.toolbar.title =null
//        textviewToolbar.text  = "Login"
//MainAct.toolbar.title ="jnjnj"
//        MainAct.toolbar.title =null
//        val  textviewToolbar: TextView =  requireActivity().findViewById(R.id.toolbar_title)
//        textviewToolbar.text  = "Login"
//
//        MainAct.toolbar.setNavigationIcon(R.drawable.ic_baseline_navigate_before_24)

        val usernameEditText = view.findViewById<EditText>(R.id.username)
        val passwordEditText = view.findViewById<EditText>(R.id.password)
        val loginButton = view.findViewById<Button>(R.id.loginBtn)
        val loadingProgressBar = view.findViewById<ProgressBar>(R.id.loading)

        val tf = Typeface.createFromAsset(
            requireContext().assets,
            "fronts/Comfortaa-VariableFont_wght.ttf"
        )
        val txt2 = view.findViewById<TextView>(R.id.LoginTV)
        txt2.typeface = tf

        loginViewModel.loginFormState.observe(viewLifecycleOwner,
            Observer { loginFormState ->
                if (loginFormState == null) {
                    return@Observer
                }
                loginButton.isEnabled = loginFormState.isDataValid
//                loginFormState.usernameError?.let {
//                    usernameEditText.error = getString(it)
//                }
//                loginFormState.passwordError?.let {
//                    passwordEditText.error = getString(it)
//                }
            })

        loginViewModel.loginResult.observe(viewLifecycleOwner,
            Observer { loginResult ->
                loginResult ?: return@Observer
                loadingProgressBar.visibility = View.GONE
                loginResult.error?.let {
                    showLoginFailed(it)
                }
                if (loginResult.success) {
                    updateUiWithUser()
                }
            })

        val afterTextChangedListener = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) = Unit

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) = Unit

            override fun afterTextChanged(s: Editable) {
                loginViewModel.loginDataChanged(
                    usernameEditText.text.toString(),
                    passwordEditText.text.toString()
                )
            }
        }
        usernameEditText.addTextChangedListener(afterTextChangedListener)
        passwordEditText.addTextChangedListener(afterTextChangedListener)


        loginButton.setOnClickListener { Log.i("Tggg", "onViewCreated: asdfsf")
            loadingProgressBar.visibility = View.VISIBLE
            Log.i(
                "TAG",
                "onViewCreated: ${usernameEditText.text}  ${passwordEditText.text.toString() == "login@gmail.com"} ${passwordEditText.text}"
            )
            loginViewModel.login(
                usernameEditText.text.toString(),

                passwordEditText.text.toString()
            )
        }

    }

    private fun updateUiWithUser() {
      findNavController().navigate(R.id.action_navigation_login_to_navigation_home2)
    }

    private fun showLoginFailed(@StringRes errorString: Int) {
        val appContext = context?.applicationContext ?: return
        Toast.makeText(appContext, errorString, Toast.LENGTH_LONG).show()
    }
}