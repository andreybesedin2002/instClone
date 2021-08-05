package com.example.project.ui.Message

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.project.*
import com.example.project.DB.Messages.Message
import com.example.project.REST.MessageMedel
import com.example.project.REST.RetrofitClientInstance
import com.google.android.material.bottomsheet.BottomSheetBehavior
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.PublishSubject
import kotlinx.android.synthetic.main.fragment_message.*
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

class MessageFragment : Fragment() ,LifecycleEventObserver{

    companion object {
        fun newInstance() = MessageFragment()
        lateinit var InstanceView: View
        lateinit var InstanceVContext: Context

        fun addListnerToPhoto() {
            var recyclerView_: RecyclerView = InstanceView.findViewById(R.id.recyclerView)
            val bottomSheetView = InstanceView.findViewById<ConstraintLayout>(R.id.bottomSheet)
            val bottomSheetBehavior = BottomSheetBehavior.from(bottomSheetView)

            recyclerView_ = InstanceView.findViewById(R.id.recyclerView)

            recyclerView_.layoutManager = LinearLayoutManager(InstanceVContext)
            recyclerView_.adapter = RecyclerAdapterPhotosList(fillList1())

            val mgScrollView =
                InstanceView.findViewById<NestedScrollView>(R.id.nested_scroll_view) as NestedScrollView
            mgScrollView.isSmoothScrollingEnabled = true
        }
        private fun fillList1(): ArrayList<ArrayList<Int>> {
            val dat = ArrayList<ArrayList<Int>>()
            (0..5).forEach { i ->
                val d = ArrayList<Int>()
                d.add(R.drawable.ic_launcher_foreground)
                d.add(R.drawable.ic_launcher_foreground)
                d.add(R.drawable.ic_launcher_foreground)
                dat.add(d)
            }
            Log.i("TAG", "fillList1:sdf $dat ")
            return dat
        }
    }
    var firstVisibleInListview: Int = 0
    var lastVisibleInListview: Int = 0

    var lastLoadedInListview: Int = 30

    var scrollLoadingChannel: PublishSubject<Int> =
        PublishSubject.create()

    lateinit var recyclerView_: RecyclerView

    private lateinit var viewModel: MessageViewModel

    override fun onResume() {
        super.onResume()
        MainAct.navView.visibility= View.GONE
    }

    override fun onPause() {
        super.onPause()
        MainAct.navView.visibility= View.VISIBLE

    }

     override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val root = inflater.inflate(R.layout.fragment_message, container, false)

         val arg1Value : String? = arguments?.getString("arg1")
         val arg2Value : Int? = arguments?.getInt("arg2")

         InstanceView = root

         InstanceVContext = requireContext()
         //создание recyclerView

         val call: Call<List<MessageMedel>> = RetrofitClientInstance.service.getChatMessages()

         call.enqueue(object : Callback<List<MessageMedel>> {
             override fun onResponse(call: Call<List<MessageMedel>>, response: Response<List<MessageMedel>>) =
                 loadMessages(call,response,root)

             override fun onFailure(call: Call<List<MessageMedel>>, t: Throwable) =
                 Toast.makeText(context, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show()
         })


         (recyclerView_.layoutManager as LinearLayoutManager).reverseLayout = true
         (recyclerView_.layoutManager as LinearLayoutManager).stackFromEnd = true
         recyclerView_.scrollToPosition(0)

        firstVisibleInListview =
            (recyclerView_.layoutManager as LinearLayoutManager?)!!.findFirstVisibleItemPosition()
        lastVisibleInListview =
            (recyclerView_.layoutManager as LinearLayoutManager?)!!.findLastVisibleItemPosition()


        val send_massege: Button = root.findViewById(R.id.sendBtn)
        send_massege.setOnClickListener {
            if (message.text.toString() != "") {
                addMessage(MessageMedel(0, 1, 1, message.text.toString()))
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
                    scrollLoadingChannel.onNext(lastLoadedInListview)
                }
                firstVisibleInListview = currentFirstVisible
                lastVisibleInListview = currentLastVisible
            }

        })

        createStringSubscriber()

        return root
    }
    private fun loadMessages(call: Call<List<MessageMedel>>, response: Response<List<MessageMedel>>, v: View?) {
        recyclerView_ = v!!.findViewById(R.id.recyclerView)
        recyclerView_.layoutManager = LinearLayoutManager(context)
        recyclerView_.adapter = CustomRecyclerAdapter(response.body() as ArrayList<MessageMedel>?)
    }

    private fun addMessage(message: MessageMedel) {
        Log.i("__", "addMessage$message")
        val ad = recyclerView_.adapter as CustomRecyclerAdapter
        ad.addMessage(message)
        ad.notifyDataSetChanged()
    }

    fun createStringSubscriber() {

        val toLoadingChannelSubscriber: io.reactivex.rxjava3.core.Observer<Int> = object :
            io.reactivex.rxjava3.core.Observer<Int> {
            override fun onSubscribe(d: Disposable) = Unit
            override fun onComplete() = Unit
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

        val toLoadingChannelSubscriber: io.reactivex.rxjava3.core.Observer<List<MessageMedel>> = object :
            io.reactivex.rxjava3.core.Observer<List<MessageMedel>> {
            override fun onSubscribe(d: Disposable) = Unit

            override fun onComplete() = Unit

            override fun onNext(t: List<MessageMedel>) {
                Log.i("__", "fun On Next${t.size}")
                val ad = recyclerView_.adapter as CustomRecyclerAdapter

                val success = ad.addMessages(t)
                ad.notifyItemInserted(ad.itemCount - (success-1))
                lastLoadedInListview += success
                ad.notifyDataSetChanged()
                    createStringSubscriber()

            }

            override fun onError(e: Throwable) = Unit

        }

        DataService().dataMessages(lastloaded)!!.subscribeOn(Schedulers.io())
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
        viewModel = ViewModelProvider(this).get(MessageViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        Log.i("TAG", "onStateChanged: to $event  $source ")
    }

}