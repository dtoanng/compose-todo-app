package com.samsung.shrc.dtoanng.jetpackcompose_todo_project.ui.screens.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.samsung.shrc.dtoanng.jetpackcompose_todo_project.R

@Composable
fun EmptyContent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Icon(
            modifier = Modifier.size(100.dp),
            painter = painterResource(id = R.drawable.ic_dissatisfied),
            contentDescription = stringResource(R.string.empty_todo_task),
            tint = Color.LightGray
        )
        Text(
            text = "No task found!",
            style = TextStyle(
                color = Color.LightGray,
                fontSize = MaterialTheme.typography.displaySmall.fontSize,
                fontWeight = FontWeight.Light
            )
        )

    }
}

@Composable
@Preview
fun EmptyContentPreview() {
    EmptyContent()
}