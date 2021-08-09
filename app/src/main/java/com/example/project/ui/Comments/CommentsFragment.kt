package com.example.project.ui.Comments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.project.DB.Comments.Comment
import com.example.project.DB.Comments.ReplyComment
import com.example.project.R
import com.example.project.REST.CommentsModel
import com.example.project.REST.RestApi
import com.example.project.REST.RetrofitClientInstance
import com.example.project.RecyclerAdapterComments
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import retrofit2.Call
import retrofit2.Response
import java.util.*

class CommentsFragment : Fragment() {

    companion object {

        @SuppressLint("StaticFieldLeak")
        lateinit var commentEditText: CommentEditText
        lateinit var recyclerView_: RecyclerView
    }


    @SuppressLint("UseRequireInsteadOfGet")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_comments, container, false)

        val arg1Value = arguments!!.getString("arg1")
        val arg2Value = arguments!!.getInt("arg2")
        Log.i("TAG", "onCreateView: + $arg1Value")
        Log.i("TAG", "onCreateView: + $arg2Value")

        val call: Call<List<CommentsModel>> = RetrofitClientInstance.service.getComments(arg2Value)

        RestApi.run(call,
            { response: Response<List<CommentsModel>>, _: View ->
                Log.i("TAG", "onCreateView: recyclerview_comments ${response.body()}")
                recyclerView_ = root.findViewById(R.id.recyclerview_comments) as RecyclerView
                recyclerView_.layoutManager = LinearLayoutManager(context)
                recyclerView_.adapter =
                    RecyclerAdapterComments(response.body() as ArrayList<CommentsModel>?, this)
                commentEditText = CommentEditText(
                    root, R.id.input_filed, R.id.sendBtn,
                    recyclerView_.adapter as RecyclerAdapterComments
                )
            }, {}, root
        )







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

//    fun setDataToEditText(data: String) {
//        inputField!!.setText(data.toCharArray(), 0, data.length)
//        //  inputField!!.setText(R.string.app_name)
//
//    }

}