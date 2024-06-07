package com.samsung.shrc.dtoanng.jetpackcompose_todo_project.navigation.destinations

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.samsung.shrc.dtoanng.jetpackcompose_todo_project.util.Action
import com.samsung.shrc.dtoanng.jetpackcompose_todo_project.util.Constants.TASK_ARGUMENT_KEY
import com.samsung.shrc.dtoanng.jetpackcompose_todo_project.util.Constants.TASK_SCREEN

fun NavGraphBuilder.taskComposable(
    navigateToListScreens: (Action) -> Unit
) {
    composable(
        route = TASK_SCREEN,
        arguments = listOf(navArgument(TASK_ARGUMENT_KEY) {
            type = NavType.StringType
        })
    ) {

    }
}