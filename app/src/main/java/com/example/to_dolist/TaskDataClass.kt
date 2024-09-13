package com.example.to_dolist

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "tasks")
data class TaskDataClass(
    @PrimaryKey(autoGenerate = true) val id:Int=0,
   @ColumnInfo(name = "Task Name") val taskName: String,
    @ColumnInfo(name = "Task Description") val taskDescription: String,
    @ColumnInfo(name = "Task Date") val taskDate: String
)
