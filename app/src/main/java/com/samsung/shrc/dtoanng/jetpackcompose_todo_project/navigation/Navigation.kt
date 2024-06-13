package com.samsung.shrc.dtoanng.jetpackcompose_todo_project.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.samsung.shrc.dtoanng.jetpackcompose_todo_project.navigation.destinations.listComposable
import com.samsung.shrc.dtoanng.jetpackcompose_todo_project.navigation.destinations.taskComposable
import com.samsung.shrc.dtoanng.jetpackcompose_todo_project.ui.SharedViewModel
import com.samsung.shrc.dtoanng.jetpackcompose_todo_project.util.Constants.LIST_SCREEN

@Composable
fun Navigation(navController: NavHostController, sharedViewModel: SharedViewModel) {
    val screen = remember(navController) {
        Screens(navController = navController)
    }

    NavHost(navController = navController, startDestination = LIST_SCREEN) {
        listComposable(
            navigateToTaskScreens = screen.task,
            sharedViewModel = sharedViewModel
        )
        taskComposable(
            navigateToListScreens = screen.list,
            sharedViewModel = sharedViewModel
        )
    }
}