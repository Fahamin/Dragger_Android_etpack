package com.fahamin.unplashapi_jetpack.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.fahamin.unplashapi_jetpack.UnsplashModel
import com.fahamin.unplashapi_jetpack.adapter.ImageAdapter
import com.fahamin.unplashapi_jetpack.databinding.ActivityMainBinding
import com.fahamin.unplashapi_jetpack.utils.ImageApplication
import com.fahamin.unplashapi_jetpack.utils.ItemClickListener
import com.fahamin.unplashapi_jetpack.viewModel.MainActivityViewModel
import com.fahamin.unplashapi_jetpack.viewModelFactory.MainViewModelFactory
import javax.inject.Inject

class MainActivity : AppCompatActivity() , ItemClickListener {

    lateinit var mainActivityViewModel: MainActivityViewModel

    @Inject
    lateinit var mainViewModelFactory: MainViewModelFactory

    lateinit var binging: ActivityMainBinding
    lateinit var imageAdapter: ImageAdapter
    lateinit var filterList: ArrayList<String>
    lateinit var allList: ArrayList<UnsplashModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binging = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binging.root)

        //inject with component
        (application as ImageApplication).applicationComponent.inject(this)

        val map = (application as ImageApplication).applicationComponent.getMap()

        mainActivityViewModel =
            ViewModelProvider(this, mainViewModelFactory).get(MainActivityViewModel::class.java)

        mainActivityViewModel.imageList.observe(this, Observer { it ->
            it?.let {
                Log.e("url", "" + it.get(0).urls?.small)
                imageAdapter = ImageAdapter(it, this)
                allList = it as ArrayList<UnsplashModel>
                binging.recyclerViewID.apply {
                    layoutManager = GridLayoutManager(context, 2)
                    adapter = imageAdapter
                }
            }
        })
    }

    override fun onItemClick(pos: Int) {

        filterList = ArrayList<String>();

        for (i in 0 until allList.size) {
            filterList.add(allList.get(i).urls?.full.toString())
        }

        startActivity(
            Intent(this, ImageSliderActivity::class.java)
                .putExtra("list", filterList)
                .putExtra("pos", pos)
        );

    }
}