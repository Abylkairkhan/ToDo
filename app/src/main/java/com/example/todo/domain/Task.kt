package com.example.todo.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Task(
    val id: String,
    val title: String,
    val description: String,
    val done: Boolean = false,
    val createdAt: String
): Parcelable
