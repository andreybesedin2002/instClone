package com.example.project.ui.Comments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.project.DB.Comments.Comment
import com.example.project.MainAct
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

    }

    private lateinit var viewModel: CommentsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_comments, container, false)
        val recyclerView_: RecyclerView = root.findViewById(R.id.recyclerview_comments)
        recyclerView_.layoutManager = LinearLayoutManager(context)
        recyclerView_.adapter = RecyclerAdapterComments(fillList() as ArrayList<Comment>,this)
        inputField = root.findViewById(R.id.input_filed)

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CommentsViewModel::class.java)
    }

    private fun fillList(): List<Comment> {
        val l = runBlocking {
            val list_ = GlobalScope.async {

                return@async MainAct.getInstance(requireContext()).CommentDao().getAllCommetsPost()

            }.await()
            return@runBlocking list_
        }
        Log.i("TAG", "fillList: ${l}")
        return l
    }
    fun setDataToEditText(data: String) {
        inputField!!.setText(data.toCharArray(),0,data.length )
      //  inputField!!.setText(R.string.app_name)

    }

}