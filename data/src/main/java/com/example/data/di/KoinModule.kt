package com.example.data.di

import androidx.room.Room
import com.example.data.db.AppDatabase
import com.example.data.db.dao.AssetDao
import com.example.data.network.ExchangeRatesApi
import com.example.data.repository.AssetsRepositoryImpl
import com.example.domain.repository.AssetsRepository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val dataModule = module {

    single<AssetsRepository> { AssetsRepositoryImpl(get(), get())  }

    single<Gson> { GsonBuilder().setLenient().create() }

    single<AppDatabase> {
        Room.databaseBuilder(
            get(),
            AppDatabase::class.java,
            "assets_db"
        ).fallbackToDestructiveMigration()
            .build()
    }

    single<AssetDao> {
        get<AppDatabase>().assetsDao()
    }
}

val networkModule = module {
    factory {
        Retrofit.Builder()
            .baseUrl("https://openexchangerates.org/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
            .create(ExchangeRatesApi::class.java)
    }

    factory {
        OkHttpClient.Builder()
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .build()
    }
}