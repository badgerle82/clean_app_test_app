package com.example.data.model

data class ExchangeRatesResponseDTO(
    val disclaimer: String,
    val license: String,
    val timestamp: Long,
    val base: String,
    val rates: Map<String, Double>
)