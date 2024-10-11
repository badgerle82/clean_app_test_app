package com.example.data.repository

import android.content.Context
import com.example.domain.model.Greeting
import com.example.domain.repository.GreetingRepository
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.InputStreamReader

class GreetingRepositoryImpl(
    private val gson: Gson,
    private val context: Context
): GreetingRepository {

    var greetingsCache: MutableList<Greeting> = mutableListOf()

    override suspend fun getGreetings(): List<Greeting> {

        val inputStream = context.assets.open("greeting_data.json")
        val reader = InputStreamReader(inputStream)

        // Define the type for List<Greeting>
        val greetingListType = object : TypeToken<List<Greeting>>() {}.type

        // Parse and return the list of Greeting objects
        return gson.fromJson(reader, greetingListType)
    }

    override suspend fun cacheGreetings(greetings: List<Greeting>) {
        greetingsCache.clear()
        greetingsCache.addAll(greetings)
    }
}