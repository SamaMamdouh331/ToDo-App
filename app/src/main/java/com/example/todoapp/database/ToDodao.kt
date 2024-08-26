package com.example.todoapp.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
abstract class ToDodao {

    @Insert
    abstract fun addTodo(todo: ToDoEntity)

    @Delete
    abstract fun deleteTodo(todo: ToDoEntity)

    @Update
    abstract fun updateTodo(todo: ToDoEntity)

    @Query("select * from Todo")
    abstract fun getTodo(): List<ToDoEntity>
}