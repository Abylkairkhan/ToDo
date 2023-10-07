package com.example.todo.ui.main

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.example.todo.data.TaskRepository
import com.example.todo.domain.Task

class MainViewModel(
    private val repository: TaskRepository
): ViewModel() {

    private var taskList = repository.getData()

    fun loadData(): List<Task> {
        taskList = repository.getData()
        return taskList
    }

    fun deleteData(id: String) {

    }

    fun execute(){
        repository.readData()
        repository.getData().forEach {
            Log.d("MyLog", it.id + " " + it.title)
        }
    }
}