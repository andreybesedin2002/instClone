package com.example.project

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.coroutines.*
import java.io.File
import java.util.*


class RecyclerAdapterPhotosList(private val names: ArrayList<ArrayList<Int>>) :
    RecyclerView.Adapter<RecyclerAdapterPhotosList.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var img1: ImageView? = null
        var img2: ImageView? = null
        var img3: ImageView? = null

        init {
            img1 = itemView.findViewById(R.id.img1)
            img2 = itemView.findViewById(R.id.img2)
            img3 = itemView.findViewById(R.id.img3)
            Log.i("TAG", "jn: ${img1!!.width}")
        }
    }

    companion object {

        var isActive: Boolean = true

    }

    fun addNewRandomItems(t: List<List<Int>>) = (0..9).forEach { i -> names.add(t[i] as ArrayList<Int>) }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycleview_item_photos_list, parent, false)

        return MyViewHolder(itemView)
    }


    @DelicateCoroutinesApi
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
names[position][0]
        Picasso.get().load(R.mipmap.s1)
            .error(names[position][0]).into(holder.img1)
   //     holder.img1!!.layoutParams.height   = holder.img1!!.width

        Log.i("TAG", "jnn: ${holder.img1!!.width}")
        Picasso.get().load(R.mipmap.s1)
            .error(names[position][0]).into(holder.img2)
    //    holder.img2!!.layoutParams.height   = holder.img2!!.width

        Picasso.get().load(R.mipmap.s1)
            .error(names[position][0]).into(holder.img3)
     //   holder.img3!!.layoutParams.height   = holder.img3!!.width


//        Picasso.get()
//            .load("/Users/andreybesedin/Desktop/s1.png")
//            .error(R.drawable.ic_launcher_foreground)
//            .into(holder.img3)

        holder.itemView.setOnClickListener {
            if (isActive) {
                Log.i("TAG", "jnn: ${holder.img1!!.width}")
                Navigation.findNavController(holder.itemView)
                    .navigate(R.id.action_navigation_notifications_to_navigation_publications)

            }
//ChatListFragment.bottomSheetView.visibility = View.VISIBLE
            //      ChatListFragment.bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
            //    ChatListFragment.back_dim_layout.visibility = View.VISIBLE
        }

    }

    override fun getItemCount(): Int = names.size
}


