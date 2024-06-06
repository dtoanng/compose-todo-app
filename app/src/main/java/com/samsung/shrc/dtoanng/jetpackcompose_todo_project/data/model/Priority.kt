package com.samsung.shrc.dtoanng.jetpackcompose_todo_project.data.model

import androidx.compose.ui.graphics.Color
import com.samsung.shrc.dtoanng.jetpackcompose_todo_project.ui.theme.HighPriorityColor
import com.samsung.shrc.dtoanng.jetpackcompose_todo_project.ui.theme.LowPriorityColor
import com.samsung.shrc.dtoanng.jetpackcompose_todo_project.ui.theme.MediumPriorityColor
import com.samsung.shrc.dtoanng.jetpackcompose_todo_project.ui.theme.NonePriorityColor

enum class Priority(val color: Color) {
    HIGH(HighPriorityColor),
    MEDIUM(MediumPriorityColor),
    LOW(LowPriorityColor),
    NONE(NonePriorityColor)
}