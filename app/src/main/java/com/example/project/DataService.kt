package com.example.project


import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.project.DB.Messages.Message
import com.example.project.REST.MainPublicationModel
import com.example.project.REST.MessageMedel
import com.example.project.REST.RetrofitClientInstance
import com.example.project.ui.chat_list.ChatListFragment
import io.reactivex.rxjava3.core.Observable
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DataService {

    @DelicateCoroutinesApi
    suspend fun dataMessages(t: Int): Observable<List<MessageMedel>>? {
        val call: Call<List<MessageMedel>> = RetrofitClientInstance.service.getChatMessages()
        var p: Observable<List<MessageMedel>>? = null
        call.enqueue(object : Callback<List<MessageMedel>> {
            override fun onResponse(call: Call<List<MessageMedel>>, response: Response<List<MessageMedel>>) {
                 p = Observable.create { t -> t.onNext(loadMessages(call, response)) }
            }

            override fun onFailure(call: Call<List<MessageMedel>>, t: Throwable) = Unit
        })


        Log.i("__", "dataMessages: $t")
return p
    }

    private fun loadMessages(call: Call<List<MessageMedel>>, response: Response<List<MessageMedel>>): List<MessageMedel>? {
        return response.body()

    }

    @DelicateCoroutinesApi
    suspend fun dataChats(t: Int): Observable<List<Message>> {

        Log.i("__", "dataMessages: $t")

        val o = GlobalScope.async(Dispatchers.IO) {
            val arr: ArrayList<Message> = arrayListOf()
            (0..10).forEach { e ->
                val message = ChatListFragment.db.MessagesDao().getMessagesfromUserChat(1, 1, t + e)
                Log.i("TAG", "message $message")
                arr.add(message)

            }
            arr
        }.await()

//        Thread.sleep(30)
        val p: Observable<List<Message>> = Observable.create { t -> t.onNext(o.toList()) }
        return p
    }

}