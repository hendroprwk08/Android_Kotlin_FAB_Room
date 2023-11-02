package com.hendro.kotlinroom.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Buku::class], version = 1)
abstract class BukuDatabase: RoomDatabase() {
    abstract fun bukuDao(): BukuDao
}