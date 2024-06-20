package com.samsung.shrc.dtoanng.jetpackcompose_todo_project.ui

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.samsung.shrc.dtoanng.jetpackcompose_todo_project.data.model.Priority
import com.samsung.shrc.dtoanng.jetpackcompose_todo_project.domain.model.TodoTask
import com.samsung.shrc.dtoanng.jetpackcompose_todo_project.domain.repository.DataStoreRepository
import com.samsung.shrc.dtoanng.jetpackcompose_todo_project.domain.repository.TodoRepository
import com.samsung.shrc.dtoanng.jetpackcompose_todo_project.util.Action
import com.samsung.shrc.dtoanng.jetpackcompose_todo_project.util.Constants.MAX_TITLE_LENGTH
import com.samsung.shrc.dtoanng.jetpackcompose_todo_project.util.RequestState
import com.samsung.shrc.dtoanng.jetpackcompose_todo_project.util.SearchAppBarState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(
    private val todoRepository: TodoRepository,
    private val dataStoreRepository: DataStoreRepository
) : ViewModel() {

    val action: MutableState<Action> = mutableStateOf(Action.NO_ACTION)

    var id by mutableIntStateOf(0)
        private set
    val title: MutableState<String> = mutableStateOf("")
    val description: MutableState<String> = mutableStateOf("")
    val priority: MutableState<Priority> = mutableStateOf(Priority.LOW)

    var searchAppBarState by mutableStateOf(SearchAppBarState.CLOSED)
        private set

    val searchTextState: MutableState<String> = mutableStateOf("")

    private val _allTasks = MutableStateFlow<RequestState<List<TodoTask>>>(RequestState.Idle)
    val allTasks: StateFlow<RequestState<List<TodoTask>>> = _allTasks

    private val _selectedTask: MutableStateFlow<TodoTask?> = MutableStateFlow(null)
    val selectedTask: StateFlow<TodoTask?> = _selectedTask.asStateFlow()

    private val _searchedTasks = MutableStateFlow<RequestState<List<TodoTask>>>(RequestState.Idle)
    val searchedTask: StateFlow<RequestState<List<TodoTask>>> = _searchedTasks

    private val _sortState = MutableStateFlow<RequestState<Priority>>(RequestState.Idle)
    val sortState: StateFlow<RequestState<Priority>> = _sortState

    init {
        readSortState()
    }

    fun getAllTasks() {
        _allTasks.value = RequestState.Loading
        try {
            viewModelScope.launch {
                todoRepository.getAllTasks().collect {
                    _allTasks.value = RequestState.Success(it)
                }
            }
        } catch (exception: Exception) {
            _allTasks.value = RequestState.Error(exception)
        }
    }

    fun getSelectedTask(taskId: Int) {
        viewModelScope.launch {
            todoRepository.getSelectedTask(taskId).collectLatest {
                _selectedTask.value = it
            }
        }
    }

    fun searchData(searchQuery: String) {
        _searchedTasks.value = RequestState.Loading
        try {
            viewModelScope.launch {
                todoRepository.searchDatabase(search = "%$searchQuery%").collect { result ->
                    _searchedTasks.value = RequestState.Success(data = result)
                }
            }
        } catch (e: Exception) {
            _searchedTasks.value = RequestState.Error(error = e)
        }

        searchAppBarState = SearchAppBarState.TRIGGERED
    }

    fun persistSortState(priority: Priority) {
        viewModelScope.launch(Dispatchers.IO) {
            dataStoreRepository.persistSortState(priority = priority)
        }
    }

    private fun readSortState() {
        _sortState.value = RequestState.Loading
        try {
            viewModelScope.launch {
                dataStoreRepository.readSortStore
                    .map { Priority.valueOf(it) }
                    .collect {
                        _sortState.value = RequestState.Success(it)
                    }
            }
        } catch (e: Exception) {
            _sortState.value = RequestState.Error(e)
        }
    }

    val lowPriorityTasks: StateFlow<List<TodoTask>> = todoRepository.sortByLowPriority().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = emptyList()
    )

    val highPriorityTasks: StateFlow<List<TodoTask>> = todoRepository.sortByHighPriority().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = emptyList()
    )

    fun handleActions(action: Action) {
        when (action) {
            Action.ADD -> {
                addTask()
            }

            Action.UPDATE -> {
                updateTask()
            }

            Action.DELETE -> {
                deleteTask()
            }

            Action.DELETE_ALL -> {
                deleteAllTasks()
            }

            Action.UNDO -> {
                addTask()
            }

            else -> {
                //todo: has nothing to implement now
            }
        }

        this.action.value = Action.NO_ACTION
    }

    private fun addTask() {
        viewModelScope.launch(Dispatchers.IO) {
            val todoTask = TodoTask(
                id = 0,
                title = title.value,
                description = description.value,
                priority = priority.value
            )

            todoRepository.addTask(todoTask)
        }

        searchAppBarState = SearchAppBarState.CLOSED
    }

    private fun updateTask() {
        viewModelScope.launch(Dispatchers.IO) {

            val todoTask = TodoTask(
                id = id,
                title = title.value,
                description = description.value,
                priority = priority.value
            )

            todoRepository.updateTask(todoTask)
        }
    }

    private fun deleteTask() {
        viewModelScope.launch(Dispatchers.IO) {
            val todoTask = TodoTask(
                id = id,
                title = title.value,
                description = description.value,
                priority = priority.value
            )

            todoRepository.deleteTask(todoTask)
        }
    }

    private fun deleteAllTasks() {
        viewModelScope.launch(Dispatchers.IO) {
            todoRepository.deleteAllTasks()
        }
    }

    fun updateTaskFields(selectedTask: TodoTask?) {
        if (selectedTask != null) {
            // case: selected a task
            id = selectedTask.id
            title.value = selectedTask.title
            description.value = selectedTask.description
            priority.value = selectedTask.priority
        } else {
            // case: add new task
            id = 0
            title.value = ""
            description.value = ""
            priority.value = Priority.LOW
        }
    }

    fun updateTitle(titleTask: String) {
        if (titleTask.length < MAX_TITLE_LENGTH) {
            title.value = titleTask
        }
    }

    fun updateSearchAppBarState(newSearchAppBarState: SearchAppBarState) {
        searchAppBarState = newSearchAppBarState
    }

    fun validateFields(): Boolean {
        return title.value.isNotEmpty() && description.value.isNotEmpty()
    }
}