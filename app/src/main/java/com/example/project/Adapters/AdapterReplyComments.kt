package com.example.project

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.project.DB.Comments.ReplyComment
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation
import kotlinx.coroutines.*
import java.util.*


class RecyclerAdapterReplyComments(private val names: ArrayList<ReplyComment>) :
    RecyclerView.Adapter<RecyclerAdapterReplyComments.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var img: ImageView? = null

        init {
            img = itemView.findViewById(R.id.img_user)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycleview_item_reply_comments, parent, false)

        return MyViewHolder(itemView)
    }


    @DelicateCoroutinesApi
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        Picasso.get()
            .load("https://c.wallhere.com/photos/68/15/1600x1200_px_animal_cute_dog_dogs_Frendly_Pet-1642413.jpg!d")
            .transform( CropCircleTransformation())
            .error(R.drawable.ic_launcher_foreground)
            .into(holder.img)

    }

    override fun getItemCount(): Int = names.size
}



















