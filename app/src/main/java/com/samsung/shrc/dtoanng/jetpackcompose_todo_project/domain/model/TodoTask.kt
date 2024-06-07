package com.samsung.shrc.dtoanng.jetpackcompose_todo_project.domain.model

import com.samsung.shrc.dtoanng.jetpackcompose_todo_project.data.model.Priority

data class TodoTask(
    val id: Int = 0,
    val title: String = "",
    val description: String = "",
    val priority: Priority?= null
)