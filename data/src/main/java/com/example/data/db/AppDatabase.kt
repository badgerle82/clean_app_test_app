package com.example.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.db.dao.AssetDao
import com.example.data.db.entity.AssetEntity

@Database(entities = [AssetEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun assetsDao(): AssetDao
}