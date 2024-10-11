package com.example.domain.base

interface Mapper<P, R> {
    suspend fun map(param: P): R
}