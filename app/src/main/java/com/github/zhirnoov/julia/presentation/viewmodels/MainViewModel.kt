package com.github.zhirnoov.julia.presentation.viewmodels

import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.zhirnoov.julia.data.database.entity.CardEntity
import com.github.zhirnoov.julia.data.datastore.StoreTheme
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(storeTheme: StoreTheme) : ViewModel() {

    val uiState : StateFlow<MainUIState> = storeTheme.getTheme.map {
        MainUIState.Success(it)
    }.stateIn(
        scope = viewModelScope,
        initialValue = MainUIState.Loading,
        started = SharingStarted.WhileSubscribed(5_000)
    )
}

sealed class MainUIState {
    object Loading : MainUIState()
    data class Success(val isDarkTheme: Boolean) : MainUIState()
}