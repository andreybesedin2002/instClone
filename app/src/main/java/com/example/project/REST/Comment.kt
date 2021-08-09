package com.example.project.REST

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.project.R
import com.example.project.RecyclerAdapterComments
import com.example.project.RecyclerAdapterReplyComments

open class Comment() {
    var recycleComment: RecyclerAdapterComments? = null
    var recycleReplyComment:RecyclerAdapterReplyComments? = null
   // var recycl RecyclerAdapterComments? = null
    var position: Int? = null
    var view: RecyclerAdapterComments.MyViewHolder? = null
    fun getCommentsModel(): CommentsModel {
        return recycleComment!!.names!![position!!]
    }


    fun addReply(reply: Comment) {


        val recyclerView_: RecyclerView = view!!.itemView.findViewById(R.id.recycler_reply)
        Log.i("TAG", "addReply: ${recyclerView_.layoutManager}")
        recyclerView_.layoutManager = LinearLayoutManager(view!!.itemView.context)
        recyclerView_.adapter =
            RecyclerAdapterReplyComments(listOf(reply) as ArrayList<ReplyCommentModel>)
        Log.i("TAG", "addReply: add first new reply ")

        //else {
        //   if (getCommentsModel().replies == null) {
//                if (getCommentsModel().replies!!.isNotEmpty()) {
//                    val recyclerView_: RecyclerView = view!!.itemView.findViewById(R.id.recycler_reply)
//                    recyclerView_.layoutManager = LinearLayoutManager(view!!.itemView.context)
//                    recyclerView_.adapter = RecyclerAdapterReplyComments(getCommentsModel().replies as ArrayList<ReplyCommentModel>)
//                }
        //     Log.i("TAG", "addReply: add first new reply ")
        //      } else {
        //        Log.i("TAG", "addReply: add not first  new reply ")

        //        }
    }


//    fun addReplysToReply(replys: ArrayList<Comment>?) {
//        Log.i("TAG", "addReplys: ijh")
//        Log.i(
//            "TAG", "addReplys: ijh"
//        )
//
//
//            val recyclerView_: RecyclerView = view!!.itemView.findViewById(R.id.recycler_reply)
//
//            recyclerView_.layoutManager = LinearLayoutManager(view!!.itemView.context)
//            recyclerView_.adapter =
//                RecyclerAdapterReplyComments(listOf(replys) as ArrayList<ReplyCommentModel>)
//            Log.i("TAG", "addReply: add first new reply ")
//
//        }

    fun addReplysToComment(
        replys: ArrayList<ReplyCommentModel>?,
        commentView: View?,
        commentModel: CommentsModel
    ) {
        Log.i("TAG", "addReplys: ijh")
        val recyclerView_: RecyclerView = commentView!!.findViewById(R.id.recycler_reply)
        recyclerView_.layoutManager = LinearLayoutManager(commentView!!.context)
        recyclerView_.adapter =
            replys?.let { RecyclerAdapterReplyComments(it)}
        commentModel.comment!!.recycleReplyComment =  recyclerView_.adapter as RecyclerAdapterReplyComments
        Log.i("TAG", "addReply: add first new reply ")

    }
}