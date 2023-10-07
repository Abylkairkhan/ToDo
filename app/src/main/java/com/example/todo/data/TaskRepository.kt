package com.example.todo.data

import android.content.SharedPreferences
import android.util.Log
import com.example.todo.domain.Task
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

const val DB_NAME = "ListOfTasks"

class TaskRepository(
    private val sharedPref: SharedPreferences
) {
    private var taskList = ArrayList<Task>()

    private fun saveData() {
        val editor = sharedPref.edit()
        val gson = Gson()
        val json = gson.toJson(taskList)
        editor?.putString(DB_NAME, json)
        editor?.apply()
    }

    fun deleteData(id: String) {
        taskList.forEach { task: Task ->
            if (task.id == id) {
                taskList.remove(task)
            }
        }
        saveData()
    }

    fun putData(task: Task) {
        taskList.add(task)
        saveData()
    }

    fun readData() {
        val gson = Gson()
        val type = object : TypeToken<List<Task>>() {}.type
        val json = sharedPref.getString(DB_NAME, null)
        if (json != null) {
            taskList = gson.fromJson(json, type)
            Log.d("MyLog", "emptyJSON")
        } else Log.d("MyLog", "null")
    }

    fun getData(): List<Task> {
        readData()
        return taskList
    }
}