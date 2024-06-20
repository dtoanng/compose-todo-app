package com.samsung.shrc.dtoanng.jetpackcompose_todo_project.di

import com.samsung.shrc.dtoanng.jetpackcompose_todo_project.data.repository.DataStoreRepositoryImpl
import com.samsung.shrc.dtoanng.jetpackcompose_todo_project.data.repository.TodoRepositoryImpl
import com.samsung.shrc.dtoanng.jetpackcompose_todo_project.domain.repository.DataStoreRepository
import com.samsung.shrc.dtoanng.jetpackcompose_todo_project.domain.repository.TodoRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindTodoTasks(todoRepositoryImpl: TodoRepositoryImpl): TodoRepository

    @Binds
    @Singleton
    abstract fun bindDataStore(dataStoreRepository: DataStoreRepositoryImpl): DataStoreRepository
}