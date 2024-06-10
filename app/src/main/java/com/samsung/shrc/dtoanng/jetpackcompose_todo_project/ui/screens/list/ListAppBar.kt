package com.samsung.shrc.dtoanng.jetpackcompose_todo_project.ui.screens.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.samsung.shrc.dtoanng.jetpackcompose_todo_project.R
import com.samsung.shrc.dtoanng.jetpackcompose_todo_project.data.model.Priority
import com.samsung.shrc.dtoanng.jetpackcompose_todo_project.ui.components.PriorityItem
import com.samsung.shrc.dtoanng.jetpackcompose_todo_project.ui.theme.ROUND_CONNER
import com.samsung.shrc.dtoanng.jetpackcompose_todo_project.ui.theme.topAppBarBgColor
import com.samsung.shrc.dtoanng.jetpackcompose_todo_project.ui.theme.topAppBarContentColor


@Composable
fun ListAppBar() {
    DefaultListAppBar(
        onSearchClicked = {},
        onSortClicked = {},
        onDeleteClicked = {}
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultListAppBar(
    onSearchClicked: () -> Unit,
    onSortClicked: (Priority) -> Unit,
    onDeleteClicked: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                stringResource(R.string.tasks),
                fontSize = MaterialTheme.typography.titleLarge.fontSize,
                color = topAppBarContentColor
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(topAppBarBgColor),
        actions = {
            ListAppBarActions(
                onSearchClicked = onSearchClicked,
                onSortClicked = onSortClicked,
                onDeleteClicked = onDeleteClicked
            )
        }
    )
}

@Composable
fun ListAppBarActions(
    onSearchClicked: () -> Unit,
    onSortClicked: (Priority) -> Unit,
    onDeleteClicked: () -> Unit
) {
    SearchAction(onSearchClicked = onSearchClicked)
    SortAction(onSortClicked = onSortClicked)
    DeleteAction(onDeleteClicked = onDeleteClicked)
}

@Composable
fun SearchAction(
    onSearchClicked: () -> Unit
) {
    IconButton(onClick = { onSearchClicked() }) {
        Icon(
            imageVector = Icons.Filled.Search,
            contentDescription = stringResource(R.string.search_tasks),
            tint = topAppBarContentColor
        )
    }
}

@Composable
fun SortAction(
    onSortClicked: (Priority) -> Unit
) {

    val expanded = remember {
        mutableStateOf(false)
    }

    IconButton(
        onClick = {
            expanded.value = true
        }
    ) {
        Icon(
            imageVector = Icons.Filled.FilterList,
            contentDescription = stringResource(R.string.filter_tasks),
            tint = topAppBarContentColor
        )

        MaterialTheme(shapes = MaterialTheme.shapes.copy(extraSmall = RoundedCornerShape(ROUND_CONNER))) {
            DropdownMenu(
                expanded = expanded.value,
                onDismissRequest = {
                    expanded.value = false
                },
                modifier = Modifier
                    .background(
                        color = MaterialTheme.colorScheme.inverseOnSurface,
                    )
                    .padding(all = 0.dp)
            ) {
                DropdownMenuItem(
                    text = { PriorityItem(priority = Priority.LOW) },
                    onClick = {
                        expanded.value = false
                        onSortClicked(Priority.LOW)
                    }
                )
                DropdownMenuItem(
                    text = { PriorityItem(priority = Priority.MEDIUM) },
                    onClick = {
                        expanded.value = false
                        onSortClicked(Priority.MEDIUM)
                    }
                )
                DropdownMenuItem(
                    text = { PriorityItem(priority = Priority.HIGH) },
                    onClick = {
                        expanded.value = false
                        onSortClicked(Priority.HIGH)
                    }
                )
            }
        }
    }
}

@Composable
fun DeleteAction(
    onDeleteClicked: () -> Unit
) {
    val expanded = remember {
        mutableStateOf(false)
    }

    IconButton(
        onClick = {
            expanded.value = true
        }
    ) {
        Icon(
            imageVector = Icons.Filled.MoreVert,
            contentDescription = stringResource(R.string.delete_all_tasks),
            tint = topAppBarContentColor
        )

        MaterialTheme(shapes = MaterialTheme.shapes.copy(extraSmall = RoundedCornerShape(ROUND_CONNER))) {
            DropdownMenu(
                expanded = expanded.value,
                onDismissRequest = {
                    expanded.value = false
                },
                modifier = Modifier.background(
                    color = MaterialTheme.colorScheme.inverseOnSurface
                )
            ) {
                DropdownMenuItem(
                    text = {
                        Text(
                            text = "Delete all tasks",
                            style = TextStyle(
                                color = MaterialTheme.colorScheme.inverseSurface,
                                fontStyle = FontStyle.Normal
                            ),
                        )
                    },
                    onClick = {
                        expanded.value = false
                        onDeleteClicked()
                    }
                )
            }
        }
    }
}

@Composable
@Preview
private fun DefaultListAppBarPreview() {
    DefaultListAppBar(
        onSearchClicked = {},
        onSortClicked = {},
        onDeleteClicked = {}
    )
}