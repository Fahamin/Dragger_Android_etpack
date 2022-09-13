package com.fahamin.unplashapi_jetpack.di

import com.fahamin.unplashapi_jetpack.Api.API
import com.fahamin.unplashapi_jetpack.utils.Constans
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
class NetworkModule {

    @Singleton
    @Provides
    fun providesRetrofit(): Retrofit {
        return  Retrofit.Builder().baseUrl(Constans.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    @Singleton
    @Provides
    fun providesApi(retrofit: Retrofit):API{
        return retrofit.create(API::class.java)
    }
}