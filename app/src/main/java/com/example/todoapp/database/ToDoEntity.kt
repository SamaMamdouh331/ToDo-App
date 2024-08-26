package com.example.todoapp.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("Todo")
data class ToDoEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo
    val id: Int,
    @ColumnInfo
    var title: String,
    @ColumnInfo
    var isdone: Boolean,
    @ColumnInfo
    var date: Long,
    @ColumnInfo
    var description: String
)