package com.github.zhirnoov.julia.domain

import androidx.compose.runtime.snapshots.SnapshotStateList
import com.github.zhirnoov.julia.data.database.entity.CardEntity
import com.github.zhirnoov.julia.data.database.entity.CollectionEntity


sealed class UIState {
    object Loading : UIState()
    object Error : UIState()
    data class Success(val collections: SnapshotStateList<CollectionEntity>) : UIState()
}

sealed class UIStateCards {
    object Loading : UIStateCards()
    object Error : UIStateCards()
    data class Success(val cards: SnapshotStateList<CardEntity>) : UIStateCards()
}