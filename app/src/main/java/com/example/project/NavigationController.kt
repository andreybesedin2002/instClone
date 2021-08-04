package com.example.project

import android.os.Bundle
import android.view.View
import androidx.navigation.Navigation

class NavigationController {
    private fun navigateTo(id: Int, view: View, bundle: Bundle?) {
        Navigation.findNavController(view)
            .navigate(id,bundle)
    }

    fun goToFragment(id: Int, view: View, bundle: Bundle?) {


        when (id) {
            R.id.action_navigation_dashboard_to_navigation_other_profile -> {
                navigateTo(R.id.action_navigation_dashboard_to_navigation_other_profile, view,bundle)
                    MainAct.navView.visibility = View.GONE
            }
            R.id.action_navigation_home_to_navigation_comments -> {
                navigateTo(R.id.action_navigation_home_to_navigation_comments, view,bundle)
                    MainAct.navView.visibility = View.GONE
            }

        }

    }
}