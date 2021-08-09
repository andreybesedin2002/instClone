package com.example.project


import android.util.Log
import com.example.project.REST.MessageMedel
import com.example.project.REST.RetrofitClientInstance
import io.reactivex.rxjava3.core.Observable
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DataService {

    @ExperimentalCoroutinesApi
    @DelicateCoroutinesApi
    suspend fun dataMessages(t: Int): Observable<List<MessageMedel>> ?{
        Log.i("TAG", "dataMessages: fghjkllkjhg")
        val call: Call<List<MessageMedel>> = RetrofitClientInstance.service.getChatMessages()

        var p: Observable<List<MessageMedel>>? = null
        call.enqueue(object : Callback<List<MessageMedel>> {
                override fun onResponse(
                    call: Call<List<MessageMedel>>,
                    response: Response<List<MessageMedel>>
                ) {
                    Log.i("TAG", "onResponse: _+- ${response.body()}")
                    //p = Observable.create { t -> t.onNext(response.body()) }
                   p =  Observable.create { t -> t.onNext(response.body()) }

                }

                override fun onFailure(call: Call<List<MessageMedel>>, t: Throwable) {
                    Log.i("TAG", "onFailure: $t")
                }
            })


        Thread.sleep(300)
//        Log.i("__", "dataMessages: ${p.isEmpty}")
        return p
    }


//    @DelicateCoroutinesApi
//    suspend fun dataChats(t: Int): Observable<List<ChatModel>> {
//
//        Log.i("__", "dataMessages: $t")
//
//        val o = GlobalScope.async(Dispatchers.IO) {
//            val arr: ArrayList<Message> = arrayListOf()
//            (0..10).forEach { e ->
//                val message = ChatListFragment.db.MessagesDao().getMessagesfromUserChat(1, 1, t + e)
//                Log.i("TAG", "message $message")
//                arr.add(message)
//
//            }
//            arr
//        }.await()
//
////        Thread.sleep(30)
//        val p: Observable<List<ChatModel>> = Observable.create { t -> t.onNext(o.toList()) }
//        return p
//    }

}