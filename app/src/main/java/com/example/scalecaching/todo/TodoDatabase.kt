package com.example.scalecaching.todo

import androidx.room.*

@Entity
data class TodoItem(
    @PrimaryKey val id: Int,
    @ColumnInfo val title: String,
    @ColumnInfo var urgent: Boolean = false
)
