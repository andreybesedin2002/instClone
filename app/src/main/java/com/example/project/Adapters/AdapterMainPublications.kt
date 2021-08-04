package com.example.project

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.project.Objects.Like
import com.example.project.REST.MainPublicationModel
import com.example.project.ui.MainPublications.MainPublicationsFragment
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation
import kotlinx.coroutines.DelicateCoroutinesApi


class RecyclerAdapterMainPublications(private val names: ArrayList<MainPublicationModel>) :
    RecyclerView.Adapter<RecyclerAdapterMainPublications.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var img: ImageView? = null
        var img_publication: ImageView? = null
        var like: ImageView? = null
        var like_text: TextView? = null
        var comment: ImageView? = null
        var share: ImageView? = null

        var like_publication: Like? = null

        init {

            img = itemView.findViewById(R.id.img) as ImageView?
            img_publication = itemView.findViewById(R.id.img_publication) as ImageView?
            like = itemView.findViewById(R.id.like) as ImageView?
            like_text = itemView.findViewById(R.id.like_text) as TextView?
            comment = itemView.findViewById(R.id.comment) as ImageView?
            share = itemView.findViewById(R.id.share) as ImageView?
            like_publication = Like(img = like!!)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycleview_item_main_publication, parent, false)

        return MyViewHolder(itemView)
    }

    companion object {

        var isActive: Boolean = true

    }

    @DelicateCoroutinesApi
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        Picasso.get()
            .load("https://c.wallhere.com/photos/68/15/1600x1200_px_animal_cute_dog_dogs_Frendly_Pet-1642413.jpg!d")
            .error(R.drawable.ic_launcher_foreground)
            .transform(CropCircleTransformation())
            .into(holder.img)

        Picasso.get()
            .load("https://c.wallhere.com/photos/68/15/1600x1200_px_animal_cute_dog_dogs_Frendly_Pet-1642413.jpg!d")
            .error(R.drawable.ic_launcher_foreground)
            .resize(holder.itemView.rootView.width, 300)
            .into(holder.img_publication)

        holder.like_text!!.text = names[position].likes

        holder.like_publication!!.setOnClickListener {
            holder.like_text!!.text = (5).toString()
        }

        holder.comment?.setOnClickListener {

            val bundle = Bundle()
            bundle.putString("arg1", names[position].idPost)
            bundle.putInt("arg2", 2)

            NavigationController().goToFragment(
                R.id.action_navigation_home_to_navigation_comments,
                holder.itemView, bundle
            )
        }

        holder.share?.setOnClickListener {
            MainPublicationsFragment.bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
            MainPublicationsFragment.back_dim_layout.visibility = View.VISIBLE
            isActive = false

        }
    }

    override fun getItemCount(): Int = names.size
}


