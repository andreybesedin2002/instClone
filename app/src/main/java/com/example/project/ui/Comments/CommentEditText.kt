package com.example.project.ui.Comments

import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.project.R
import com.example.project.REST.CommentsModel
import com.example.project.REST.ReplyCommentModel
import com.example.project.RecyclerAdapterComments
import com.example.project.RecyclerAdapterReplyComments

class CommentEditText(v: View, idET: Int, idSB: Int, var recycle: RecyclerAdapterComments? = null) {
    var inputField: EditText? = null
    var sendBtn: Button? = null
    var replyedComment: CommentsModel? = null
    var replyedReplyComment: ReplyCommentModel? = null

    init {
        inputField = v.findViewById(idET)
        sendBtn = v.findViewById(idSB)

        sendBtn!!.setOnClickListener {
            if (replyedComment != null) {
                Log.i("TAG", "reply to comment: ")
                sendReplyComment(replyedComment!!,ReplyCommentModel(1, 1, "1", "5", "12:43", "1", getText()))
                replyedComment = null
            } else {
                if (replyedReplyComment != null) {
                    Log.i("TAG", "reply to reply: ")
                    sendReplyReplyedComment()
                    replyedReplyComment = null

                } else {
                    Log.i("TAG", "reply to post: ")
                    sendCommentToPost()
                }
            }


//            if((CommentsFragment.recyclerView_.adapter as RecyclerAdapterComments).replyPosition!=null){
//
//                (CommentsFragment.recyclerView_.adapter as RecyclerAdapterComments).addReply(
//                    (CommentsFragment.recyclerView_.adapter as RecyclerAdapterComments).replyPosition,
//                    inputField!!.text.toString(),
//                    CommentsFragment.recyclerView_.findViewHolderForAdapterPosition((CommentsFragment.recyclerView_.adapter as RecyclerAdapterComments).replyPosition!!)
//                )
//                CommentsFragment.recyclerView_.findViewHolderForAdapterPosition((CommentsFragment.recyclerView_.adapter as RecyclerAdapterComments).replyPosition!!)
//            }
//
//            Log.i("TAG", "onCreateView: fgcvhbjklhgfcvbn")
//            (CommentsFragment.recyclerView_.adapter as RecyclerAdapterComments).addComment(inputField!!.text.toString())
//            (CommentsFragment.recyclerView_).scrollToPosition((CommentsFragment.recyclerView_.adapter as RecyclerAdapterComments).itemCount - 1)


        }
    }

    private fun sendReplyReplyedComment() {

    }

    private fun sendCommentToPost() {
        recycle!!.addComment(CommentsModel(1, 1, "1", "5", "67843", "1", getText(), null))
    }

    private fun sendReplyComment(comment: CommentsModel, replyComment: ReplyCommentModel) {
        if (comment.replies == null) {
            comment.replies =arrayListOf(replyComment)
            Log.i("TAG", "sendReplyComment: ${comment.replies }")
            Log.i("TAG", "sendReplyComment: ${comment.replies!![0]}")

            val recyclerView_: RecyclerView = comment.comment!!.view!!.itemView.findViewById(R.id.recycler_reply)
            recyclerView_.layoutManager = LinearLayoutManager(comment.comment!!.view!!.itemView.context)
            recyclerView_.adapter = RecyclerAdapterReplyComments(comment.replies!!)
            comment.comment!!.recycleReplyComment = recyclerView_.adapter as RecyclerAdapterReplyComments
        } else {
            if (comment.replies!!.isEmpty()) {
                val recyclerView_: RecyclerView = comment.comment!!.view!!.itemView.findViewById(R.id.recycler_reply)
                recyclerView_.layoutManager = LinearLayoutManager(comment.comment!!.view!!.itemView.context)
                recyclerView_.adapter = RecyclerAdapterReplyComments(listOf(replyComment) as ArrayList<ReplyCommentModel>)
                comment.comment!!.recycleReplyComment = recyclerView_.adapter as RecyclerAdapterReplyComments

            } else {
                Log.i("TAG", "_+_ ${comment }")
                Log.i("TAG", "_+_ ${comment.comment }")
                Log.i("TAG", "_+_ ${comment.comment!!.recycleReplyComment }")
                Log.i("TAG", "_+_ ${comment.comment!!.recycleReplyComment!!.names }")
                comment.comment!!.recycleReplyComment!!.names.add(replyComment)
                comment.comment!!.recycleReplyComment!!.notifyItemInserted(comment.comment!!.recycleReplyComment!!.itemCount - 1)
                comment.comment!!.recycleReplyComment!!.notifyDataSetChanged()

                comment.replies!!.add(replyComment)
            }
        }


//            if(replyedComment is ReplyCommentModel) {
//                replyedComment!!.addReply(ReplyCommentModel())
//            }
//            else{
        //  replyedComment!!.addReply(CommentsModel())
    }
//        (
//                (CommentsFragment.recyclerView_.adapter as RecyclerAdapterComments).replyPosition,
//        CommentsFragment.inputField!!.text.toString(),
//        CommentsFragment.recyclerView_.findViewHolderForAdapterPosition((CommentsFragment.recyclerView_.adapter as RecyclerAdapterComments).replyPosition!!)
//        )
//        CommentsFragment.recyclerView_.findViewHolderForAdapterPosition((CommentsFragment.recyclerView_.adapter as RecyclerAdapterComments).replyPosition!!)
//
//
//        Log.i("TAG", "onCreateView: fgcvhbjklhgfcvbn")
//        (CommentsFragment.recyclerView_.adapter as RecyclerAdapterComments).addComment(
//            CommentsFragment.inputField!!.text.toString()
//        )
//        (CommentsFragment.recyclerView_).scrollToPosition((CommentsFragment.recyclerView_.adapter as RecyclerAdapterComments).itemCount - 1)
//        (CommentsFragment.recyclerView_.adapter as RecyclerAdapterComments).addComment(inputField!!.text.toString())


    fun addReferenceToReply(reference: String) {

        inputField!!.setText(reference.toCharArray(), 0, reference.length)

    }

    fun getText(): String {
        return inputField!!.text.toString()
    }
}




