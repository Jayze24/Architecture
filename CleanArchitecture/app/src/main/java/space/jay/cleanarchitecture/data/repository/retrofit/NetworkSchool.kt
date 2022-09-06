package space.jay.cleanarchitecture.data.repository.retrofit

import com.google.gson.GsonBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import space.jay.cleanarchitecture.base.WrapperResult
import java.util.concurrent.TimeUnit

class NetworkSchool() {

    fun <T> initApi(baseUrl: String, service: Class<T>): T {
        val logger = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC }
        val httpClient = OkHttpClient.Builder()
            .readTimeout(10, TimeUnit.SECONDS)
            .connectTimeout(10, TimeUnit.SECONDS)
            .addInterceptor(logger)
            .build()

        val retrofit = Retrofit.Builder()
            .client(httpClient)
            .baseUrl(baseUrl)
            .addConverterFactory(ScalarsConverterFactory.create()) // 응답을 string으로 받을 수 있다.
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder().setLenient()
                        .create()
                )
            ) // 응답을 json으로 받는데 컨버터로 gson 사용
            .build()

        return retrofit.create(service)
    }

    suspend fun <T> safeApiCall(apiCall: suspend () -> Response<T>): WrapperResult<T?> {
        return withContext<WrapperResult<T?>>(Dispatchers.IO) {
            try {
                apiCall.invoke().let { result ->
                    if (result.isSuccessful) {
                        WrapperResult.Success(result.body())
                    } else {
                        WrapperResult.Error(Throwable(message = result.errorBody().toString()))
                    }
                }
            } catch (t: Throwable) {
                WrapperResult.Error(t)
            }
        }
    }
}