package com.samsung.shrc.dtoanng.jetpackcompose_todo_project.ui.screens.task

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.samsung.shrc.dtoanng.jetpackcompose_todo_project.R
import com.samsung.shrc.dtoanng.jetpackcompose_todo_project.data.model.Priority
import com.samsung.shrc.dtoanng.jetpackcompose_todo_project.domain.model.TodoTask
import com.samsung.shrc.dtoanng.jetpackcompose_todo_project.util.Action


@Composable
fun TaskAppBar(
    todoTask: TodoTask?,
    navigationToListScreen: (Action) -> Unit
) {
    if (todoTask == null) {
        NewTaskAppBar(navigationToListScreen = navigationToListScreen)
    } else {
        ExistingTaskAppBar(
            todoTask = todoTask,
            navigationToListScreen = navigationToListScreen
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewTaskAppBar(
    navigationToListScreen: (Action) -> Unit
) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(MaterialTheme.colorScheme.secondaryContainer),
        title = {
            Text(
                text = stringResource(R.string.add_new_task),
                style = TextStyle(
                    color = MaterialTheme.colorScheme.secondary,
                    fontWeight = FontWeight.Bold,
                    fontSize = MaterialTheme.typography.titleLarge.fontSize,
                )
            )
        },
        navigationIcon = {
            BackAction(onBackClicked = navigationToListScreen)
        },
        actions = {
            AddAction(addClicked = navigationToListScreen)
        }
    )
}

@Composable
fun BackAction(
    onBackClicked: (Action) -> Unit
) {

    IconButton(
        onClick = { onBackClicked(Action.NO_ACTION) }
    ) {
        Icon(
            imageVector = Icons.Filled.ArrowBackIosNew,
            contentDescription = stringResource(R.string.back_button),
            tint = MaterialTheme.colorScheme.secondary
        )
    }
}

@Composable
fun AddAction(
    addClicked: (Action) -> Unit
) {

    IconButton(
        onClick = { addClicked(Action.ADD) }
    ) {
        Icon(
            imageVector = Icons.Filled.Check,
            contentDescription = stringResource(R.string.add_task),
            tint = MaterialTheme.colorScheme.secondary
        )
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExistingTaskAppBar(
    todoTask: TodoTask,
    navigationToListScreen: (Action) -> Unit
) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(MaterialTheme.colorScheme.secondaryContainer),
        title = {
            Text(
                text = todoTask.title,
                style = TextStyle(
                    color = MaterialTheme.colorScheme.secondary,
                    fontWeight = FontWeight.Bold,
                    fontSize = MaterialTheme.typography.titleLarge.fontSize,
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        navigationIcon = {
            CloseAction(onBackClicked = navigationToListScreen)
        },
        actions = {
            DeleteAction(deleteClicked = navigationToListScreen)
            UpdateAction(updateClicked = navigationToListScreen)
        }
    )
}

@Composable
fun CloseAction(
    onBackClicked: (Action) -> Unit
) {

    IconButton(
        onClick = { onBackClicked(Action.NO_ACTION) }
    ) {
        Icon(
            imageVector = Icons.Filled.Close,
            contentDescription = stringResource(R.string.close_action),
            tint = MaterialTheme.colorScheme.secondary
        )
    }
}

@Composable
fun DeleteAction(
    deleteClicked: (Action) -> Unit
) {

    IconButton(
        onClick = { deleteClicked(Action.DELETE) }
    ) {
        Icon(
            imageVector = Icons.Filled.Delete,
            contentDescription = stringResource(R.string.remove_task),
            tint = MaterialTheme.colorScheme.secondary
        )
    }
}

@Composable
fun UpdateAction(
    updateClicked: (Action) -> Unit
) {

    IconButton(
        onClick = { updateClicked(Action.UPDATE) }
    ) {
        Icon(
            imageVector = Icons.Filled.Check,
            contentDescription = stringResource(R.string.update_task),
            tint = MaterialTheme.colorScheme.secondary
        )
    }
}

@Composable
@Preview
fun TaskBarPreview() {
    TaskAppBar(null, navigationToListScreen = {})
}

@Composable
@Preview
fun ExistingTaskBarPreview() {
    ExistingTaskAppBar(
        todoTask = TodoTask(
            id = 0,
            title = "Check email",
            description = "Check email and feedback to customer soon",
            Priority.MEDIUM
        ),
        navigationToListScreen = {}
    )
}