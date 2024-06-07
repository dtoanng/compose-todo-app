package com.samsung.shrc.dtoanng.jetpackcompose_todo_project.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.samsung.shrc.dtoanng.jetpackcompose_todo_project.navigation.Navigation
import com.samsung.shrc.dtoanng.jetpackcompose_todo_project.ui.theme.TodoAppTheme

class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TodoAppTheme {
                navController = rememberNavController()
                Navigation(navController = navController)
            }
        }
    }
}
