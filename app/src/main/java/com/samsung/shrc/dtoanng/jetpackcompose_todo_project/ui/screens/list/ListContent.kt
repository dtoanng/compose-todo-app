package com.samsung.shrc.dtoanng.jetpackcompose_todo_project.ui.screens.list

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.samsung.shrc.dtoanng.jetpackcompose_todo_project.data.model.Priority
import com.samsung.shrc.dtoanng.jetpackcompose_todo_project.domain.model.TodoTask
import com.samsung.shrc.dtoanng.jetpackcompose_todo_project.ui.theme.PRIORITY_INDICATOR_SIZE
import com.samsung.shrc.dtoanng.jetpackcompose_todo_project.ui.theme.ROUND_CONNER
import com.samsung.shrc.dtoanng.jetpackcompose_todo_project.util.RequestState
import com.samsung.shrc.dtoanng.jetpackcompose_todo_project.util.SearchAppBarState

@Composable
fun ListContent(
    allStates: RequestState<List<TodoTask>>,
    searchedState: RequestState<List<TodoTask>>,
    searchAppBarState: SearchAppBarState,
    navigateToTaskScreen: (taskId: Int) -> Unit
) {
    if (searchAppBarState == SearchAppBarState.TRIGGERED) {
        if (searchedState is RequestState.Success) {
            HandleListContent(
                tasks = searchedState.data,
                navigateToTaskScreen = navigateToTaskScreen
            )
        }
    } else {
        if (allStates is RequestState.Success) {
            HandleListContent(
                tasks = allStates.data,
                navigateToTaskScreen = navigateToTaskScreen
            )
        }
    }
}

@Composable
fun HandleListContent(
    tasks: List<TodoTask>,
    navigateToTaskScreen: (taskId: Int) -> Unit
) {
    if (tasks.isEmpty()) {
        EmptyContent()
    } else {
        DisplayTask(
            listTask = tasks,
            navigateToTaskScreen = navigateToTaskScreen
        )
    }
}

@Composable
fun DisplayTask(
    listTask: List<TodoTask>,
    navigateToTaskScreen: (taskId: Int) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxHeight(),
        contentPadding = PaddingValues(horizontal = 4.dp, vertical = 4.dp),
    ) {
        items(listTask.size) { index ->
            TaskItem(todoTask = listTask[index]) {
                navigateToTaskScreen(listTask[index].id)
            }
        }
    }
}

@Composable
fun TaskItem(
    todoTask: TodoTask,
    navigateToTaskScreen: (taskId: Int) -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(2.dp),
        color = MaterialTheme.colorScheme.inverseOnSurface,
        shape = RoundedCornerShape(ROUND_CONNER),
        shadowElevation = 2.0.dp,
        onClick = {
            navigateToTaskScreen(todoTask.id)
        }
    ) {
        Column(
            modifier = Modifier
                .padding(all = 16.0.dp)
                .fillMaxWidth()
                .border(shape = RoundedCornerShape(ROUND_CONNER), width = 1.dp, color = Color.Transparent)
        ) {
            Row(
                modifier = Modifier.padding(bottom = 5.0.dp)
            ) {
                Text(
                    modifier = Modifier.weight(10f),
                    text = todoTask.title,
                    style = TextStyle(
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        fontSize = MaterialTheme.typography.titleLarge.fontSize
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    contentAlignment = Alignment.CenterEnd
                ) {
                    Canvas(
                        modifier = Modifier.size(PRIORITY_INDICATOR_SIZE)
                    ) {
                        drawCircle(
                            color = todoTask.priority.color
                        )
                    }
                }
            }

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = todoTask.description,
                style = TextStyle(
                    color = Color.Black,
                    fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                    fontWeight = FontWeight.Light
                ),
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )
        }

    }
}

@Composable
@Preview
fun TaskItemPreview() {
    TaskItem(
        todoTask = TodoTask(0, "Do my homework", "Description here!", Priority.LOW),
        navigateToTaskScreen = {}
    )
}