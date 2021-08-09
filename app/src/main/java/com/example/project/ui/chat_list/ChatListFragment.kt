package com.example.project.ui.chat_list

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.project.*
import com.example.project.DB.AppDataBase
import com.example.project.DB.Messages.Message
import com.example.project.REST.ChatModel
import com.example.project.REST.MessageMedel
import com.example.project.REST.RetrofitClientInstance
import com.google.android.material.bottomsheet.BottomSheetBehavior
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.PublishSubject
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.google.android.material.bottomsheet.BottomSheetBehavior.BottomSheetCallback as BottomSheetCallback1


class ChatListFragment : Fragment() {

    private lateinit var chatListViewModel: ChatListViewModel

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var back_dim_layout: RelativeLayout
        lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>
        lateinit var bottomSheetView: ConstraintLayout
        lateinit var db: AppDataBase
    }

    var firstVisibleInListview: Int = 0
    var lastVisibleInListview: Int = 0

    var lastLoadedInListview: Int = 30

    var scrollLoadingChannel: PublishSubject<Int> = PublishSubject.create()
    lateinit var recyclerView_: RecyclerView

    @DelicateCoroutinesApi
    @SuppressLint("UseCompatLoadingForDrawables", "ResourceAsColor")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        chatListViewModel = ViewModelProvider(this).get(ChatListViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_chat_list, container, false)

        val v: View = layoutInflater.inflate(R.layout.activity_main_toolbar_mine_view, null)
        //toolbar
//        val  toolbar_view_textview: TextView = v.findViewById(R.id.textview_toolbar)
//        MainAct.toolbar.removeAllViews()
//        MainAct.toolbar.addView(v)
//        MainAct.toolbar.title =null
//        MainAct.toolbar.setBackgroundColor(R.color.white)

//        MainAct.toolbar.title =null
//        val  textviewToolbar: TextView =  requireActivity().findViewById(R.id.toolbar_title)
//        textviewToolbar.text  = "Chats"
//
//        MainAct.toolbar.setNavigationIcon(R.drawable.ic_baseline_navigate_before_24)
//
        val call: Call<List<ChatModel>> = RetrofitClientInstance.service.getChats()

        call.enqueue(object : Callback<List<ChatModel>> {
            override fun onResponse(
                call: Call<List<ChatModel>>,
                response: Response<List<ChatModel>>
            ) =
                loadChats(call, response, root)

            override fun onFailure(call: Call<List<ChatModel>>, t: Throwable) {
                Toast.makeText(
                    context,
                    "Something went wrong...Please try later!",
                    Toast.LENGTH_SHORT
                ).show()

            }
        })

        //создание затемнение при появлении bottomSheetView
        back_dim_layout = root.findViewById(R.id.bac_dim_layout)
        back_dim_layout.setOnClickListener {
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
            back_dim_layout.visibility = View.GONE
            RecyclerAdapterPhotosList.isActive = true
        }


        //  recycleview для bottomSheet
        val recyclerViewBottomSheet: RecyclerView = root.findViewById(R.id.recyclerView_bottom_sheet)
        recyclerViewBottomSheet.layoutManager = LinearLayoutManager(context)
        recyclerViewBottomSheet.adapter = RecyclerAdapterPhotosList(fillRecycleBottomSheetList())

        val goToProfileBtn : Button = root.findViewById(R.id.go_to_profile_btn)
        goToProfileBtn.setOnClickListener {
            NavigationController().goToFragment(
                R.id.action_navigation_dashboard_to_navigation_other_profile,
                root,null
            )

        }
        //action_navigation_dashboard_to_navigation_other_profile


        bottomSheetView = root.findViewById(R.id.bottomSheet)
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheetView)
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN

        val bottomSheetCallback = object : BottomSheetCallback1() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                    back_dim_layout.visibility = View.GONE
                    RecyclerAdapterPhotosList.isActive = true
                    Log.i("TAG", "onStateChanged: ${RecyclerAdapterPhotosList.isActive}")
                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) = Unit
        }
        bottomSheetBehavior.addBottomSheetCallback(bottomSheetCallback)

        //enable smooth scrolling
        val mgScrollView = root.findViewById(R.id.nested_scroll_view) as NestedScrollView
        mgScrollView.isSmoothScrollingEnabled = true


     //   createStringSubscriber()

        return root
    }   private fun loadChats(call: Call<List<ChatModel>>, response: Response<List<ChatModel>>, v: View?) {

        //создание recyclerView
        recyclerView_ = v!!.findViewById(R.id.recyclerView)
        recyclerView_.layoutManager = LinearLayoutManager(context)
        recyclerView_.adapter = RecyclerAdapterChatList(response.body() as ArrayList<ChatModel>)


        //divider
        val dividerItemDecoration = DividerItemDecoration(context, RecyclerView.VERTICAL)
        dividerItemDecoration.setDrawable(resources.getDrawable(R.drawable.divider_drawable))
        recyclerView_.addItemDecoration(dividerItemDecoration)

        //scrool to position 0
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
                if (currentLastVisible == lastLoadedInListview) scrollLoadingChannel.onNext(lastLoadedInListview)

                firstVisibleInListview = currentFirstVisible
                lastVisibleInListview = currentLastVisible
            }
        })

    }

    private fun fillRecycleBottomSheetList(): ArrayList<ArrayList<Int>> {
        val dat = ArrayList<ArrayList<Int>>()
        for (it in 0..5) {
            val d = ArrayList<Int>()
            d.add(R.drawable.ic_launcher_foreground)
            d.add(R.drawable.ic_launcher_foreground)
            d.add(R.drawable.ic_launcher_foreground)
            dat.add(d)
        }
        Log.i("TAG", "fillList1:sdf $dat ")
        return dat
    }

    @DelicateCoroutinesApi
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

//        val toLoadingChannelSubscriber: io.reactivex.rxjava3.core.Observer<List<ChatModel>> = object :
//            io.reactivex.rxjava3.core.Observer<List<ChatModel>> {
//            override fun onSubscribe(d: Disposable) {
//            }
//
//            override fun onComplete() {
//            }
//
//            override fun onNext(t: List<ChatModel>) {
//                val ad = recyclerView_.adapter as RecyclerAdapterChatList
//                ad.addNewRandomItems(t)
//                ad.notifyItemInserted(ad.itemCount - 9)
//                lastLoadedInListview += 10
//                ad.notifyDataSetChanged()
//                createStringSubscriber()
//            }
//
//            override fun onError(e: Throwable) {
//
//            }
//        }
//
//        DataService().dataChats(lastloaded).subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread()).subscribe(toLoadingChannelSubscriber)
    }


    @DelicateCoroutinesApi
    private fun fillRecycleList(): List<Message> {
        val dat = mutableListOf<Message>()

        runBlocking {
            val job = GlobalScope.launch(Dispatchers.Default) {

                MainAct.getInstance(requireContext()).MessagesDao().insert(Message(0, 1, 1, "Saved"))
                MainAct.getInstance(requireContext()).MessagesDao().insert(Message(2, 1, 1, "Анонимусы"))
                MainAct.getInstance(requireContext()).MessagesDao().insert(Message(3, 1, 1, "Информмационка 201-352"))
                MainAct.getInstance(requireContext()).MessagesDao().insert(Message(4, 1, 1, "James"))
                MainAct.getInstance(requireContext()).MessagesDao().insert(Message(5, 1, 1, "Will Kenny"))
                MainAct.getInstance(requireContext()).MessagesDao().insert(Message(6, 1, 1, "Beth Williams"))
                MainAct.getInstance(requireContext()).MessagesDao().insert(Message(7, 1, 1, "Rev Shawn"))
                MainAct.getInstance(requireContext()).MessagesDao().insert(Message(1, 1, 1, "old School"))
                MainAct.getInstance(requireContext()).MessagesDao().insert(Message(8, 1, 1, "япы"))
                MainAct.getInstance(requireContext()).MessagesDao().insert(Message(9, 1, 1, "Александр Беседин"))
                MainAct.getInstance(requireContext()).MessagesDao().insert(Message(10, 1, 1, "Банда Looking Rooms"))
                MainAct.getInstance(requireContext()).MessagesDao().insert(Message(11, 1, 1, "Ася Санжеева"))
                MainAct.getInstance(requireContext()).MessagesDao().insert(Message(12, 1, 1, "Леонардо Дайвинчик"))
                MainAct.getInstance(requireContext()).MessagesDao().insert(Message(13, 1, 1, "Георгий Мелихов"))
                MainAct.getInstance(requireContext()).MessagesDao().insert(Message(14, 1, 1, "Хоровой чат"))
                MainAct.getInstance(requireContext()).MessagesDao().insert(Message(15, 1, 1, "Матвей Гребенников"))
                MainAct.getInstance(requireContext()).MessagesDao().insert(Message(16, 1, 1, "Никита Лавлинский"))
                MainAct.getInstance(requireContext()).MessagesDao().insert(Message(17, 1, 1, "Тимофей Щипунов"))
                MainAct.getInstance(requireContext()).MessagesDao().insert(Message(18, 1, 1, "ФЛУДИЛЬНАЯ 201-351,352"))
                MainAct.getInstance(requireContext()).MessagesDao().insert(Message(21, 1, 1, "Ксения Фокина"))

                (0..30).forEach { i ->
                    dat.add(MainAct.getInstance(requireContext()).MessagesDao().getMessagesfromUserChat(1, 1, i))
                }
            }
            job.join()
        }
        Log.i("TAG", "fillList: -")
        return dat
    }
}