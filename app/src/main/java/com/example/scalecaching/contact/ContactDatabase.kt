package com.example.scalecaching.contact

import androidx.room.*

@Entity
data class ContactItem(
    @PrimaryKey val id: Int,
    @ColumnInfo val name: String,
    @ColumnInfo var friend: Boolean = false
)

