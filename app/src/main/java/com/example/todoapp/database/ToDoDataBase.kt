package com.example.todoapp.database

import android.content.Context
import androidx.room.Database

@Database(entities = [ToDoEntity::class], version = 1)
abstract class ToDoDataBase {
    Companion
    object {
        private var toDoDataBase: ToDoDataBase? = null

        fun getInstance(context: Context): ToDoDataBase {
            if (ToDoDataBase.`<no name provided>`.toDoDataBase == null) {
                Room.databaseBuilder(
                    this,
                    ToDoDataBase::class.java, "todos"
                ).build()
            }
        }
    }
}