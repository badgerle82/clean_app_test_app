package com.example.domain.base

interface UseCaseBlocking<in P, out R> {
    fun invoke(param: P): R
}

fun <R> UseCaseBlocking<Nothing?, R>.invoke() = invoke(null)