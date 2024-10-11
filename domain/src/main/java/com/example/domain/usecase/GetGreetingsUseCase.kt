package com.example.domain.usecase

import com.example.domain.base.UseCase
import com.example.domain.model.Greeting
import com.example.domain.repository.GreetingRepository

class GetGreetingsUseCase(
    private val repository: GreetingRepository
): UseCase<Nothing?, List<Greeting>> {

    override suspend fun invoke(param: Nothing?): List<Greeting> =
        repository.getGreetings()
}