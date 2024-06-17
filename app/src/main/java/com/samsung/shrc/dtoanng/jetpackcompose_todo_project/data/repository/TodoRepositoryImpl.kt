package com.samsung.shrc.dtoanng.jetpackcompose_todo_project.data.repository

import com.samsung.shrc.dtoanng.jetpackcompose_todo_project.data.TodoDatabase
import com.samsung.shrc.dtoanng.jetpackcompose_todo_project.data.mapper.toTodoTask
import com.samsung.shrc.dtoanng.jetpackcompose_todo_project.data.mapper.toTodoTaskEntity
import com.samsung.shrc.dtoanng.jetpackcompose_todo_project.domain.model.TodoTask
import com.samsung.shrc.dtoanng.jetpackcompose_todo_project.domain.repository.TodoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TodoRepositoryImpl @Inject constructor(private val todoDatabase: TodoDatabase) : TodoRepository {
    override suspend fun getAllTasks(): Flow<List<TodoTask>> {
        return flow {
            val toDoTaskEntities = todoDatabase.todoDao().getAllTasks()

            if (toDoTaskEntities.isNotEmpty()) {
                emit(toDoTaskEntities.map { it.toTodoTask() })
                return@flow
            }
        }
    }

    override suspend fun getSelectedTask(taskId: Int): Flow<TodoTask?> {
        return flow {
            val toDoTaskEntity = todoDatabase.todoDao().getSelectedTask(taskId)
            if (toDoTaskEntity == null) {
                emit(null)
            } else {
                emit(toDoTaskEntity.toTodoTask())
            }
            return@flow
        }
    }

    override suspend fun sortByLowPriority(): Flow<List<TodoTask>> {
        return flow {
            val toDoTaskEntities = todoDatabase.todoDao().sortByLowPriority()
            if (toDoTaskEntities.isNotEmpty()) {
                emit(toDoTaskEntities.map { it.toTodoTask() })
                return@flow
            }
        }
    }

    override suspend fun sortByHighPriority(): Flow<List<TodoTask>> {
        return flow {
            val toDoTaskEntities = todoDatabase.todoDao().sortByHighPriority()
            if (toDoTaskEntities.isNotEmpty()) {
                emit(toDoTaskEntities.map { it.toTodoTask() })
                return@flow
            }
        }
    }

    override suspend fun searchDatabase(search: String): Flow<List<TodoTask>> {
        return flow {
            val toDoTaskEntities = todoDatabase.todoDao().searchDatabase(search)
            if (toDoTaskEntities.isNotEmpty()) {
                emit(toDoTaskEntities.map { it.toTodoTask() })
                return@flow
            }
        }
    }

    override suspend fun deleteAllTasks() = todoDatabase.todoDao().deleteAllTasks()

    override suspend fun addTask(toDoTask: TodoTask) = todoDatabase.todoDao().addTask(toDoTask.toTodoTaskEntity())

    override suspend fun updateTask(toDoTask: TodoTask) = todoDatabase.todoDao().updateTask(toDoTask.toTodoTaskEntity())

    override suspend fun deleteTask(toDoTask: TodoTask) = todoDatabase.todoDao().deleteTask(toDoTask.toTodoTaskEntity())
}