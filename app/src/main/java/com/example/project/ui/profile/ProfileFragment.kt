package com.example.project.ui.profile

import android.graphics.Typeface
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.project.R
import com.example.project.RecyclerAdapterPhotosList
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation


class ProfileFragment : Fragment() {
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>
    private lateinit var recyclerView_: RecyclerView
    private lateinit var notificationsViewModel: ProfileViewModel
    private lateinit var bottomSheetView: ConstraintLayout
    private lateinit var back_dim_layout: RelativeLayout
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        notificationsViewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_profile, container, false)

        recyclerView_ = root.findViewById(R.id.recyclerView)
        recyclerView_.layoutManager = LinearLayoutManager(context)
        recyclerView_.adapter = RecyclerAdapterPhotosList(fillList1())

        //
        val imgView: ImageView = root.findViewById(R.id.profileIV)
        val name: TextView = root.findViewById(R.id.name_profile)
        val location: TextView = root.findViewById(R.id.lacation_user)

        val tf = Typeface.createFromAsset(
            requireContext().assets,
            "fronts/Comfortaa-VariableFont_wght.ttf"
        )

        name.typeface = tf
        location.typeface = tf

        Picasso.get()
            .load("https://c.wallhere.com/photos/68/15/1600x1200_px_animal_cute_dog_dogs_Frendly_Pet-1642413.jpg!d")
            .transform(CropCircleTransformation())
            .error(R.drawable.ic_launcher_foreground)
            .into(imgView)

        return root
    }

    private fun fillList1(): ArrayList<ArrayList<Int>> {
        val dat = ArrayList<ArrayList<Int>>()
        (0..15).forEach { i ->
            Log.i("TAG", "fillList1: add new photos $i")
            val d = ArrayList<Int>()
            d.add(R.drawable.ic_launcher_foreground)
            d.add(R.drawable.ic_launcher_foreground)
            d.add(R.drawable.ic_launcher_foreground)
            dat.add(d)
        }
        Log.i("TAG", "fillList1:sdf $dat ")
        return dat
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        back_dim_layout = view.findViewById(R.id.bac_dim_layout)
        back_dim_layout.setOnClickListener {
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
            back_dim_layout.visibility = View.GONE

        }

        bottomSheetView = view.findViewById(R.id.bottom_sheet_profile)

        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheetView)
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN

        val bottomSheetCallback = object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                Log.i(
                    "TAG",
                    "onStateChanged: ${newState}, ${BottomSheetBehavior.STATE_HALF_EXPANDED} , ${BottomSheetBehavior.STATE_HIDDEN}"
                )
                if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                    back_dim_layout.visibility = View.GONE
                    Log.i("TAG", "onStateChanged: ${RecyclerAdapterPhotosList.isActive}")
                }
                if (newState == 4) {
                    bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN

                }

            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) = Unit
        }
        bottomSheetBehavior.addBottomSheetCallback(bottomSheetCallback)


        val change_profile: Button = view.findViewById(R.id.change_profile_btn)
        change_profile.setOnClickListener {
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
            back_dim_layout.visibility = View.VISIBLE
//
//            val anim = ObjectAnimator.ofFloat(back_dim_layout, "alpha", 1.0f,0.0f)
//
//            anim.duration = 3000 // duration 3 seconds
//
//            anim.start()
        }

        val mgScrollView = view.findViewById(R.id.scroll) as NestedScrollView
        mgScrollView.isSmoothScrollingEnabled = true

    }
}