package com.ted.fluffy.api

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

class ApiManager {

    private val BASE_URL = "https://api.odsay.com"
    private val api: ODsay = createRetrofit(BASE_URL)

    private fun createRetrofit(baseUrl: String): ODsay {
        return Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(baseUrl)
            .build().create(ODsay::class.java)
    }

    fun from(): ODsay {
        return api
    }
}