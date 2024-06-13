package com.samsung.shrc.dtoanng.jetpackcompose_todo_project.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.samsung.shrc.dtoanng.jetpackcompose_todo_project.data.model.ToDoTaskEntity

@Dao
interface TodoDao {

    @Query("SELECT * FROM todo_table ORDER BY id ASC")
    suspend fun getAllTasks(): List<ToDoTaskEntity>

    @Query("SELECT * FROM todo_table WHERE id=:taskId")
    suspend fun getSelectedTask(taskId: Int): ToDoTaskEntity?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addTask(toDoTaskEntity: ToDoTaskEntity)

    @Update
    suspend fun updateTask(toDoTaskEntity: ToDoTaskEntity)

    @Delete
    suspend fun deleteTask(toDoTaskEntity: ToDoTaskEntity)

    @Query("DELETE FROM todo_table")
    suspend fun deleteAllTasks()

    @Query("SELECT * FROM todo_table WHERE title LIKE :searchQuery OR description LIKE :searchQuery")
    suspend fun searchDatabase(searchQuery: String): List<ToDoTaskEntity>

    @Query("SELECT * FROM todo_table ORDER BY CASE WHEN priority LIKE 'L%' THEN 1 WHEN priority LIKE 'M%' THEN 2 WHEN priority LIKE 'H%' THEN 3 END")
    suspend fun sortByLowPriority(): List<ToDoTaskEntity>

    @Query("SELECT * FROM todo_table ORDER BY CASE WHEN priority LIKE 'H%' THEN 1 WHEN priority LIKE 'M%' THEN 2 WHEN priority LIKE 'L%' THEN 3 END")
    suspend fun sortByHighPriority(): List<ToDoTaskEntity>
}