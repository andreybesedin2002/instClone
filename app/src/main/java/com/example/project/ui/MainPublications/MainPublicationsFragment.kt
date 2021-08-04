package com.example.project.ui.MainPublications

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.project.*
import com.example.project.REST.GetDataService
import com.example.project.REST.MainPublicationModel
import com.example.project.REST.MessageMedel
import com.example.project.REST.RetrofitClientInstance.retrofitInstance
import com.google.android.material.bottomsheet.BottomSheetBehavior
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


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

        MainAct.navView.visibility = View.VISIBLE
        //val recyclerView_: RecyclerView = root.findViewById(R.id.recyclerView_home)
//                recyclerView_.layoutManager = LinearLayoutManager(context)
//                recyclerView_.adapter = RecyclerAdapterMainPublications(fillList_() as ArrayList<MainPublicationModel>)

//
        val service = retrofitInstance!!.create(
            GetDataService::class.java
        )
        val call: Call<List<MainPublicationModel>> = service.getMainPuplications()
        //Log.i("TAG", "onCreateView: ${call.execute()}")

        call.enqueue(object : Callback<List<MainPublicationModel>> {

            override fun onResponse(
                call: Call<List<MainPublicationModel>>,
                response: Response<List<MainPublicationModel>>
            ) {
                Log.i("TAG", "onResponse: ${response.body()}")
                val recyclerView_: RecyclerView = root.findViewById(R.id.recyclerView_home)
                recyclerView_.layoutManager = LinearLayoutManager(context)
                recyclerView_.adapter =
                    RecyclerAdapterMainPublications(response.body() as ArrayList<MainPublicationModel>)

            }

            override fun onFailure(call: Call<List<MainPublicationModel>>, t: Throwable) {
                Log.i("TAG", "onFailure: $t ")
                Toast.makeText(
                    context,
                    "Something went wrong...Please try later!",
                    Toast.LENGTH_SHORT
                ).show()

            }


        })

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

        val recyclerView_bottom_sheet: RecyclerView =
            view.findViewById(R.id.recyclerView_bottom_sheet_share)
        recyclerView_bottom_sheet.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL,
            true
        )
        recyclerView_bottom_sheet.adapter =
            RecyclerAdapterShare(fillList() as java.util.ArrayList<String>)
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
    private fun fillList_(): List<MainPublicationModel> {
        val dat = mutableListOf<MainPublicationModel>()
        (0..15).forEach { i ->
            Log.i("TAG", "load String : $i")
            dat.add(MainPublicationModel("1323",1,1,"wfwer","ds","6"))
        }
        Log.i("TAG", "fillList: ---")
        return dat
    }
}


