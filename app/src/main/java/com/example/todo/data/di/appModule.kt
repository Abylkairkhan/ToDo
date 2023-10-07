package com.example.todo.data.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.example.todo.data.TaskRepository
import com.example.todo.ui.detail.DetailViewModel
import com.example.todo.ui.main.MainViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel {
        MainViewModel(get())
    }

    viewModel {
        DetailViewModel(get())
    }

    single<TaskRepository> { TaskRepository(get()) }

    single<SharedPreferences> {
        provideSharedPref(androidApplication())
    }

}

const val KEY = "ToDo"
const val DB_NAME = "ListOfTasks"

fun provideSharedPref(app: Application): SharedPreferences {
    return app.applicationContext.getSharedPreferences(
        KEY,
        Context.MODE_PRIVATE
    )
}