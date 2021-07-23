package com.example.project

import android.annotation.SuppressLint
import android.graphics.Typeface
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation
import kotlinx.coroutines.DelicateCoroutinesApi
import java.util.*


class RecyclerAdapterShare(private val names: ArrayList<String>) :
    RecyclerView.Adapter<RecyclerAdapterShare.MyViewHolder>() {
    companion object {
        var w : Int = 0
        var h : Int = 0
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var nameTextView: TextView? = null

        var img: ImageView? = null
        var img_selected: ImageView? = null
        var containerView: LinearLayout? = null

        init {
            img = itemView.findViewById(R.id.img)
            img_selected = itemView.findViewById(R.id.img_select)
            nameTextView = itemView.findViewById(R.id.nameTextView)
            containerView = itemView.findViewById(R.id.lin_layout_share)
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycleview_item_share, parent, false)

        return MyViewHolder(itemView)
    }


    @DelicateCoroutinesApi
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        w = holder.itemView.measuredWidth
        h = holder.itemView.measuredHeight
        val tf = Typeface.createFromAsset(
            holder.itemView.context.assets,
            "fronts/Comfortaa-VariableFont_wght.ttf"
        )
        holder.nameTextView!!.typeface = tf
        //  holder.img_selected!!.visibility = View.INVISIBLE
        Picasso.get()
            .load("https://c.wallhere.com/photos/68/15/1600x1200_px_animal_cute_dog_dogs_Frendly_Pet-1642413.jpg!d")
            .error(R.drawable.ic_launcher_foreground)
            .transform(CropCircleTransformation())
            .into(holder.img)

        holder.img!!.setOnClickListener {
            //  holder.img_selected!!.visibility = View.VISIBLE
            Log.i(
                "TAG",
                "_______onBindViewHolder: click" +
                        " ${holder.itemView.rootView.measuredWidth}  ${holder.itemView.rootView.measuredHeight}  ${w}  ${h} "
            )
            if (holder.img_selected!!.visibility == View.INVISIBLE) {
                holder.img_selected!!.visibility = View.VISIBLE

                holder.itemView.rootView.measure(
                    1080,2340
                )
            }
            if (holder.img_selected!!.visibility == View.VISIBLE) {
                holder.img_selected!!.visibility = View.INVISIBLE


            }


        }
        holder.containerView!!.setOnClickListener {
            Log.i("TAG", "onBindViewHolder: click")
//                if (holder.img_selected!!.visibility == View.GONE) {
//                    holder.img_selected!!.visibility = View.VISIBLE
//                }
//                if (holder.img_selected!!.visibility == View.VISIBLE) {
//                    holder.img_selected!!.visibility = View.GONE
//                }
        }

    }

    override fun getItemCount(): Int = names.size
}


