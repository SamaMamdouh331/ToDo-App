package com.example.todoapp.database

import androidx.room.Database

@Database(entities = [ToDoEntity::class], version = 1)
abstract class ToDoDataBase {

    abstract fun getToDo(): ToDodao

}