package com.example.to_dolist

import androidx.lifecycle.LiveData

class TaskRepo(private val taskdao: TaskDao) {
    val allTasks: LiveData<List<TaskDataClass>> = taskdao.getAllTasks()
    suspend fun insert(task: TaskDataClass) {
        taskdao.insert(task)
    }
    suspend fun update(task: TaskDataClass) {
        taskdao.UpdateTask(task)
    }
    suspend fun delete(task: TaskDataClass) {
        taskdao.deleteTask(task)
    }
}