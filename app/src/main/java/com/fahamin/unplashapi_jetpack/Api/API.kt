package com.fahamin.unplashapi_jetpack.Api

import com.fahamin.unplashapi_jetpack.UnsplashModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface API {

    @GET("photos")
    suspend fun getImageList(@Query("client_id") cid: String?): Response<List<UnsplashModel>>

    //user page section
    @GET("photos")
    suspend fun getImageListWithPage(
        @Query("client_id") cid: String?,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): Response<List<UnsplashModel>>

    //can search section
    @GET("search/photos")
    suspend fun searchImage(
        @Query("client_id") cid: String?,
        @Query("query") name: String?
    ): Response<List<UnsplashModel>>

}