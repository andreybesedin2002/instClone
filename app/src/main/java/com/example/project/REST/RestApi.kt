package com.example.project.REST

import android.view.View
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

public class RestApi {
    companion object {
        public fun<T> run(
            call_: Call<List<T>>,
            success: (response: Response<List<T>>, view: View) -> Unit,
            failure: (t: Throwable) -> Unit,
            v: View
        ) {

            call_.enqueue(object : Callback<List<T>> {
                override fun onResponse(
                    call: Call<List<T>>,
                    response: Response<List<T>>
                ) =
                    success(response, v)

                override fun onFailure(call: Call<List<T>>, t: Throwable) = failure(t)
            })
        }
    }
}