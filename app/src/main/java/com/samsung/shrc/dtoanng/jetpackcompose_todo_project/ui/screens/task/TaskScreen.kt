package com.samsung.shrc.dtoanng.jetpackcompose_todo_project.ui.screens.task

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.samsung.shrc.dtoanng.jetpackcompose_todo_project.R
import com.samsung.shrc.dtoanng.jetpackcompose_todo_project.data.model.Priority
import com.samsung.shrc.dtoanng.jetpackcompose_todo_project.domain.model.TodoTask
import com.samsung.shrc.dtoanng.jetpackcompose_todo_project.ui.SharedViewModel
import com.samsung.shrc.dtoanng.jetpackcompose_todo_project.util.Action

@Composable
fun TaskScreen(
    selectedTask: TodoTask?,
    sharedViewModel: SharedViewModel,
    navigationToListScreen: (Action) -> Unit
) {

    val title: String by sharedViewModel.title
    val description: String by sharedViewModel.description
    val priority: Priority by sharedViewModel.priority

    val context = LocalContext.current

    Scaffold(
        topBar = {
            TaskAppBar(
                todoTask = selectedTask,
                navigationToListScreen = { action ->
                    if (action == Action.NO_ACTION) {
                        navigationToListScreen(action)
                    } else {
                        if (sharedViewModel.validateFields()) {
                            navigationToListScreen(action)
                        } else {
                            displayToast(context = context)
                        }
                    }
                }
            )
        },
        content = { padding ->
            Surface(
                modifier = Modifier.padding(padding)
            ) {
                TaskContent(
                    title = title,
                    onTitleChanged = {
                        sharedViewModel.updateTitle(it)
                    },
                    description = description,
                    onDescriptionChanged = {
                        sharedViewModel.description.value = it
                    },
                    priority = priority,
                    onPrioritySelected = {
                        sharedViewModel.priority.value = it
                    }
                )
            }
        }
    )
}

fun displayToast(context: Context) {
    Toast.makeText(context, context.getText(R.string.fields_empty), Toast.LENGTH_SHORT).show()
}