package com.samsung.shrc.dtoanng.jetpackcompose_todo_project.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import com.samsung.shrc.dtoanng.jetpackcompose_todo_project.data.model.Priority
import com.samsung.shrc.dtoanng.jetpackcompose_todo_project.ui.theme.PRIORITY_INDICATOR_SIZE
import com.samsung.shrc.dtoanng.jetpackcompose_todo_project.ui.theme.SMALL_PADDING

@Composable
fun PriorityItem(priority: Priority) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Canvas(
            modifier = Modifier.size(PRIORITY_INDICATOR_SIZE)
        ) {
            drawCircle(color = priority.color)
        }
        Spacer(modifier = Modifier.padding(SMALL_PADDING))
        Text(
            text = priority.name,
            style = TextStyle(
                color = MaterialTheme.colorScheme.inverseSurface,
                fontStyle = FontStyle.Normal
            ),
        )
    }
}

@Composable
@Preview
fun PriorityItemPreview() {
    PriorityItem(Priority.HIGH)
}