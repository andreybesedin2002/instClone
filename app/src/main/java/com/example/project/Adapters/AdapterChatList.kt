package com.example.project

import android.annotation.SuppressLint
import android.graphics.Typeface
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.project.DB.Messages.Message
import com.example.project.REST.ChatModel
import com.example.project.ui.chat_list.ChatListFragment
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation
import kotlinx.coroutines.DelicateCoroutinesApi
import java.util.*


class RecyclerAdapterChatList(private val names: ArrayList<ChatModel>) :
    RecyclerView.Adapter<RecyclerAdapterChatList.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var largeTextView: TextView? = null
        var smallTextView: TextView? = null
        var img: ImageView? = null
        var containerView: ConstraintLayout? = null
        //  var img_person: ImageView? = null

        init {
            img = itemView.findViewById(R.id.img)
            largeTextView = itemView.findViewById(R.id.textViewLarge)
            smallTextView = itemView.findViewById(R.id.textViewSmall)
            containerView = itemView.findViewById(R.id.t)
        }

    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycleview_item_chat_list, parent, false)

        return MyViewHolder(itemView)
    }


    @DelicateCoroutinesApi
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.largeTextView?.text = names[position].largeText
        holder.smallTextView?.text = names[position].smallText

        val tf_roboto = Typeface.createFromAsset(
            holder.itemView.context.assets,
            "fronts/Roboto-Black.ttf"
        )


        val tf = Typeface.createFromAsset(
            holder.itemView.context.assets,
            "fronts/Comfortaa-VariableFont_wght.ttf"
        )
        holder.largeTextView!!.typeface = tf
        holder.smallTextView!!.typeface = tf_roboto

//            GlobalScope.launch(Dispatchers.Default) {
//                Picasso.get().load("http://i.imgur.com/DvpvklR.png").into(holder.img)
//                notifyDataSetChanged()
//            }


//            Picasso.get().load("http://i.imgur.com/DvpvklR.png").placeholder(R.mipmap.ic_launcher)
//                .resizeDimen(48,48).centerCrop().into(holder.img)
        Log.i("Tggg", "onViewCreated: _")
        val radius = 5
        val margin = 5
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

        Log.i("Tggg", "onViewCreated: _")

        holder.img!!.setOnClickListener {
            ChatListFragment.bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
            ChatListFragment.back_dim_layout.visibility = View.VISIBLE
            RecyclerAdapterPhotosList.isActive = false
        }

        holder.containerView!!.setOnClickListener {
            Navigation.findNavController(holder.itemView)
                .navigate(R.id.action_navigation_dashboard_to_navigation_message)
        }
    }

    override fun getItemCount(): Int = names.size
}


