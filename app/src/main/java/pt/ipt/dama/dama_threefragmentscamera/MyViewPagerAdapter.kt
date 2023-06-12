package pt.ipt.dama.dama_threefragmentscamera

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import pt.ipt.dama.dama_threefragmentscamera.fragments.Fragment1
import pt.ipt.dama.dama_threefragmentscamera.fragments.Fragment2
import pt.ipt.dama.dama_threefragmentscamera.fragments.Fragment3

/**
 * this class is used to inject fragments on activity
 */
class MyViewPagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {

    /**
     * specify the number of fragments that you are going to use
     */
    override fun getItemCount(): Int {
        return 3
    }

    /**
     * return a new instance of a fragment
     */
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> Fragment1()
            1 -> Fragment2()
            2 -> Fragment3()
            else -> Fragment1()
        }
        /*
         when (position) {
            0 -> return FragmentOne()
            1 -> return FragmentTwo()
            2 -> return FragmentThree()
            else -> return FragmentOne()
        }
         */
    }
}