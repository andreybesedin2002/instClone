package com.example.project

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.project.DB.Comments.Comment
import com.example.project.DB.Comments.ReplyComment
import com.example.project.Objects.Like
import com.example.project.ui.Comments.CommentsFragment
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation
import kotlinx.coroutines.DelicateCoroutinesApi
import java.util.*


class RecyclerAdapterComments(
    private val names: ArrayList<Comment>,
    private var parentFragment: CommentsFragment
) :
    RecyclerView.Adapter<RecyclerAdapterComments.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var num_likes: TextView? = null
        var likes: ImageView? = null
        var like_comment: Like? = null
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
            like_comment = Like(img = likes!!)

        }
    }

    var replyPosition: Int? = null

    fun addComment(t: String) {
        names.add(CommentsFragment().createComment(t))
        notifyItemInserted(this.itemCount - 1)
        notifyDataSetChanged()

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


        holder.like_comment!!.setOnClickListener {
            names[position].likes = names[position].likes + 1
            holder.num_likes!!.text = names[position].likes.toString()
            holder.likes!!.setBackgroundResource(R.mipmap.ic_launcher_foreground__)
        }
        holder.data_comment!!.setOnClickListener {
            parentFragment.setDataToEditText(names[position].dataComment + ", ")
            replyPosition = position
        }
        if(names[position].replies!=null) {
            if (names[position].replies!!.isNotEmpty()) {
                val recyclerView_: RecyclerView = holder.itemView.findViewById(R.id.recycler_reply)
                recyclerView_.layoutManager = LinearLayoutManager(holder.itemView.context)
                recyclerView_.adapter =
                    RecyclerAdapterReplyComments(names[position].replies as ArrayList<ReplyComment>)
            }
        }
    }


    override fun getItemCount(): Int = names.size

    fun addReply(position: Int?, text: String, findViewHolderForAdapterPosition: RecyclerView.ViewHolder?) {
        Log.i("TAG", "addReply: $findViewHolderForAdapterPosition")
        val recyclerView_: RecyclerView =
            findViewHolderForAdapterPosition!!.itemView.findViewById(R.id.recycler_reply)

        if (names[position!!].replies!!.isEmpty()) {
            recyclerView_.layoutManager =
                LinearLayoutManager(findViewHolderForAdapterPosition.itemView.context)
            recyclerView_.adapter =
                RecyclerAdapterReplyComments(listOf(ReplyComment(1, 1, 1, text, 5))as ArrayList<ReplyComment>)
        }
        else{
            Log.i("TAG", "addReply: add second or more new reply")
        }
    }
}



















