package com.example.project

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.project.Objects.Like
import com.example.project.REST.Comment
import com.example.project.REST.CommentsModel
import com.example.project.REST.ReplyCommentModel
import com.example.project.ui.Comments.CommentsFragment
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation
import kotlinx.coroutines.*
import java.util.*


class RecyclerAdapterReplyComments(val names: ArrayList<ReplyCommentModel>) :
    RecyclerView.Adapter<RecyclerAdapterReplyComments.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var img: ImageView? = null
        var likes: ImageView? = null
        var like_comment: Like? = null
        var num_likes: TextView? = null
        var time: TextView? = null
        var text: TextView? = null
        var answer: TextView? = null

        init {
            img = itemView.findViewById(R.id.img_user)
            likes = itemView.findViewById(R.id.likes)
            like_comment = Like(likes!!)
            num_likes = itemView.findViewById(R.id.num_likes)
            text = itemView.findViewById(R.id.comment_data)
            time = itemView.findViewById(R.id.time)
            answer = itemView.findViewById(R.id.answer)
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

        names!![position].comment = Comment()
        val comment: Comment = names!![position].comment!!
        val replyCommentModel: ReplyCommentModel = names!![position]

        Picasso.get()
            .load("https://c.wallhere.com/photos/68/15/1600x1200_px_animal_cute_dog_dogs_Frendly_Pet-1642413.jpg!d")
            .transform( CropCircleTransformation())
            .error(R.drawable.ic_launcher_foreground)
            .into(holder.img)
        holder.text!!.text = names[position].userId + ", " + names[position].time
        holder.num_likes!!.text = names[position].likes.toString()
        holder.text!!.text = names[position].text


        holder.like_comment!!.setOnClickListener {
            holder.num_likes!!.text = (5).toString()
        }
        holder.answer!!.setOnClickListener {
            CommentsFragment.commentEditText.replyedReplyComment = replyCommentModel
        }
    }

    override fun getItemCount(): Int = names.size
}



















