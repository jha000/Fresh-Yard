package com.example.milkaggregatorapplication

import okhttp3.OkHttpClient.newBuilder
import okhttp3.OkHttpClient.Builder.addInterceptor
import okhttp3.OkHttpClient.Builder.build
import retrofit2.Retrofit
import com.example.milkaggregatorapplication.RetrofitInstance
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient
import com.example.milkaggregatorapplication.MyIntercepter

object RetrofitInstance {
    private const val BASE_URL = "https://graph.facebook.com/"
    private const val VERSION = "v15.0/"
    private var retrofit: Retrofit? = null
    val retrofitClient: Retrofit?
        get() {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL + VERSION)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(OkHttpClient().newBuilder().addInterceptor(MyIntercepter()).build())
                    .build()
            }
            return retrofit
        }
}