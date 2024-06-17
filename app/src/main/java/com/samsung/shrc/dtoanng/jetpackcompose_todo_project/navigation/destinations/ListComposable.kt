package com.samsung.shrc.dtoanng.jetpackcompose_todo_project.navigation.destinations

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.samsung.shrc.dtoanng.jetpackcompose_todo_project.ui.SharedViewModel
import com.samsung.shrc.dtoanng.jetpackcompose_todo_project.ui.screens.list.ListScreen
import com.samsung.shrc.dtoanng.jetpackcompose_todo_project.util.Constants.LIST_ARGUMENT_KEY
import com.samsung.shrc.dtoanng.jetpackcompose_todo_project.util.Constants.LIST_SCREEN

fun NavGraphBuilder.listComposable(
    navigateToTaskScreen: (taskId: Int) -> Unit,
    sharedViewModel: SharedViewModel
) {
    composable(
        route = LIST_SCREEN,
        arguments = listOf(navArgument(LIST_ARGUMENT_KEY) {
            type = NavType.StringType
        })
    ) {
        ListScreen(navigateToTaskScreen = navigateToTaskScreen, sharedViewModel = sharedViewModel)
    }
}