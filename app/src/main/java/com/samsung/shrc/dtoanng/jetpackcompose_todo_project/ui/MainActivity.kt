package com.samsung.shrc.dtoanng.jetpackcompose_todo_project.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.samsung.shrc.dtoanng.jetpackcompose_todo_project.navigation.Navigation
import com.samsung.shrc.dtoanng.jetpackcompose_todo_project.ui.theme.TodoAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController
    private val sharedViewModel: SharedViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TodoAppTheme {
                navController = rememberNavController()
                Navigation(navController = navController, sharedViewModel)
            }
        }
    }
}
