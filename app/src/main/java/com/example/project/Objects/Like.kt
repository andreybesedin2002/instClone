package com.example.project.Objects

import android.widget.ImageView
import com.example.project.R

class Like (val img: ImageView) {

    fun setOnClickListener(click: () -> Unit){
        img.setOnClickListener {
            click()
            changeView()
        }

    }
fun changeView(){
    img.setBackgroundResource(R.drawable.ic_baseline_favorite_24)
}
    init {

    }
}