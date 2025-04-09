package com.example.data.network

import com.example.data.model.ExchangeRatesResponseDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface ExchangeRatesApi {

    @GET("latest.json")
    suspend fun getLatestRates(
        @Query("app_id") appId: String,
        @Query("base") base: String = "USD"
    ): ExchangeRatesResponseDTO
}