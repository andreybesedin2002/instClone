package com.example.project

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.project.DB.Comments.Comment
import com.example.project.DB.Comments.ReplyComment
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation
import kotlinx.coroutines.*
import java.util.*


class RecyclerAdapterComments(private val names: ArrayList<Comment>) :
    RecyclerView.Adapter<RecyclerAdapterComments.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var num_likes: TextView? = null
        var likes: ImageView? = null

        var img: ImageView? = null
        var username: TextView? = null
        var time: TextView? = null
        var data_comment: TextView? = null

        init {
            username = itemView.findViewById(R.id.name_user)
            time = itemView.findViewById(R.id.time)
            data_comment = itemView.findViewById(R.id.comment_data)
            num_likes = itemView.findViewById(R.id.num_likes)
            likes = itemView.findViewById(R.id.likes)
            img = itemView.findViewById(R.id.img_user)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycleview_item_comments, parent, false)

        return MyViewHolder(itemView)
    }


    @DelicateCoroutinesApi
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        Picasso.get()
            .load("https://c.wallhere.com/photos/68/15/1600x1200_px_animal_cute_dog_dogs_Frendly_Pet-1642413.jpg!d")
            .transform(CropCircleTransformation())
            .error(R.drawable.ic_launcher_foreground)
            .into(holder.img)

        holder.username!!.text = names[position].idIser.toString()
        holder.time!!.text = names[position].time
        holder.num_likes!!.text = names[position].likes.toString()
        holder.data_comment!!.text = names[position].dataComment

        holder.likes!!.setOnClickListener {
            names[position].likes = names[position].likes + 1

            holder.num_likes!!.text = names[position].likes.toString()
        }

        val recyclerView_: RecyclerView = holder.itemView.findViewById(R.id.recycler_reply)
        recyclerView_.layoutManager = LinearLayoutManager(holder.itemView.context)
        recyclerView_.adapter = RecyclerAdapterReplyComments(fillList() as ArrayList<ReplyComment>)

        //     names[position].replyComments.forEach
    }

    private fun fillList(): List<ReplyComment> {
//        val l = runBlocking {
//            val list_ =  GlobalScope.async {
//
//                return@async MainAct.getInstance(requireContext()).CommentDao().getAllCommetsPost()
//
//            }.await()
//            return@runBlocking list_
//        }
        val dat = mutableListOf<ReplyComment>()
        dat.add(ReplyComment(1, 1, 1, "reply1", 5))
        dat.add(ReplyComment(2, 1, 1, "reply2", 5))
        dat.add(ReplyComment(3, 1, 2, "reply3", 5))



        return dat
    }

    override fun getItemCount(): Int = names.size
}



















