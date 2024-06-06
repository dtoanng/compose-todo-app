package com.samsung.shrc.dtoanng.jetpackcompose_todo_project.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.samsung.shrc.dtoanng.jetpackcompose_todo_project.data.model.ToDoTask

@Database(
    entities = [ToDoTask::class], version = 1, exportSchema = false
)
abstract class TodoDatabase : RoomDatabase() {
    abstract fun todoDao(): TodoDao
}