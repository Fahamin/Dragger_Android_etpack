package com.fahamin.unplashapi_jetpack.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.fahamin.unplashapi_jetpack.R
import com.fahamin.unplashapi_jetpack.adapter.ImageViewPagerAdapter
import com.fahamin.unplashapi_jetpack.databinding.ActivityImageSliderBinding

class ImageSliderActivity : AppCompatActivity() {

    private lateinit var binding: ActivityImageSliderBinding
    lateinit var imageViewPagerAdapter: ImageViewPagerAdapter

    var position: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityImageSliderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var list = intent.getStringArrayListExtra("list")
        position = intent.getIntExtra("pos", 1);
        imageViewPagerAdapter = ImageViewPagerAdapter(list)

        setUpViewPager()
    }

    private fun setUpViewPager() {

        binding.viewPager.adapter = imageViewPagerAdapter

        //set the orientation of the viewpager using ViewPager2.orientation
        binding.viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        //select any page you want as your starting page
        val currentPageIndex = position
        binding.viewPager.currentItem = currentPageIndex

        // registering for page change callback
        binding.viewPager.registerOnPageChangeCallback(
            object : ViewPager2.OnPageChangeCallback() {

                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                }
            }
        )
    }

    override fun onDestroy() {
        super.onDestroy()

        // unregistering the onPageChangedCallback
        binding.viewPager.unregisterOnPageChangeCallback(
            object : ViewPager2.OnPageChangeCallback() {}
        )
    }
}