package com.example.project.ui.Comments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.project.DB.Comments.Comment
import com.example.project.DB.Comments.ReplyComment
import com.example.project.R
import com.example.project.RecyclerAdapterComments
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import java.util.*

class CommentsFragment : Fragment() {

    companion object {
        fun newInstance() = CommentsFragment()

        @SuppressLint("StaticFieldLeak")
        var inputField: EditText? = null

        @SuppressLint("StaticFieldLeak")
        var sendBtn: Button? = null

    }

    private lateinit var viewModel: CommentsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_comments, container, false)
        val recyclerView_: RecyclerView = root.findViewById(R.id.recyclerview_comments)
        recyclerView_.layoutManager = LinearLayoutManager(context)
        recyclerView_.adapter = RecyclerAdapterComments(fillList() as ArrayList<Comment>, this)
        inputField = root.findViewById(R.id.input_filed)
        sendBtn = root.findViewById(R.id.sendBtn)
        sendBtn!!.setOnClickListener {
            if((recyclerView_.adapter as RecyclerAdapterComments).replyPosition!=null){
                Log.i("TAG", "addReply _ : ${(recyclerView_.adapter as RecyclerAdapterComments).replyPosition!!}")




                (recyclerView_.adapter as RecyclerAdapterComments).addReply(
                    (recyclerView_.adapter as RecyclerAdapterComments).replyPosition,
                    inputField!!.text.toString(),
                    recyclerView_.findViewHolderForAdapterPosition((recyclerView_.adapter as RecyclerAdapterComments).replyPosition!!)
                )





                recyclerView_.findViewHolderForAdapterPosition((recyclerView_.adapter as RecyclerAdapterComments).replyPosition!!)
            }

            Log.i("TAG", "onCreateView: fgcvhbjklhgfcvbn")
            (recyclerView_.adapter as RecyclerAdapterComments).addComment(inputField!!.text.toString())
            (recyclerView_).scrollToPosition((recyclerView_.adapter as RecyclerAdapterComments).itemCount - 1)
        }
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CommentsViewModel::class.java)
    }

    private fun fillList(): List<com.example.project.DB.Comments.Comment> {
        val l = runBlocking {
            val list_ = GlobalScope.async {
                //   return@async MainAct.getInstance(requireContext()).CommentDao().getAllCommetsPost()
                val list = mutableListOf<Comment>()
                for (j in 0..5) {
                    val replyCommentsList = mutableListOf<ReplyComment>()
                    for (i in 0..2) {
                        replyCommentsList.add(ReplyComment(1, 1, 1, "reply$i", 5))
                    }
                    list.add(
                        Comment(1, 1, "12:32", 0, 1, "bdfgbfgdbgd", 5, replyCommentsList)
                    )
                }
                return@async list
            }.await()
            return@runBlocking list_
        }
        Log.i("TAG", "fillList: ${l}")
        return l
    }

    fun createComment(data_comment: String): Comment {
        return Comment(1, 1, "12:32", 0, 1, data_comment, 5, null)
    }

    fun setDataToEditText(data: String) {
        inputField!!.setText(data.toCharArray(), 0, data.length)
        //  inputField!!.setText(R.string.app_name)

    }

}