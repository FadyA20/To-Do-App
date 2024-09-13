package com.example.to_dolist

import android.app.Application
import androidx.compose.ui.window.application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TaskViewModel(application: Application): AndroidViewModel(application) {
    private val repository: TaskRepo
    val allTasks: LiveData<List<TaskDataClass>>

    init {
        val taskDao = taskdatabase.getdatabase(application).taskdao()
        repository = TaskRepo(taskDao)
        allTasks = repository.allTasks
    }

    fun addTask(taskDataClass: TaskDataClass) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(taskDataClass)
        }
    }

    fun deleteTask(taskDataClass: TaskDataClass) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.delete(taskDataClass)
        }
    }
    fun updatetask(taskDataClass: TaskDataClass) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.update(taskDataClass)
        }
    }
}