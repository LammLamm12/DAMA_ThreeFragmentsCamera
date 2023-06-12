package pt.ipt.dama.dama_threefragmentscamera

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {
    // add variables to reference the objects on interface
    lateinit var tabLayout: TabLayout
    lateinit var viewPager: ViewPager2
    // add variable to reference the tool used
    // to interact with fragments
    lateinit var myViewPagerAdapter: MyViewPagerAdapter




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // reference elements on interface
        tabLayout=findViewById(R.id.tabLayout)
        viewPager=findViewById(R.id.viewPager)

        // assign the viewPagerAdapter to ViewPager
        myViewPagerAdapter= MyViewPagerAdapter(this)
        viewPager.adapter=myViewPagerAdapter

        // choose what happens when we click on tabs
        tabLayout.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                viewPager.currentItem=tab!!.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) { }
            override fun onTabReselected(tab: TabLayout.Tab?){ }
        })

        // do something if user uses their fingers on screen
        viewPager.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                tabLayout.getTabAt(position)?.select()
            }
        })

    }



}