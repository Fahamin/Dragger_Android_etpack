package com.fahamin.unplashapi_jetpack.repository

import androidx.lifecycle.MutableLiveData
import com.fahamin.unplashapi_jetpack.Api.API
import com.fahamin.unplashapi_jetpack.UnsplashModel
import com.fahamin.unplashapi_jetpack.roomDB.ImageDatabase
import com.fahamin.unplashapi_jetpack.utils.Constans
import javax.inject.Inject

class ImageRepository @Inject constructor(private var api: API, private var imageDatabase: ImageDatabase) {

    var imageList = MutableLiveData<List<UnsplashModel>>()
    var imagepageList = MutableLiveData<List<UnsplashModel>>()


    suspend fun getImageList() {
        var result = api.getImageList(Constans.tokernID)

        if (result.isSuccessful && result.body() != null) {
            imageDatabase.getImageDao().insertImageList(result.body()!!)

            imageList.postValue(result.body())

        }
    }

    suspend fun getImagePageList() {
        var result = api.getImageListWithPage(Constans.tokernID, 2, 20)

        if (result.isSuccessful && result.body() != null) {
            //push data on roomdb
            imageDatabase.getImageDao().insertImageList(result.body()!!)
            //set data on livedata
            imagepageList.postValue(result.body())
        }
    }

}