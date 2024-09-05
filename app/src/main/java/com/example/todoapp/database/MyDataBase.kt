package com.example.todoapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Todo::class], version = 2)
abstract class MyDataBase : RoomDatabase() {
    companion object {
        private var myDataBase: MyDataBase? = null

        fun getInstance(context: Context): MyDataBase {
            if (myDataBase == null) {
                myDataBase = Room.databaseBuilder(
                    context,
                    MyDataBase::class.java, "todos database"
                ).allowMainThreadQueries().fallbackToDestructiveMigration().build()
            }
            return myDataBase!!;
        }
    }

    abstract fun getTodosDao(): ToDodao
}