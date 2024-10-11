package com.example.domain.usecase

import com.example.domain.base.UseCase
import com.example.domain.model.Greeting
import com.example.domain.repository.GreetingRepository

class CacheGreetingsUseCase(
    private val repository: GreetingRepository
): UseCase<List<Greeting>, Unit> {

    override suspend fun invoke(param: List<Greeting>) =
        repository.cacheGreetings(param)
}