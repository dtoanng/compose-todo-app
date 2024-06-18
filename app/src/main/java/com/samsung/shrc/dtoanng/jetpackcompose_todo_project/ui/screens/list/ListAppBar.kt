package com.samsung.shrc.dtoanng.jetpackcompose_todo_project.ui.screens.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.samsung.shrc.dtoanng.jetpackcompose_todo_project.R
import com.samsung.shrc.dtoanng.jetpackcompose_todo_project.data.model.Priority
import com.samsung.shrc.dtoanng.jetpackcompose_todo_project.ui.SharedViewModel
import com.samsung.shrc.dtoanng.jetpackcompose_todo_project.ui.components.PriorityItem
import com.samsung.shrc.dtoanng.jetpackcompose_todo_project.ui.theme.ROUND_CONNER
import com.samsung.shrc.dtoanng.jetpackcompose_todo_project.ui.theme.TOP_APPBAR_HEIGHT
import com.samsung.shrc.dtoanng.jetpackcompose_todo_project.util.Action
import com.samsung.shrc.dtoanng.jetpackcompose_todo_project.util.SearchAppBarState
import com.samsung.shrc.dtoanng.jetpackcompose_todo_project.util.TrailingIconState


@Composable
fun ListAppBar(
    sharedViewModel: SharedViewModel,
    searchAppBarState: SearchAppBarState,
    searchTextState: String
) {

    when (searchAppBarState) {
        SearchAppBarState.CLOSED -> {
            DefaultListAppBar(
                onSearchClicked = { sharedViewModel.searchAppBarState.value = SearchAppBarState.OPENED },
                onSortClicked = {},
                onDeleteClicked = { sharedViewModel.action.value = Action.DELETE_ALL }
            )
        }

        else -> {
            SearchAppBar(
                text = searchTextState,
                onTextChange = { searchTxt ->
                    sharedViewModel.searchTextState.value = searchTxt
                },
                onCloseClicked = { sharedViewModel.searchAppBarState.value = SearchAppBarState.CLOSED },
                onSearchClicked = {}
            )
        }
    }
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
                color = MaterialTheme.colorScheme.secondary,
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(MaterialTheme.colorScheme.secondaryContainer),
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
            tint = MaterialTheme.colorScheme.secondary
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
            tint = MaterialTheme.colorScheme.secondary
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
            tint = MaterialTheme.colorScheme.secondary
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
                            text = stringResource(R.string.delete_all_tasks),
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
fun SearchAppBar(
    text: String,
    onTextChange: (String) -> Unit,
    onCloseClicked: () -> Unit,
    onSearchClicked: () -> Unit
) {

    var trailingIconsState by remember {
        mutableStateOf(TrailingIconState.READY_TO_DELETE)
    }

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(TOP_APPBAR_HEIGHT),
        color = MaterialTheme.colorScheme.secondaryContainer,
        contentColor = MaterialTheme.colorScheme.secondary
    ) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = text,
            onValueChange = {
                onTextChange(it)
            },
            placeholder = {
                Text(
                    modifier = Modifier.alpha(0.3F),
                    text = stringResource(R.string.search_txt),
                    style = TextStyle(
                        color = MaterialTheme.colorScheme.secondary,
                        fontSize = MaterialTheme.typography.bodyLarge.fontSize
                    )
                )
            },
            textStyle = TextStyle(
                color = MaterialTheme.colorScheme.secondary,
                fontSize = MaterialTheme.typography.bodyLarge.fontSize
            ),
            singleLine = true,
            leadingIcon = {
                IconButton(onClick = { onSearchClicked() }) {
                    Icon(
                        modifier = Modifier.alpha(0.3F),
                        imageVector = Icons.Filled.Search,
                        contentDescription = stringResource(R.string.search_icon),
                        tint = MaterialTheme.colorScheme.secondary
                    )
                }
            },
            trailingIcon = {
                IconButton(
                    onClick = {
                        when (trailingIconsState) {
                            TrailingIconState.READY_TO_DELETE -> {
                                onTextChange("")
                                trailingIconsState = TrailingIconState.READY_TO_CLOSE
                            }

                            TrailingIconState.READY_TO_CLOSE -> {
                                if (text.isNotEmpty()) {
                                    onTextChange("")
                                } else {
                                    onCloseClicked()
                                    trailingIconsState = TrailingIconState.READY_TO_DELETE
                                }
                            }
                        }
                    }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = stringResource(R.string.close_icon),
                        tint = MaterialTheme.colorScheme.secondary
                    )
                }
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(onSearch = { onSearchClicked() }),
            colors = TextFieldDefaults.colors(
                cursorColor = MaterialTheme.colorScheme.secondary,
                focusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            )
        )
    }
}

@Composable
@Preview
fun SearchAppBarPreview() {
    SearchAppBar(text = "", onTextChange = {}, onCloseClicked = {}, onSearchClicked = {})
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