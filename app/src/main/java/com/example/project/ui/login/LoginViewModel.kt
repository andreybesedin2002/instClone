package com.example.project.ui.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.util.Patterns
import com.example.project.MainAct
import com.example.project.data.LoginRepository

import com.example.project.R
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking

class LoginViewModel(private val loginRepository: LoginRepository) : ViewModel() {

    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm

    private val _loginResult = MutableLiveData<LoginResult>()
    val loginResult: LiveData<LoginResult> = _loginResult

    fun login(login_: String, password: String) {
        // can be launched in a separate asynchronous job
        val pass_: Boolean
//        runBlocking {
//            pass_ = GlobalScope.async {
//                Log.i(
//                    "TAG",
//                    "login:  $login_ $password ${
//                        MainAct.INSTANCE!!.AuthDao().check(login_, password)
//                    }"
//                )
//                return@async MainAct.INSTANCE!!.AuthDao().check(login_, password)
//            }.await()
//        }
//        Log.i("TAG", "login: sdfas")
//        //if (MainAct.INSTANCE!!.AuthDao().check(login_,password)) {
        //if (pass_) {
            _loginResult.value =
                LoginResult(success = true)
//        } else {
//            _loginResult.value = LoginResult(error = R.string.login_failed)
//        }
    }

    fun loginDataChanged(username: String, password: String) {
//        if (!isUserNameValid(username)) {
//            _loginForm.value = LoginFormState(usernameError = R.string.invalid_username)
//        } else if (!isPasswordValid(password)) {
//            _loginForm.value = LoginFormState(passwordError = R.string.invalid_password)
//        } else {
           _loginForm.value = LoginFormState(isDataValid = true)
       // }
    }

    // A placeholder username validation check
//    private fun isUserNameValid(username: String): Boolean {
//        return if (username.contains("@")) {
//            Patterns.EMAIL_ADDRESS.matcher(username).matches()
//        } else {
//            username.isNotBlank()
//        }
//    }

    // A placeholder password validation check
    private fun isPasswordValid(password: String): Boolean {
        return password.length > 5
    }
}