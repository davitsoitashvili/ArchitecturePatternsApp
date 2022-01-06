package com.example.model.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Davit Soitashvili on 06.01.22
 **/

class RetrofitClient {
    private val baseUrl = "https://jsonplaceholder.typicode.com/"

    private var retrofit: Retrofit? = null

    fun getApiClient(): Retrofit {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        return retrofit!!
    }
}