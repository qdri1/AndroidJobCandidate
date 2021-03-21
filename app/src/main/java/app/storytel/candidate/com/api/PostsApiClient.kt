package app.storytel.candidate.com.api

import com.jaredsburrows.retrofit2.adapter.synchronous.SynchronousCallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object PostsApiClient {

    private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

    fun create(): PostsApi {

        val client = OkHttpClient.Builder()
                .readTimeout(7, TimeUnit.SECONDS)
                .connectTimeout(7, TimeUnit.SECONDS)
                .build()

        val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(SynchronousCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .baseUrl(BASE_URL)
                .build()

        return retrofit.create(PostsApi::class.java)
    }

}