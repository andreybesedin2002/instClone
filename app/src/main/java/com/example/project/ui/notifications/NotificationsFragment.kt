package com.example.project.ui.notifications

import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.project.R
import com.example.project.RecyclerAdapterPhotosList
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation
import kotlinx.android.synthetic.main.fragment_notifications.*


class NotificationsFragment : Fragment() {

    private lateinit var recyclerView_: RecyclerView
    private lateinit var notificationsViewModel: NotificationsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        notificationsViewModel = ViewModelProvider(this).get(NotificationsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_notifications, container, false)

        recyclerView_ = root.findViewById(R.id.recyclerView)
        recyclerView_.layoutManager = LinearLayoutManager(context)
        recyclerView_.adapter = RecyclerAdapterPhotosList(fillList() as ArrayList<String>)

        //
        val imgView: ImageView = root.findViewById(R.id.imageView)
        val  name: TextView =   root.findViewById(R.id.name_profile)
        val  location: TextView =   root.findViewById(R.id.lacation_user)

        val tf = Typeface.createFromAsset(
            requireContext().assets,
            "fronts/Comfortaa-VariableFont_wght.ttf"
        )

        name.typeface = tf
        location.typeface = tf

        Picasso.get()
/*
            .load(R.mipmap.ic_launcher_foreground__).transform(CropCircleTransformation()).error(R.drawable.ic_launcher_foreground)

            .into(imgView, object : Callback {
                override fun onSuccess() {
                    Log.i("WWWWWWWW", "onSuccess:$ ")
                }

                override fun onError(e: Exception?) {
                    Log.i("WWWWWWWW", "onError:$e ")
                }
            })

*/
        Picasso.get()
            .load("https://c.wallhere.com/photos/68/15/1600x1200_px_animal_cute_dog_dogs_Frendly_Pet-1642413.jpg!d")
            .transform(CropCircleTransformation())
            .error(R.drawable.ic_launcher_foreground)
            .into(imgView)

        return root
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mgScrollView = view.findViewById(R.id.scroll) as NestedScrollView
        mgScrollView.isSmoothScrollingEnabled = true;

    }
}