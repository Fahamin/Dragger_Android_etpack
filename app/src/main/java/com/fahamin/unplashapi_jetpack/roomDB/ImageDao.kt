package com.fahamin.unplashapi_jetpack.roomDB

import androidx.room.*
import com.fahamin.unplashapi_jetpack.Model.typeConverter.UrlsTypeConverters
import com.fahamin.unplashapi_jetpack.Model.typeConverter.UserTypeConverters
import com.fahamin.unplashapi_jetpack.UnsplashModel

@Dao
interface ImageDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertImageList(list: List<UnsplashModel>)

    @Query("SELECT * FROM UnsplashModel")
    suspend fun getImageList(): List<UnsplashModel>


}