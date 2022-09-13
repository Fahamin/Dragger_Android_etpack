package com.fahamin.unplashapi_jetpack.view

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.fahamin.unplashapi_jetpack.UnsplashModel
import com.fahamin.unplashapi_jetpack.adapter.ImageAdapter
import com.fahamin.unplashapi_jetpack.databinding.ActivityPerPageImageBinding
import com.fahamin.unplashapi_jetpack.roomDB.ImageDatabase
import com.fahamin.unplashapi_jetpack.utils.ImageApplication
import com.fahamin.unplashapi_jetpack.utils.ItemClickListener
import com.fahamin.unplashapi_jetpack.viewModelFactory.MainViewModelFactory
import com.fahamin.unplashapi_jetpack.viewModel.PerPageViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class PerPageImageActivity : AppCompatActivity(), ItemClickListener {

    @Inject
    lateinit var mainViewModelFactory: MainViewModelFactory
    lateinit var perPageViewModel: PerPageViewModel

    lateinit var binging: ActivityPerPageImageBinding
    lateinit var imageAdapter: ImageAdapter
    lateinit var filterList: ArrayList<String>
    lateinit var allList: List<UnsplashModel>
    lateinit var dblist: List<UnsplashModel>

    @Inject
    lateinit var imageDatabase: ImageDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binging = ActivityPerPageImageBinding.inflate(layoutInflater)
        setContentView(binging.root)

        //inject with component
        (application as ImageApplication).applicationComponent.inject(this)


        //for multiple viewmodel in one factory
        val map = (application as ImageApplication).applicationComponent.getMap()

        perPageViewModel =
            ViewModelProvider(this, mainViewModelFactory).get(PerPageViewModel::class.java)


        // room db not access in main thread so use corotineScope
        lifecycleScope.launch {
            dblist = imageDatabase.getImageDao().getImageList()

        }

        //observer not working without main thread so i use it handaler to wait complete in lifecyclescope
        Handler(Looper.getMainLooper()).postDelayed({
            if (dblist.isNotEmpty()) {
                Log.e("url", "Data From DB")
                setDataOnRecyleView()
            } else {
                Log.e("url", "Data From API")
                observeDataFromApi()
            }
        },3000)


    }

    private fun setDataOnRecyleView() {
        imageAdapter = ImageAdapter(dblist, this)
        allList = dblist
        binging.recyclerViewID.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = imageAdapter
        }
    }

    private fun observeDataFromApi() {
        perPageViewModel.perPageList.observe(this, Observer {
            it?.let {
                Log.e("url", "" + it.get(1).urls?.small)
                imageAdapter = ImageAdapter(it, this)
                allList = it
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