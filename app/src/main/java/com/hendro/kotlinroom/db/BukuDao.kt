package com.hendro.kotlinroom.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface BukuDao {
    @Query("SELECT * FROM buku")
    fun getAllBooks():List<Buku>

    @Insert
    fun insert(vararg buku: Buku)

    @Delete
    fun delete(buku: Buku)

    @Update
    fun update(buku: Buku)
}