package com.example.project

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.project.DB.Messages.Message
import com.example.project.ui.chat_list.ChatListFragment
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation
import kotlinx.coroutines.*
import java.util.*
import kotlin.collections.ArrayList


class RecyclerAdapterPublications(private val names: ArrayList<String>) :
    RecyclerView.Adapter<RecyclerAdapterPublications.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var img: ImageView? = null
        var img_publication: ImageView? = null
        var img1: ImageView? = null
        var img2: ImageView? = null
        var img3: ImageView? = null
        init {
            img = itemView.findViewById(R.id.img)
            img_publication = itemView.findViewById(R.id.img_publication)
            img1 = itemView.findViewById(R.id.img1)
            img2 = itemView.findViewById(R.id.img2)
            img3 = itemView.findViewById(R.id.img3)

        }
    }

    fun addNewRandomItems(t: List<String>) = (0..9).forEach { i -> names.add(t[i]) }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycleview_item_publication, parent, false)

        return MyViewHolder(itemView)
    }


    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    @DelicateCoroutinesApi
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        Picasso.get()
            .load("https://c.wallhere.com/photos/68/15/1600x1200_px_animal_cute_dog_dogs_Frendly_Pet-1642413.jpg!d").error(R.drawable.ic_launcher_foreground)
            .transform( CropCircleTransformation())
            .into(holder.img)
        Picasso.get()
            .load("https://c.wallhere.com/photos/68/15/1600x1200_px_animal_cute_dog_dogs_Frendly_Pet-1642413.jpg!d").error(R.drawable.ic_launcher_foreground)
            .resize(300,300)
            .into(holder.img_publication)

        holder.img2?.setOnClickListener {
            Navigation.findNavController(holder.itemView)
                .navigate(R.id.action_navigation_publications_to_navigation_comments)


        }
        holder.img3?.setOnClickListener {



        }
    }

    override fun getItemCount(): Int = names.size
}


