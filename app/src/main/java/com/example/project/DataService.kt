package com.example.project


import android.util.Log
import com.example.project.DB.Messages.Message
import com.example.project.ui.chat_list.ChatListFragment
import io.reactivex.rxjava3.core.Observable
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class DataService {

    @DelicateCoroutinesApi
    suspend fun dataMessages(t: Int): Observable<List<Message>> {

        Log.i("__", "dataMessages: $t")

        val o = GlobalScope.async(Dispatchers.IO) {
            val arr: ArrayList<Message> = arrayListOf()
            (0..5).forEach { e ->
                arr.add(MainAct.INSTANCE!!.MessagesDao().getMessagesfromUserChat(1, 1, t + 2 * e))
                arr.add(MainAct.INSTANCE!!.MessagesDao().getMessagesfromUserChat(1, 2, t + 2 * e + 1))
            }
            arr
        }.await()

//        Thread.sleep(30)
        return Observable.create { t -> t.onNext(o.toList()) }
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