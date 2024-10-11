package com.example.presentation.greeting.mapper

import com.example.domain.base.Mapper
import com.example.domain.model.Greeting
import com.example.presentation.greeting.model.GreetingDisplayModel

class GreetingToGreetingDisplayModelMapper: Mapper<Greeting, GreetingDisplayModel> {

    override suspend fun map(param: Greeting): GreetingDisplayModel {
        return GreetingDisplayModel((param.status.plus(" ").plus(param.greeting)))
    }
}