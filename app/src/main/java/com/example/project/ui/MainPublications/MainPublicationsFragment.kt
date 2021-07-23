package com.example.project.ui.MainPublications

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.project.*
import com.google.android.material.bottomsheet.BottomSheetBehavior


class MainPublicationsFragment : Fragment() {

    companion object {
        lateinit var bottomSheetView: ConstraintLayout
        lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>
        lateinit var back_dim_layout: RelativeLayout
    }


    private lateinit var recyclerView_: RecyclerView
    private lateinit var homeViewModel: MainPublicationsViewModel



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(MainPublicationsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
     //   MainAct.toolbar.title =null
//        val  textviewToolbar: TextView =  requireActivity().findViewById(R.id.toolbar_title)
//        textviewToolbar.text  = "Research"


        MainAct.navView.visibility = View.VISIBLE

        val recyclerView_: RecyclerView = root.findViewById(R.id.recyclerView_home)
        recyclerView_.layoutManager = LinearLayoutManager(context)
        recyclerView_.adapter = RecyclerAdapterMainPublications(fillList() as ArrayList<String>)

// Use this to programmatically apply behavior attributes
//        val bottomSheetCallback = object : BottomSheetBehavior.BottomSheetCallback() {
//
//            override fun onStateChanged(bottomSheet: View, newState: Int) {
//                bottomSheet.findViewById<TextView>(R.id.afds).text = "Qwr"
//
//                // Do something for new state
//            }
//
//            override fun onSlide(bottomSheet: View, slideOffset: Float) {
//                // Do something for slide offset
//            }
//        }
//        standardBottomSheetBehavior.addBottomSheetCallback(bottomSheetCallback)


//       val textView: TextView = root.findViewById(R.id.)
//        homeViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //создание затемнение при появлении bottomSheetView
        back_dim_layout = view.findViewById(R.id.bac_dim_layout_share)
        back_dim_layout.setOnClickListener {
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
            back_dim_layout.visibility = View.GONE
            RecyclerAdapterPhotosList.isActive = true
        }
        //recycleview for bottomSheet

        val recyclerView_bottom_sheet: RecyclerView = view.findViewById(R.id.recyclerView_bottom_sheet_share)
        recyclerView_bottom_sheet.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,true)
        recyclerView_bottom_sheet.adapter = RecyclerAdapterShare(fillList() as java.util.ArrayList<String>)
        recyclerView_bottom_sheet.scrollToPosition(0)

        // bottomSheet
        bottomSheetView = view.findViewById(R.id.bottom_sheet_share_)
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheetView)
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN

        val bottomSheetCallback = object : BottomSheetBehavior.BottomSheetCallback() {
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




        //   MainAct.navView.visibility = View.GONE
//        val but = view.findViewById<Button>(R.id.sd)
//        but.setOnClickListener {
//        val bottomSheetView = view.findViewById<ConstraintLayout>(R.id.bottomSheet)
//         bottomSheetBehavior = BottomSheetBehavior.from(bottomSheetView)
//        recyclerView_ = view.findViewById(R.id.recyclerView)
//
//        recyclerView_.layoutManager = LinearLayoutManager(context)
//        recyclerView_.adapter = RecyclerAdapterPhotosList(fillList() as ArrayList<String>)
//
//        val mgScrollView = view.findViewById<NestedScrollView>(R.id.dsfg) as NestedScrollView
//        mgScrollView.isSmoothScrollingEnabled = true;

        ///        val textView: TextView = root.findViewById(R.id.text_notifications)
//        notificationsViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//

//            // val metrics = resources.displayMetrics
//            //   bottomSheetBehavior.peekHeight = metrics.heightPixels / 2
//            //bottomSheetBehavior.state = BottomSheetBehavior.STATE_HALF_EXPANDED
//
//            val bottomSheetCallback = object : BottomSheetBehavior.BottomSheetCallback() {
//
//                override fun onStateChanged(bottomSheet: View, newState: Int) {
//                    // Do something for new state
//                    Log.i("TAG", "onStateChanged: $newState")
//
//                }
//
//                override fun onSlide(bottomSheet: View, slideOffset: Float) {
//                    // Do something for slide offset
//                    Log.i("TAG", "onSlide: $slideOffset")
//                }
//            }
//            bottomSheetBehavior.addBottomSheetCallback(bottomSheetCallback)
//            bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
//            bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
//        }
//

    }

    private fun fillList(): List<String> {
        val dat = mutableListOf<String>()
        (0..15).forEach { i ->
            Log.i("TAG", "load String : $i")
            dat.add("ds")
        }
        Log.i("TAG", "fillList: ---")
        return dat
    }
}