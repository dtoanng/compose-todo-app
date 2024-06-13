package com.samsung.shrc.dtoanng.jetpackcompose_todo_project.ui.screens.task

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.samsung.shrc.dtoanng.jetpackcompose_todo_project.domain.model.TodoTask
import com.samsung.shrc.dtoanng.jetpackcompose_todo_project.util.Action

@Composable
fun TaskScreen(
    selectedTask: TodoTask?,
    navigationToListScreen: (Action) -> Unit
) {
    Scaffold(
        topBar = {
            TaskAppBar(
                todoTask = selectedTask,
                navigationToListScreen = navigationToListScreen
            )
        },
        content = { padding ->
            Surface(
                modifier = Modifier.padding(padding)
            ) {

            }
        })
}