package com.example.project

import android.view.View
import androidx.navigation.Navigation

class NavigationController {
    private fun navigateTo(id: Int, view: View) {
        Navigation.findNavController(view)
            .navigate(id)
    }

    fun goToFragment(id: Int, view: View) {


        when (id) {
            R.id.action_navigation_dashboard_to_navigation_other_profile -> {
                navigateTo(R.id.action_navigation_dashboard_to_navigation_other_profile, view)
                    MainAct.navView.visibility = View.GONE
            }
            R.id.action_navigation_home_to_navigation_comments -> {
                navigateTo(R.id.action_navigation_home_to_navigation_comments, view)
                    MainAct.navView.visibility = View.GONE
            }

        }

    }
}