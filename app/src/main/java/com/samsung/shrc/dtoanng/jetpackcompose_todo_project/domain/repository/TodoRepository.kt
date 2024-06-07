package com.samsung.shrc.dtoanng.jetpackcompose_todo_project.domain.repository

import com.samsung.shrc.dtoanng.jetpackcompose_todo_project.domain.model.TodoTask
import kotlinx.coroutines.flow.Flow

interface TodoRepository {
    suspend fun getAllTasks(): Flow<List<TodoTask>>
    suspend fun getSelectedTask(taskId: Int): Flow<TodoTask>
    suspend fun sortByLowPriority(): Flow<List<TodoTask>>
    suspend fun sortByHighPriority(): Flow<List<TodoTask>>
    suspend fun searchDatabase(search: String): Flow<List<TodoTask>>
    suspend fun deleteAllTasks()
    suspend fun addTask(toDoTask: TodoTask)
    suspend fun updateTask(toDoTask: TodoTask)
    suspend fun deleteTask(toDoTask: TodoTask)
}