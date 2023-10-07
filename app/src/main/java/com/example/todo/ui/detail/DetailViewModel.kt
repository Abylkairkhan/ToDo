package com.example.todo.ui.detail

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.todo.data.TaskRepository
import com.example.todo.domain.Task
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.UUID

class DetailViewModel(
    private val repository: TaskRepository
): ViewModel() {

    fun saveTask(title: String, desc: String) {
        val date = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format( Date())
        Log.d("MyLog", date)
        repository.putData(
            Task(
                UUID.randomUUID().toString(),
                title,
                desc,
                false,
                date
            )
        )

    }

    fun deleteTask(id: String) {
        repository.deleteData(id)
    }
}