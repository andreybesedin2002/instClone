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



    @SuppressLint("UseRequireInsteadOfGet")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_comments, container, false)

        val recyclerView_: RecyclerView = root.findViewById(R.id.recyclerview_comments) as RecyclerView
        recyclerView_.layoutManager = LinearLayoutManager(context)

        val arg1Value = arguments!!.getString("arg1")
        val arg2Value = arguments!!.getInt("arg2")
        Log.i("TAG", "onCreateView: + $arg1Value")
        Log.i("TAG", "onCreateView: + $arg2Value")
        recyclerView_.adapter = RecyclerAdapterComments(
            fillList(arg1Value) as ArrayList<Comment>,
            this
        )

        inputField = root.findViewById(R.id.input_filed) as EditText?
        sendBtn = root.findViewById(R.id.sendBtn) as Button?
        sendBtn!!.setOnClickListener {
            if((recyclerView_.adapter as RecyclerAdapterComments).replyPosition!=null){

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
        }

    private fun fillList(get: String?): List<Comment> {
        Log.i("TAG", "fillList: vgbhj $get")
        val l = runBlocking {
            val list_ = GlobalScope.async {
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