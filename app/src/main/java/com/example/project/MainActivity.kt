package com.example.project

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.AbsListView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.project.DB.AppDataBase
import com.example.project.DB.Messages.Message
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.PublishSubject
import kotlinx.coroutines.*


@Suppress("UNREACHABLE_CODE")
class MainActivity : AppCompatActivity() {
    companion object {
        lateinit var db: AppDataBase
    }

    var firstVisibleInListview: Int = 0
    var lastVisibleInListview: Int = 0


    var lastLoadedInListview: Int = 30

    var scrollLoadingChannel: PublishSubject<Int> =
        PublishSubject.create<Int>()
    lateinit var recyclerView_: RecyclerView
    fun getlastLoadedInListview(): Int {
        return lastLoadedInListview
    }

    fun setlastLoadedInListview() {
        lastLoadedInListview += 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        recyclerView_ = findViewById(R.id.recyclerView)

        recyclerView_.layoutManager = LinearLayoutManager(this@MainActivity)
        recyclerView_.adapter = RecyclerAdapterChatList(fillList() as ArrayList<Message>)
        (recyclerView_.layoutManager as LinearLayoutManager).reverseLayout = true;
        (recyclerView_.layoutManager as LinearLayoutManager).stackFromEnd = true;
recyclerView_.scrollToPosition(0)

        firstVisibleInListview =
            (recyclerView_.layoutManager as LinearLayoutManager?)!!.findFirstVisibleItemPosition()
        lastVisibleInListview =
            (recyclerView_.layoutManager as LinearLayoutManager?)!!.findLastVisibleItemPosition()


        recyclerView_.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val currentFirstVisible: Int =
                    (recyclerView.layoutManager as LinearLayoutManager?)!!.findFirstVisibleItemPosition()
                val currentLastVisible: Int =
                    (recyclerView.layoutManager as LinearLayoutManager?)!!.findLastVisibleItemPosition()

                if (currentLastVisible == lastLoadedInListview) {
                    //todo smth
                    Log.i(
                        "RecyclerView scrolled: ",
                        " " + currentFirstVisible + " - " + currentLastVisible + " " + lastLoadedInListview
                    )

                    scrollLoadingChannel.onNext(lastLoadedInListview)

                }

                firstVisibleInListview = currentFirstVisible
                lastVisibleInListview = currentLastVisible
            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    // special handler to avoid displaying half elements
                }
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_FLING) {
                    // Do something
                } else if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    // Do something
                } else {
                    // Do something
                }
            }
        })


        createStringSubscriber()

    }

    fun createStringSubscriber() {
        val i = Log.i("__", " fun createStringSubscriber: ")
        val toLoadingChannelSubscriber: Observer<Int> = object : Observer<Int> {
            override fun onSubscribe(d: Disposable) {}
            override fun onComplete() {}


            override fun onNext(t: Int) {
                val job = GlobalScope.launch(Dispatchers.Default) {
                    loadData(t)
                }
            }

            override fun onError(e: Throwable?) {}
        }




        scrollLoadingChannel.take(1).subscribe(toLoadingChannelSubscriber)
    }

    @DelicateCoroutinesApi
    @SuppressLint("CheckResult")
    suspend fun loadData(lastloaded: Int) {

        val toLoadingChannelSubscriber: Observer<List<Message>> = object : Observer<List<Message>> {
            override fun onSubscribe(d: Disposable) {
            }

            override fun onComplete() {
            }

            override fun onNext(t: List<Message>) {
                Log.i("__", "fun On Next$t")
                val ad = recyclerView_.adapter as RecyclerAdapterChatList
                ad.addNewRandomItems(t)
                ad.notifyItemInserted(ad.itemCount - 9)
                lastLoadedInListview += 10
                ad.notifyDataSetChanged();
                createStringSubscriber()
            }

            override fun onError(e: Throwable) {

            }
        }

        DataService().dataMessages(lastloaded).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe(toLoadingChannelSubscriber)
    }


    private fun fillList(): List<Message> {
        val dat = mutableListOf<Message>()

        runBlocking {
            val job = GlobalScope.launch(Dispatchers.Default) {
                db = Room.databaseBuilder(
                    applicationContext,
                    AppDataBase::class.java, "New_Messages_db___"
                ).build()
                (0..100).forEach { el ->
                    db.MessagesDao().insert(Message(el, 1, 1, "____message_${el}"))
                }
                (0..30).forEach { i ->
                    Log.i("TAG", "load message : $i")
                    dat.add(
                        db.MessagesDao().getMessagesfromUserChat(1, 1, i)
                    )
                }
            }
            job.join()
        }
        Log.i("TAG", "fillList: -")
        return dat
    }
}








