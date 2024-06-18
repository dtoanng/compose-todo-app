package com.samsung.shrc.dtoanng.jetpackcompose_todo_project.ui.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import com.samsung.shrc.dtoanng.jetpackcompose_todo_project.R

@Composable
fun DisplayAlertDialog(
    title: String,
    message: String,
    openDialog: Boolean,
    closeDialog: () -> Unit,
    onYesClicked: () -> Unit
) {
    if (openDialog) {
        AlertDialog(
            title = {
                Text(
                    text = title,
                    style = TextStyle(
                        fontWeight = MaterialTheme.typography.titleMedium.fontWeight,
                        fontSize = MaterialTheme.typography.titleMedium.fontSize,
                    )
                )
            },
            text = {
                Text(
                    text = message,
                    style = TextStyle(
                        fontWeight = MaterialTheme.typography.bodyMedium.fontWeight,
                        fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                    )
                )
            },
            onDismissRequest = {
                closeDialog()
            },
            confirmButton = {
                Button(
                    onClick = {
                        onYesClicked()
                        closeDialog()
                    }
                ) {
                    Text(text = stringResource(R.string.yes))
                }
            },
            dismissButton = {
                OutlinedButton(
                    onClick = {
                        closeDialog()
                    }
                ) {
                    Text(text = stringResource(R.string.no))
                }
            }
        )
    }
}