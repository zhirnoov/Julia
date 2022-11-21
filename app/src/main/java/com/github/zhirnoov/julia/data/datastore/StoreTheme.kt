package com.github.zhirnoov.julia.data.datastore

import android.content.Context
import androidx.compose.runtime.rememberCoroutineScope
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.*
import javax.inject.Inject
import kotlin.coroutines.coroutineContext

class StoreTheme @Inject constructor(private val context : Context) {

    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("theme")
        val THEME_STORE_KEY = booleanPreferencesKey("theme_store")
    }


    val getTheme : Flow<Boolean> = context.dataStore.data.map {
        it[THEME_STORE_KEY] ?: false
    }

    suspend fun saveTheme(isDarkTheme : Boolean) {
        context.dataStore.edit {
            it[THEME_STORE_KEY] = isDarkTheme
        }
    }

}