package com.example.domain.repository

import com.example.domain.model.Greeting


interface GreetingRepository {

    suspend fun getGreetings(): List<Greeting>

    suspend fun cacheGreetings(greetings: List<Greeting>)
}