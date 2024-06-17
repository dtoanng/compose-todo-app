package com.samsung.shrc.dtoanng.jetpackcompose_todo_project.data.mapper

import com.samsung.shrc.dtoanng.jetpackcompose_todo_project.data.model.ToDoTaskEntity
import com.samsung.shrc.dtoanng.jetpackcompose_todo_project.domain.model.TodoTask

fun ToDoTaskEntity.toTodoTask() = TodoTask(
    id = id,
    title = title,
    description = description,
    priority = priority
)

fun TodoTask.toTodoTaskEntity() = ToDoTaskEntity(
    title = title,
    description = description,
    priority = priority
)