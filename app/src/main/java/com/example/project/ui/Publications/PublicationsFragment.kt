package com.example.project.ui.Publications

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.project.R
import com.example.project.RecyclerAdapterPhotosList
import com.example.project.RecyclerAdapterPublications
import com.example.project.RecyclerAdapterShare
import com.example.project.ui.MainPublications.MainPublicationsFragment
import com.google.android.material.bottomsheet.BottomSheetBehavior

class PublicationsFragment : Fragment() {

    companion object {
        fun newInstance() = PublicationsFragment()
        lateinit var bottomSheetView: ConstraintLayout
        lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>
        lateinit var back_dim_layout: RelativeLayout
    }


    private lateinit var viewModel: PublicationsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root =  inflater.inflate(R.layout.fragmet_publications, container, false)
        val recyclerView_: RecyclerView = root.findViewById(R.id.recyclerview_publication)
        recyclerView_.layoutManager = LinearLayoutManager(context)
        recyclerView_.adapter = RecyclerAdapterPublications(fillList() as ArrayList<String>)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(PublicationsViewModel::class.java)
        //создание затемнение при появлении bottomSheetView
        back_dim_layout = view.findViewById(R.id.bac_dim_layout_share)
        back_dim_layout.setOnClickListener {
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
            RecyclerAdapterPhotosList.isActive = true
        }
        //recycleview for bottomSheet

        val recyclerView_bottom_sheet: RecyclerView = view.findViewById(R.id.recyclerView_bottom_sheet_share)
        recyclerView_bottom_sheet.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,true)
        recyclerView_bottom_sheet.adapter = RecyclerAdapterShare(fillList() as java.util.ArrayList<String>)
        recyclerView_bottom_sheet.scrollToPosition(0)

        // bottomSheet
        bottomSheetView = view.findViewById(R.id.bottom_sheet_share_)
        bottomSheetBehavior = BottomSheetBehavior.from(
            bottomSheetView
        )
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN

        val bottomSheetCallback = object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                    back_dim_layout.visibility = View.GONE
                    RecyclerAdapterPhotosList.isActive = true
                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) = Unit
        }

        MainPublicationsFragment.bottomSheetBehavior.addBottomSheetCallback(bottomSheetCallback)




    }

    private fun fillList(): MutableList<String> {
        val dat = mutableListOf<String>()
        (0..15).forEach { i ->
            dat.add("ds")
        }
        return dat
    }


}