package com.example.project.ui.profile.showPhoto

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.project.R
import com.example.project.ui.profile.ProfileFragment
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation

class ShowProfilePhotoFragment : Fragment() {

    companion object {
        fun newInstance() = ShowProfilePhotoFragment()
        var profilePhoto : ImageView? = null
    }

    private lateinit var viewModel: ShowViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root =  inflater.inflate(R.layout.fragment_show_profile_photo, container, false)
        profilePhoto = root.findViewById(R.id.profile_photo)
        Picasso.get()
            .load("https://c.wallhere.com/photos/68/15/1600x1200_px_animal_cute_dog_dogs_Frendly_Pet-1642413.jpg!d")
            .error(R.color.no_photo)
            .into(profilePhoto)
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ShowViewModel::class.java)

    }

}