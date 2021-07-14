package com.example.project.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.project.MainAct
import com.example.project.R
import com.example.project.RecyclerAdapterMainPublications
import com.example.project.RecyclerAdapterPublications
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment() {

    private lateinit var recyclerView_: RecyclerView
    private lateinit var homeViewModel: HomeViewModel

    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
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