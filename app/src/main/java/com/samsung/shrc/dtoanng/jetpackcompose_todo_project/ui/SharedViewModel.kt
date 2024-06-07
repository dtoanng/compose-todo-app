package com.samsung.shrc.dtoanng.jetpackcompose_todo_project.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.samsung.shrc.dtoanng.jetpackcompose_todo_project.data.model.Priority
import com.samsung.shrc.dtoanng.jetpackcompose_todo_project.domain.model.TodoTask
import com.samsung.shrc.dtoanng.jetpackcompose_todo_project.domain.repository.TodoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(private val todoRepository: TodoRepository) : ViewModel() {

    private val _allTasks = MutableStateFlow<List<TodoTask>>(emptyList())
    val allTasks: StateFlow<List<TodoTask>> = _allTasks.asStateFlow()

    private val _selectedTask = MutableStateFlow(TodoTask(priority = Priority.NONE))
    val selectedTask = _selectedTask.asStateFlow()

    fun getAllTasks() {
        viewModelScope.launch {
            todoRepository.getAllTasks().collect {
                _allTasks.value = it
            }
        }
    }

    fun getSelectedTask(taskId: Int) {
        viewModelScope.launch {
            todoRepository.getSelectedTask(taskId).collect {
                _selectedTask.value = it
            }
        }
    }
}