package com.example.scalecaching.common

import androidx.room.*
import kotlinx.coroutines.flow.Flow

interface BaseDao<T> {

    @Insert
    fun insertAll(vararg obj: T)

    @Delete
    fun delete(obj: T)

    @Update
    fun update(obj: T)

    fun getAll(): Flow<List<T>>

    fun nukeTable()
}
