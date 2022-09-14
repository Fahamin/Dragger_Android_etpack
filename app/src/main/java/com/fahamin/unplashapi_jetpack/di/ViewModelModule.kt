package com.fahamin.unplashapi_jetpack.di

import androidx.lifecycle.ViewModel
import com.fahamin.unplashapi_jetpack.viewModel.MainActivityViewModel
import com.fahamin.unplashapi_jetpack.viewModel.PerPageViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap


@Module
abstract class ViewModelModule {

    //bindinstanc use interface maybe
    @Binds
    @ClassKey(MainActivityViewModel::class)
    @IntoMap
    abstract fun mainViewModel(mainActivityViewModel: MainActivityViewModel) :ViewModel


    @Binds
    @ClassKey(PerPageViewModel::class)
    @IntoMap
    abstract fun perPageViewModel(perPageViewModel: PerPageViewModel) :ViewModel

}