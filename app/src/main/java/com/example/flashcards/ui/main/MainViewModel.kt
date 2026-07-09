package com.example.flashcards.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flashcards.data.Category
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class MainUiState(
    val categories: List<Category> = emptyList(),
    val selectedCategory: Category? = null
)

@HiltViewModel
class MainViewModel @Inject constructor(
) : ViewModel() {

    private val _state = MutableStateFlow(
        MainUiState(
            categories = listOf(
                Category(1, "Matematyka"),
                Category(2, "Programowanie"),
                Category(3, "Historia")
            )
        )
    )

    val state = _state.asStateFlow()

    private val _events = MutableSharedFlow<MainEvent>()
    val events = _events.asSharedFlow()

    fun selectCategory(category: Category) {
        _state.value = _state.value.copy(
            selectedCategory = category
        )
    }

    fun enterStudy() {
        viewModelScope.launch {
            _events.emit(MainEvent.NavigateToStudy)
        }
    }
}

sealed class MainEvent {
    object NavigateToStudy : MainEvent()
}
