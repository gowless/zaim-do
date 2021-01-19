package com.kotlin_base_dev.ui.main

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.kotlin_base_dev.fragments.AllOffers
import com.kotlin_base_dev.fragments.BadList
import com.kotlin_base_dev.fragments.ZeroList
import com.kotlin_base_dev.uiactivities.Splash


private val TAB_TITLES = arrayOf(
    Splash.first,
    Splash.second,
    Splash.third
)

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class SectionsPagerAdapter(private val context: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        return when (position){
            0 -> AllOffers()
            1 -> BadList()
            2 -> ZeroList()
            else -> AllOffers()
        }

    }

    override fun getPageTitle(position: Int): String? {
        return TAB_TITLES[position]
    }

    override fun getCount(): Int {
        // Show 2 total pages.
        return Splash.numberOfTabs
    }
}