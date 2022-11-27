package com.github.zhirnoov.julia.presentation.viewmodels

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.zhirnoov.julia.data.database.entity.CollectionEntity
import com.github.zhirnoov.julia.domain.UIState
import com.github.zhirnoov.julia.domain.usecases.collection.AddCollectionUseCase
import com.github.zhirnoov.julia.domain.usecases.collection.DeleteCollectionUseCase
import com.github.zhirnoov.julia.domain.usecases.collection.GetAllCollectionsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CollectionViewModel @Inject constructor(
    private val addCollectionUseCase: AddCollectionUseCase,
    private val getAllCollectionsUseCase: GetAllCollectionsUseCase,
    private val deleteCollectionUseCase: DeleteCollectionUseCase
) : ViewModel() {

    var collections = mutableStateListOf<CollectionEntity>()
    private val _uiState = MutableStateFlow<UIState>(UIState.Loading)
    val uiState = _uiState.asStateFlow()

    fun getAllCollections() {
        viewModelScope.launch {
            getAllCollectionsUseCase.execute().collect {
                if (it.isEmpty()) {
                    _uiState.value = UIState.Error
                } else {
                    collections.clear()
                    collections.addAll(it)
                    _uiState.value = UIState.Success(collections)
                }
            }
        }
    }

    fun addCollection(collection: CollectionEntity) = viewModelScope.launch {
        addCollectionUseCase.execute(collection)
        collections.add(collection)
        if (collections.isNotEmpty()) {
            _uiState.value = UIState.Success(collections)
        }
    }


    fun deleteCollection(collection : CollectionEntity) = viewModelScope.launch {
        collections.remove(collection)
        deleteCollectionUseCase.execute(collection = collection)
        if (collections.isEmpty()) {
            _uiState.value = UIState.Error
        }
    }

}