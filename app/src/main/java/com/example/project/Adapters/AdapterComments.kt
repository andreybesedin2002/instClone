package com.example.project

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.project.Objects.Like
import com.example.project.REST.Comment
import com.example.project.REST.CommentsModel
import com.example.project.ui.Comments.CommentsFragment
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlin.collections.ArrayList


class RecyclerAdapterComments(
     val names: ArrayList<CommentsModel>?,
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
        var text: TextView? = null
        var answer: TextView? = null

        init {
            username = itemView.findViewById(R.id.name_user)
            time = itemView.findViewById(R.id.time)
            text = itemView.findViewById(R.id.comment_data)
            answer = itemView.findViewById(R.id.answer)
            num_likes = itemView.findViewById(R.id.num_likes)
            likes = itemView.findViewById(R.id.likes)
            img = itemView.findViewById(R.id.img_user)
            like_comment = Like(img = likes!!)

        }
    }

    var replyPosition: Int? = null

    fun addComment(comment: CommentsModel) {
        Log.i("TAG", "addComment: ${comment.replies}")
        names!!.add(comment)

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
        Log.i("TAG", "addRs: ijh 1 $position ${names!![position].replies}")
        names!![position].comment = Comment()
        val comment: Comment = names!![position].comment!!
        val commentModel: CommentsModel = names!![position]
        Log.i("TAG", "addRs: ijh 2 $position ${commentModel.replies}")

        Picasso.get()
            .load("https://c.wallhere.com/photos/68/15/1600x1200_px_animal_cute_dog_dogs_Frendly_Pet-1642413.jpg!d")
            .transform(CropCircleTransformation())
            .error(R.drawable.ic_launcher_foreground)
            .into(holder.img)

        comment.view = holder
        comment.position = position
        comment.recycleComment = this

        holder.username!!.text = commentModel.userId
        holder.time!!.text = commentModel.data
        holder.num_likes!!.text = commentModel.likes.toString()
        holder.text!!.text = commentModel.text


        holder.answer!!.setOnClickListener {
            CommentsFragment.commentEditText.replyedComment = commentModel
        }

        holder.like_comment!!.setOnClickListener {
            commentModel.likes = commentModel.likes + 1
            holder.num_likes!!.text = commentModel.likes.toString()
            holder.likes!!.setBackgroundResource(R.mipmap.ic_launcher_foreground__)
        }

        holder.text!!.setOnClickListener {
            CommentsFragment.commentEditText.addReferenceToReply(names[position].text + ", ")
        }

        Log.i("TAG", "addRs: ijh 3 $position ${commentModel.replies}")
        Log.i("TAG", "addRs: ijh 4 $position ${names!![position].replies}")
        if (commentModel.replies != null) {
            if (commentModel.replies!!.isNotEmpty()) {
                Log.i("TAG", "addReplys: ijh $position ${commentModel.data}")
                         comment.addReplysToComment(commentModel.replies ,holder.itemView,commentModel)
            }
        }
//                val recyclerView_: RecyclerView = holder.itemView.findViewById(R.id.recycler_reply)
//                recyclerView_.layoutManager = LinearLayoutManager(holder.itemView.context)
//                recyclerView_.adapter =
//                    RecyclerAdapterReplyComments(names[position].replies as ArrayList<ReplyCommentModel>)
    }


    override fun getItemCount(): Int = names!!.size

//    fun addReply(position: Int?, text: String, findViewHolderForAdapterPosition: RecyclerView.ViewHolder?) {
//        Log.i("TAG", "addReply: $findViewHolderForAdapterPosition")
//        val recyclerView_: RecyclerView =
//            findViewHolderForAdapterPosition!!.itemView.findViewById(R.id.recycler_reply)
//
//        if (names!![position!!].replies!!.isEmpty()) {
//            recyclerView_.layoutManager =
//                LinearLayoutManager(findViewHolderForAdapterPosition.itemView.context)
//            recyclerView_.adapter =
//                RecyclerAdapterReplyComments(listOf(ReplyComment(1, 1, 1, text, 5))as ArrayList<ReplyComment>)
//        }
//        else{
//            Log.i("TAG", "addReply: add second or more new reply")
//        }
//    }
}



















