package com.fahamin.unplashapi_jetpack.utils

import android.app.Application
import com.fahamin.unplashapi_jetpack.di.ApplicationComponent
import com.fahamin.unplashapi_jetpack.di.DaggerApplicationComponent

class ImageApplication() : Application() {

    lateinit var applicationComponent: ApplicationComponent


    override fun onCreate() {
        super.onCreate()

        // before use factory
        //applicationComponent = DaggerApplicationComponent.builder().build()

        // after use factory passing context
        applicationComponent = DaggerApplicationComponent.factory().create(this)
    }
}