package com.example.project


import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.project.DB.Messages.Message
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation
import java.util.*


class CustomRecyclerAdapter(private var names: ArrayList<Message>) :
    RecyclerView.Adapter<CustomRecyclerAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var largeTextView: TextView? = null
        var smallTextView: TextView? = null

        //  var img_person: ImageView? = null
        var img: ImageView? = null
        //  var img_person: ImageView? = null

        init {
            img = itemView.findViewById(R.id.img_l)
            //    img_person = itemView.findViewById(R.id.imageView)
            largeTextView = itemView.findViewById(R.id.textViewLarge)
            smallTextView = itemView.findViewById(R.id.textViewSmall)
        }
    }

    fun addMessages(t: List<Message>): Int {
        Log.i("-", 10.toString())
        val curs = names.size
        var success = 0
        (0..9).forEach { i ->
            if (t[i] != null) {
                names.add(t[i])
                success += 1
            }
        }
        return success
    }

    fun addMessage(t: Message) {
        Log.i("-", 10.toString())
        names = ((listOf(t) + names) as ArrayList<Message>)

    }

    override fun getItemViewType(position: Int): Int {
        super.getItemViewType(position)
        Log.i("TAG", "getItemViewType: ${names[position]} ")
        if (names[position].idUser == 1) {
            return 1
        }
        return 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView: View
        itemView = if (viewType != 1) {
            LayoutInflater.from(parent.context)
                .inflate(R.layout.recyclerview_item_l, parent, false)
        } else {
            LayoutInflater.from(parent.context)
                .inflate(R.layout.recyclerview_item_r, parent, false)
        }
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.largeTextView?.text = names[position].text
        holder.smallTextView?.text = "кот"
        Picasso.get()
            .load("https://avatars.githubusercontent.com/u/7534778?v=4")
            .error(R.drawable.ic_launcher_foreground)
            .transform(CropCircleTransformation())

            .into(holder.img, object : Callback {
                override fun onSuccess() {
                    Log.i("WWWWWWWW", "onSuccess:$ ")
                }

                override fun onError(e: Exception?) {
                    Log.i("WWWWWWWW", "onError:$e ")
                }
            })

//        val job = GlobalScope.launch(Dispatchers.Default) {
//            async {
//                Picasso.get()
//                    .load("http://lifehacker.ru/wp-content/uploads/2015/05/google-android-logo-green-black.jpg")
//                    .error(R.drawable.ic_launcher_foreground)
//                    .into(holder.img_person)
//
//            }.await()
//            notifyDataSetChanged()
    }

    override fun getItemCount(): Int = names.size
}


