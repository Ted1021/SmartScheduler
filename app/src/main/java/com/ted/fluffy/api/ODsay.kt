package com.ted.fluffy.api

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ODsay {

    // 대중교통 길찾기
    @GET("ODsayService.requestSearchPubTransPath")
    fun findPath(
        @Query("SX") startX: Float,
        @Query("SY") startY: Float,
        @Query("EX") endX: Float,
        @Query("EY") endY: Float
    ): Single<Unit>

    
}