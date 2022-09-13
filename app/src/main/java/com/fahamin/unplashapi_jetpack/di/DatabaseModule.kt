package com.fahamin.unplashapi_jetpack.di

import android.content.Context
import androidx.room.Room
import com.fahamin.unplashapi_jetpack.roomDB.ImageDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class DatabaseModule {


    @Singleton
    @Provides
    fun getImageDB(context: Context):ImageDatabase{
        return Room.databaseBuilder(context, ImageDatabase::class.java, "imoDB").build()
    }
}