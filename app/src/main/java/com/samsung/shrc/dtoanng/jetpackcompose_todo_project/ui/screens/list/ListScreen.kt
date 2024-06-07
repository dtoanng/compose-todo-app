package com.samsung.shrc.dtoanng.jetpackcompose_todo_project.ui.screens.list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.samsung.shrc.dtoanng.jetpackcompose_todo_project.R

@Composable
fun ListScreen(navigateToTaskScreens: (Int) -> Unit) {
    Scaffold(
        floatingActionButton = {
            ListFab(navigateToTaskScreens)
        }
    ) {
        Box(modifier = Modifier.fillMaxSize().padding(it))
    }
}

@Composable
fun ListFab(navigateToTaskScreens: (Int) -> Unit) {
    FloatingActionButton(onClick = { navigateToTaskScreens(-1) }) {
        Icon(imageVector = Icons.Filled.Add, contentDescription = stringResource(R.string.add_button), tint = Color.White)
    }
}

@Composable
@Preview
private fun ListScreenPreview() {
    ListScreen(navigateToTaskScreens = {})
}