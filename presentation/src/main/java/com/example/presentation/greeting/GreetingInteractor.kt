package com.example.presentation.greeting

import com.example.domain.base.invoke
import com.example.domain.model.Greeting
import com.example.domain.usecase.CacheGreetingsUseCase
import com.example.domain.usecase.GetGreetingsUseCase
import com.example.presentation.greeting.mapper.GreetingToGreetingDisplayModelMapper
import com.example.presentation.greeting.model.GreetingDisplayModel

class GreetingInteractor(
    private val getGreetingsUseCase: GetGreetingsUseCase,
    private val cacheGreetingsUseCase: CacheGreetingsUseCase,
    private val greetingToGreetingDisplayModelMapper: GreetingToGreetingDisplayModelMapper
) {

    suspend fun obtainGreeting(): List<GreetingDisplayModel> {
        val greetings = getGreetingsUseCase.invoke()
        cacheGreetingsUseCase.invoke(greetings)
        return greetings.map { greetingToGreetingDisplayModelMapper.map(it) }
    }
}