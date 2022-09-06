package space.jay.cleanarchitecture.data.repository.retrofit.service

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import space.jay.cleanarchitecture.BuildConfig
import space.jay.cleanarchitecture.data.repository.retrofit.data.DataTopic


interface ServiceUnsplash {

    @GET("topics")
    suspend fun getListTopic(
//        @Query("ids") query: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int = 30,
        @Query("order_by") oder: String = "latest",
        @Query("client_id") clientId: String = BuildConfig.UNSPLASH_ACCESS_KEY,
    ): Response<List<DataTopic>>

}