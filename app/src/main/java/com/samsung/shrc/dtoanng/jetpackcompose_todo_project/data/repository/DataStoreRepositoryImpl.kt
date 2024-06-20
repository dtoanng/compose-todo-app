package com.samsung.shrc.dtoanng.jetpackcompose_todo_project.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.IOException
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.samsung.shrc.dtoanng.jetpackcompose_todo_project.data.model.Priority
import com.samsung.shrc.dtoanng.jetpackcompose_todo_project.domain.repository.DataStoreRepository
import com.samsung.shrc.dtoanng.jetpackcompose_todo_project.util.Constants.PREFERENCE_KEY
import com.samsung.shrc.dtoanng.jetpackcompose_todo_project.util.Constants.PREFERENCE_NAME
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject


val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = PREFERENCE_NAME)

class DataStoreRepositoryImpl @Inject constructor(@ApplicationContext private val context: Context): DataStoreRepository {

    private object PreferenceKeys {
        val sortKey = stringPreferencesKey(name = PREFERENCE_KEY)
    }

    private val dataStore = context.dataStore

    override suspend fun persistSortState(priority: Priority) {
        dataStore.edit { preference ->
            preference[PreferenceKeys.sortKey] = priority.name
        }
    }

    override val readSortStore: Flow<String> = dataStore.data.catch { exception ->
        if (exception is IOException) {
            emit(emptyPreferences())
        } else {
            throw exception
        }
    }.map { preference ->
        val sortState = preference[PreferenceKeys.sortKey] ?: Priority.NONE.name
        sortState
    }
}