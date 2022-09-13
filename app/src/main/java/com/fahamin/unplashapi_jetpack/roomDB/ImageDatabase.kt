package com.fahamin.unplashapi_jetpack.roomDB

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.fahamin.unplashapi_jetpack.Model.typeConverter.*
import com.fahamin.unplashapi_jetpack.UnsplashModel

//insert to unknown data type to room db
@TypeConverters(UrlsTypeConverters::class,LinksTypeConverters::class,ProfileImageTypeConverters::class,
SocialTypeConverters::class,SponsorTypeConverters::class,UserTypeConverters::class)

@Database(entities = [UnsplashModel::class], version = 1)
abstract class ImageDatabase : RoomDatabase() {

  abstract  fun getImageDao(): ImageDao

}