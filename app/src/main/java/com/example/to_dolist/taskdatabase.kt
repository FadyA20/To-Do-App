package com.example.to_dolist

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [TaskDataClass::class], version = 1, exportSchema = false)
abstract class taskdatabase:RoomDatabase() {
    abstract fun taskdao(): TaskDao

    companion object {
        @Volatile
        private var INSTANCE: taskdatabase? = null
        fun getdatabase(context: Context): taskdatabase {
            return INSTANCE ?: synchronized(this) {
                val db = Room.databaseBuilder(
                    context.applicationContext,
                    taskdatabase::class.java,
                    "tasks database"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = db
                db
            }
        }
    }
}