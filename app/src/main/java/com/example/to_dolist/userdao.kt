package com.example.to_dolist

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao

interface TaskDao {
    @Insert
    suspend fun insert(task: TaskDataClass)
    @Query("SELECT * FROM tasks")
     fun getAllTasks(): LiveData<List<TaskDataClass>>
    @Delete
    suspend fun deleteTask(task: TaskDataClass)
    @Update
    suspend fun UpdateTask(task: TaskDataClass)
}