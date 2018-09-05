package com.example.elidev.movieapp.api

import com.example.elidev.movieapp.BuildConfig
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.*

class RetrofitImpl {

    companion object {

        fun retrofitService(): Retrofit {
            val interceptor = HttpLoggingInterceptor()
                    .setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

            val moshi = Moshi.Builder()
                    .add(KotlinJsonAdapterFactory())
                    .add(Date::class.java,  Rfc3339DateJsonAdapter())
                    .build()

            return Retrofit.Builder()
                    .baseUrl(BuildConfig.TMDB_BASE_URL)
                    .client(client)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(MoshiConverterFactory.create(moshi))
                    .build()
        }

    }
}