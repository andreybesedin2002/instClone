package com.example.project

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.project.DB.Comments.ReplyComment
import com.example.project.Objects.Like
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation
import kotlinx.coroutines.*
import java.util.*


class RecyclerAdapterReplyComments(private val names: ArrayList<ReplyComment>) :
    RecyclerView.Adapter<RecyclerAdapterReplyComments.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var img: ImageView? = null
        var likes: ImageView? = null
        var like_comment: Like? = null
        var num_likes: TextView? = null
        var data_replyComment: TextView? = null

        init {
            img = itemView.findViewById(R.id.img_user)
            likes = itemView.findViewById(R.id.likes)
            like_comment = Like(img = likes!!)
            num_likes = itemView.findViewById(R.id.num_likes)
            data_replyComment = itemView.findViewById(R.id.comment_data)
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
        holder.data_replyComment!!.text = names[position].idIser.toString()+", " + names[position].dataComment
        holder.like_comment!!.setOnClickListener {
            holder.num_likes!!.text = (5).toString()
        }
    }

    override fun getItemCount(): Int = names.size
}



















