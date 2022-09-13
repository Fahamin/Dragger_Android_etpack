package com.fahamin.unplashapi_jetpack.di

import android.content.Context
import com.fahamin.unplashapi_jetpack.view.MainActivity
import com.fahamin.unplashapi_jetpack.view.PerPageImageActivity
import dagger.Component
import dagger.Module
import javax.inject.Singleton
import androidx.lifecycle.ViewModel
import dagger.BindsInstance


@Singleton
@Component(modules = [NetworkModule::class,ViewModelModule::class,DatabaseModule::class])
interface ApplicationComponent {

    //inject this way to multiple activity in component
    fun inject(mainActivity: MainActivity)

    fun inject(perPageImageActivity: PerPageImageActivity)

    //for room db context to
    // dagger anywher access context this way
    //runtime value need to use factory
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): ApplicationComponent
    }

    fun getMap( ) : Map<Class<*>,ViewModel>

}