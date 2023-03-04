package com.example.scalecaching.common

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.scalecaching.contact.ContactItem
import com.example.scalecaching.todo.TodoItem

@Database(entities = [TodoItem::class, ContactItem::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun todoDao(): TodoItemDao
    abstract fun contactDao(): ContactItemDao
}
