package com.larissa.integrativeproject.presentation.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.larissa.integrativeproject.presentation.screen.AllMoviesFragment
import com.larissa.integrativeproject.presentation.screen.FavoriteMoviesFragment

class SectionsPagerAdapter (fa: FragmentActivity) : FragmentStateAdapter(fa) {

    override fun getItemCount(): Int = NUM_PAGES

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> AllMoviesFragment()
            1 -> FavoriteMoviesFragment()
            else -> AllMoviesFragment()
        }
    }
    companion object {
        private const val NUM_PAGES = 2
    }
}
