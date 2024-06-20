package com.samsung.shrc.dtoanng.jetpackcompose_todo_project.ui.screens.list

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.samsung.shrc.dtoanng.jetpackcompose_todo_project.R
import com.samsung.shrc.dtoanng.jetpackcompose_todo_project.data.model.Priority
import com.samsung.shrc.dtoanng.jetpackcompose_todo_project.domain.model.TodoTask
import com.samsung.shrc.dtoanng.jetpackcompose_todo_project.ui.theme.PRIORITY_INDICATOR_SIZE
import com.samsung.shrc.dtoanng.jetpackcompose_todo_project.ui.theme.ROUND_CONNER
import com.samsung.shrc.dtoanng.jetpackcompose_todo_project.util.Action
import com.samsung.shrc.dtoanng.jetpackcompose_todo_project.util.RequestState
import com.samsung.shrc.dtoanng.jetpackcompose_todo_project.util.SearchAppBarState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun ListContent(
    modifier: Modifier = Modifier,
    allStates: RequestState<List<TodoTask>>,
    searchedState: RequestState<List<TodoTask>>,
    lowPriorityTasks: List<TodoTask>,
    highPriorityTasks: List<TodoTask>,
    sortState: RequestState<Priority>,
    searchAppBarState: SearchAppBarState,
    onSwipeToDelete: (Action, TodoTask) -> Unit,
    navigateToTaskScreen: (taskId: Int) -> Unit
) {
    if (sortState is RequestState.Success) {
        when {
            searchAppBarState == SearchAppBarState.TRIGGERED -> {
                if (searchedState is RequestState.Success) {
                    HandleListContent(
                        modifier = modifier,
                        tasks = searchedState.data,
                        onSwipeToDelete = onSwipeToDelete,
                        navigateToTaskScreen = navigateToTaskScreen
                    )
                }
            }

            sortState.data == Priority.NONE -> {
                if (allStates is RequestState.Success) {
                    HandleListContent(
                        modifier = modifier,
                        tasks = allStates.data,
                        onSwipeToDelete = onSwipeToDelete,
                        navigateToTaskScreen = navigateToTaskScreen
                    )
                }
            }

            sortState.data == Priority.LOW -> {
                HandleListContent(
                    modifier = modifier,
                    tasks = lowPriorityTasks,
                    onSwipeToDelete = onSwipeToDelete,
                    navigateToTaskScreen = navigateToTaskScreen
                )
            }

            sortState.data == Priority.HIGH -> {
                HandleListContent(
                    modifier = modifier,
                    tasks = highPriorityTasks,
                    onSwipeToDelete = onSwipeToDelete,
                    navigateToTaskScreen = navigateToTaskScreen
                )
            }
        }
    }
}

@Composable
fun HandleListContent(
    modifier: Modifier = Modifier,
    tasks: List<TodoTask>,
    onSwipeToDelete: (Action, TodoTask) -> Unit,
    navigateToTaskScreen: (taskId: Int) -> Unit
) {
    if (tasks.isEmpty()) {
        EmptyContent()
    } else {
        DisplayTask(
            modifier = modifier,
            listTask = tasks,
            onSwipeToDelete = onSwipeToDelete,
            navigateToTaskScreen = navigateToTaskScreen
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DisplayTask(
    modifier: Modifier = Modifier,
    listTask: List<TodoTask>,
    onSwipeToDelete: (Action, TodoTask) -> Unit,
    navigateToTaskScreen: (taskId: Int) -> Unit
) {
    LazyColumn(
        modifier = modifier,
    ) {
        items(
            items = listTask,
            key = { task ->
                task.id
            }
        ) { task ->
            val dismissState = rememberSwipeToDismissBoxState()
            val dismissDirection = dismissState.dismissDirection
            val isDismissed = dismissState.dismissDirection == SwipeToDismissBoxValue.EndToStart && dismissState.progress == 1f

            if (isDismissed && dismissDirection == SwipeToDismissBoxValue.EndToStart) {
                val scope = rememberCoroutineScope()
                SideEffect {
                    scope.launch {
                        delay(300)
                        onSwipeToDelete(Action.DELETE, task)
                    }
                }
            }

            val degrees by animateFloatAsState(
                if (dismissState.progress in 0f..0.5f) 0f else -45f,
                label = "Degree animation"
            )

            var itemAppeared by remember { mutableStateOf(false) }
            LaunchedEffect(key1 = true) {
                itemAppeared = true
            }

            AnimatedVisibility(
                visible = itemAppeared && !isDismissed,
                enter = expandVertically(
                    animationSpec = tween(
                        durationMillis = 300
                    )
                ),
                exit = shrinkVertically(
                    animationSpec = tween(
                        durationMillis = 300
                    )
                )
            ) {
                SwipeToDismissBox(
                    state = dismissState,
                    backgroundContent = { SwipeBackground(degrees = degrees) }
                ) {
                    TaskItem(
                        todoTask = task,
                        navigateToTaskScreen = navigateToTaskScreen
                    )
                }
            }
        }
    }
}

@Composable
fun SwipeBackground(degrees: Float) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.secondaryContainer)
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            modifier = Modifier.rotate(degrees),
            imageVector = Icons.Filled.Delete,
            contentDescription = stringResource(id = R.string.remove),
            tint = MaterialTheme.colorScheme.secondary
        )
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