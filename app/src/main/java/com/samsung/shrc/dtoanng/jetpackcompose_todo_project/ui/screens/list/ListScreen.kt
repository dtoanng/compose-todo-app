package com.samsung.shrc.dtoanng.jetpackcompose_todo_project.ui.screens.list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.samsung.shrc.dtoanng.jetpackcompose_todo_project.R
import com.samsung.shrc.dtoanng.jetpackcompose_todo_project.ui.SharedViewModel
import com.samsung.shrc.dtoanng.jetpackcompose_todo_project.util.SearchAppBarState

@Composable
fun ListScreen(navigateToTaskScreens: (Int) -> Unit, sharedViewModel: SharedViewModel) {

    val searchAppBarState: SearchAppBarState by sharedViewModel.searchAppBarState
    val searchTextState: String by sharedViewModel.searchTextState

    Scaffold(
        topBar = {
            ListAppBar(
                sharedViewModel = sharedViewModel,
                searchAppBarState = searchAppBarState,
                searchTextState = searchTextState
            )
        },
        floatingActionButton = {
            ListFab(navigateToTaskScreens)
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        )
    }
}

@Composable
fun ListFab(navigateToTaskScreens: (Int) -> Unit) {
    SmallFloatingActionButton(
        onClick = { navigateToTaskScreens(-1) },
        containerColor = MaterialTheme.colorScheme.secondaryContainer,
        contentColor = MaterialTheme.colorScheme.secondary
    ) {
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = stringResource(R.string.add_button),
            tint = MaterialTheme.colorScheme.secondary,
        )
    }
}