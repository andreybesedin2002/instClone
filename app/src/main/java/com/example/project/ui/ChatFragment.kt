package com.example.project.ui

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.project.*
import com.example.project.DB.AppDataBase
import com.example.project.DB.Messages.Message
import com.google.android.material.bottomsheet.BottomSheetBehavior
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.PublishSubject
import kotlinx.android.synthetic.main.fragment_message.*
import kotlinx.coroutines.*
import java.util.ArrayList

class ChatFragment : Fragment() {

    companion object {
        fun newInstance() = ChatFragment()
        lateinit var InstanceView: View
        lateinit var InstanceVContext: Context

        fun addListnerToPhoto() {
            var recyclerView_: RecyclerView = InstanceView.findViewById(R.id.recyclerView)
            val bottomSheetView = InstanceView.findViewById<ConstraintLayout>(R.id.bottomSheet)
            val bottomSheetBehavior = BottomSheetBehavior.from(bottomSheetView)

            recyclerView_ = InstanceView.findViewById(R.id.recyclerView)

            recyclerView_.layoutManager = LinearLayoutManager(InstanceVContext)
            recyclerView_.adapter = RecyclerAdapterPhotosList(fillList1() as ArrayList<String>)

            val mgScrollView =
                InstanceView.findViewById<NestedScrollView>(R.id.dsfg) as NestedScrollView
            mgScrollView.isSmoothScrollingEnabled = true;
        }
        private fun fillList1(): List<String> {
            val dat = mutableListOf<String>()
            (0..15).forEach { i ->
                Log.i("TAG", "load String : $i")
                dat.add("ds")
            }
            Log.i("TAG", "fillList: ---")
            return dat
        }
        lateinit var db: AppDataBase
    }
    var firstVisibleInListview: Int = 0
    var lastVisibleInListview: Int = 0

    var lastLoadedInListview: Int = 30

    var scrollLoadingChannel: PublishSubject<Int> =
        PublishSubject.create<Int>()

    lateinit var recyclerView_: RecyclerView

    private lateinit var viewModel: ChatViewModel

    override fun onResume() {
        super.onResume()
        MainAct.navView.visibility= View.GONE
    }

    override fun onPause() {
        super.onPause()
        MainAct.navView.visibility= View.VISIBLE

    }

     override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_message, container, false)
//        (context as Activity).window.decorView.apply {
//            // Hide both the navigation bar and the status bar.
//            // SYSTEM_UI_FLAG_FULLSCREEN is only available on Android 4.1 and higher, but as
//            // a general rule, you should design your app to hide the status bar whenever you
//            // hide the navigation bar.
//            systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
//        }
         val v: View = layoutInflater.inflate(R.layout.activity_main_toolbar_mine_view, null)
         val  toolbar_view_textview: TextView = v.findViewById(R.id.textview_toolbar)
         MainAct.toolbar.removeAllViews()
         MainAct.toolbar.addView(v)
         MainAct.toolbar.title =null
         toolbar_view_textview.text  = "James"
         MainAct.toolbar.setNavigationIcon(R.drawable.ic_baseline_navigate_before_24)


         InstanceView = root
         InstanceVContext = requireContext()    //создание recyclerView
         recyclerView_ = root.findViewById(R.id.recyclerView)
         recyclerView_.layoutManager = LinearLayoutManager(context)
         recyclerView_.adapter = CustomRecyclerAdapter(fillList() as ArrayList<Message>)


         (recyclerView_.layoutManager as LinearLayoutManager).reverseLayout = true;
        (recyclerView_.layoutManager as LinearLayoutManager).stackFromEnd = true;
        recyclerView_.scrollToPosition(0)

        firstVisibleInListview =
            (recyclerView_.layoutManager as LinearLayoutManager?)!!.findFirstVisibleItemPosition()
        lastVisibleInListview =
            (recyclerView_.layoutManager as LinearLayoutManager?)!!.findLastVisibleItemPosition()


        val send_massege: Button = root.findViewById(R.id.button2)
        send_massege.setOnClickListener {
            if (message.text.toString() != "") {

                addMessage(Message(0, 1, 1, message.text.toString()))
                Log.i("TAG", "onCreate: fghj")

            }
            message.text.clear()
        }


        recyclerView_.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val currentFirstVisible: Int =
                    (recyclerView.layoutManager as LinearLayoutManager?)!!.findFirstVisibleItemPosition()
                val currentLastVisible: Int =
                    (recyclerView.layoutManager as LinearLayoutManager?)!!.findLastVisibleItemPosition()

                if (currentLastVisible == lastLoadedInListview) {
                    Log.i(
                        "RecyclerView scrolled: ",
                        " $currentFirstVisible - $currentLastVisible $lastLoadedInListview"
                    )
                    scrollLoadingChannel.onNext(lastLoadedInListview)
                }

                firstVisibleInListview = currentFirstVisible
                lastVisibleInListview = currentLastVisible
            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }
        })

        createStringSubscriber()

        return root
    }

    private fun addMessage(message: Message) {
        Log.i("__", "addMessage$message")
        val ad = recyclerView_.adapter as CustomRecyclerAdapter
        ad.addMessage(message)
        // ad.notifyItemInserted(ad.itemCount -1)

        ad.notifyDataSetChanged()
    }

    fun createStringSubscriber() {

        val toLoadingChannelSubscriber: io.reactivex.rxjava3.core.Observer<Int> = object :
            io.reactivex.rxjava3.core.Observer<Int> {
            override fun onSubscribe(d: Disposable) {}
            override fun onComplete() {}


            override fun onNext(t: Int) {
                 GlobalScope.launch(Dispatchers.Default) {
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

        val toLoadingChannelSubscriber: io.reactivex.rxjava3.core.Observer<List<Message>> = object :
            io.reactivex.rxjava3.core.Observer<List<Message>> {
            override fun onSubscribe(d: Disposable) {
            }

            override fun onComplete() {
            }

            override fun onNext(t: List<Message>) {
                Log.i("__", "fun On Next$t")
                val ad = recyclerView_.adapter as CustomRecyclerAdapter
                ad.addMessages(t)
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


                (0..100).forEach { el ->
                    MainAct.getInstance(requireContext()).MessagesDao().insert(Message(2 * el, 1, 1, "Really love your most recent photo. I’ve been trying to capture the same thing for a few months and would love some tips!"))
                    MainAct.getInstance(requireContext()).MessagesDao().insert(Message(2 * el + 1, 2, 1, "message ${el}"))
                }
                (0..15).forEach { i ->
                    Log.i("TAG", "load message : $i")
                    dat.add(MainAct.getInstance(requireContext()).MessagesDao().getMessagesfromUserChat(1, 1, 2 * i))
                    dat.add(MainAct.getInstance(requireContext()).MessagesDao().getMessagesfromUserChat(1, 2, 2 * i + 1))
                }
            }
            job.join()
        }
        Log.i("TAG", "fillList: -")
        return dat
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ChatViewModel::class.java)
        // TODO: Use the ViewModel
    }

}