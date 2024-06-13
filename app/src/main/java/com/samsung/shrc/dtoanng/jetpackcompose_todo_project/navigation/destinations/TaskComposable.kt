package com.samsung.shrc.dtoanng.jetpackcompose_todo_project.navigation.destinations

import android.util.Log
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.samsung.shrc.dtoanng.jetpackcompose_todo_project.ui.SharedViewModel
import com.samsung.shrc.dtoanng.jetpackcompose_todo_project.ui.screens.task.TaskScreen
import com.samsung.shrc.dtoanng.jetpackcompose_todo_project.util.Action
import com.samsung.shrc.dtoanng.jetpackcompose_todo_project.util.Constants.TASK_ARGUMENT_KEY
import com.samsung.shrc.dtoanng.jetpackcompose_todo_project.util.Constants.TASK_SCREEN

fun NavGraphBuilder.taskComposable(
    navigateToListScreens: (Action) -> Unit,
    sharedViewModel: SharedViewModel
) {
    composable(
        route = TASK_SCREEN,
        arguments = listOf(navArgument(TASK_ARGUMENT_KEY) {
            type = NavType.IntType
        })
    ) { navBackStackEntry ->
        val taskId = navBackStackEntry.arguments!!.getInt(TASK_ARGUMENT_KEY)
        Log.d("toannguyen", "taskId: $taskId")
        sharedViewModel.getSelectedTask(taskId = taskId)

        val selectedTask = sharedViewModel.selectedTask.collectAsState()
        Log.d("toannguyen", "selectedTask: ${selectedTask.value}")
        TaskScreen(
            selectedTask = selectedTask.value,
            navigationToListScreen = navigateToListScreens
        )
    }
}