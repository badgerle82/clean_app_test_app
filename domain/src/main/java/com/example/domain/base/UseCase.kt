package com.example.domain.base

interface UseCase<in P, out R> {
    suspend fun invoke(param: P): R
}

suspend fun <R> UseCase<Nothing?, R>.invoke() = invoke(null)