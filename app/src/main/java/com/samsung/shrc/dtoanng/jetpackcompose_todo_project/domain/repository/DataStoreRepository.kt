package com.samsung.shrc.dtoanng.jetpackcompose_todo_project.domain.repository

import com.samsung.shrc.dtoanng.jetpackcompose_todo_project.data.model.Priority
import kotlinx.coroutines.flow.Flow

interface DataStoreRepository {
    val readSortStore: Flow<String>
    suspend fun persistSortState(priority: Priority)
}