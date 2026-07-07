package com.example.flashcards.ui.study

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flashcards.data.Flashcard
import com.example.flashcards.data.database.FlashcardRepository
import com.example.flashcards.data.database.toDomain
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class StudyUiState(

    val flashcards: List<Flashcard> = emptyList(),

    val currentIndex: Int = 0,

    val isFlipped: Boolean = false,

    val alreadyFlipped: Boolean = false

)

@HiltViewModel
class StudyViewModel @Inject constructor(
    private val repository: FlashcardRepository
) : ViewModel() {

    private var _state = MutableStateFlow(StudyUiState())
    val state = _state.asStateFlow()

    init {
        loadFlashcards()
    }

    private fun loadFlashcards() {
        viewModelScope.launch {
            repository.initializeDatabase()

            _state.update { it ->
                it.copy(
                    flashcards = repository
                        .getAllFlashcards()
                        .map { it.toDomain() }
                )
            }
        }
    }

    fun flipCard() {
        _state.update {
            it.copy(
                isFlipped = !it.isFlipped,
                alreadyFlipped = true
            )
        }
    }

    fun getCurrentFlashcard(): Flashcard? {
        return _state.value.flashcards.getOrNull(_state.value.currentIndex)
    }

    fun nextCard() {
        val currentIndex = _state.value.currentIndex
        val totalFlashcards = _state.value.flashcards.size

        if (currentIndex + 1 < totalFlashcards) {
            _state.update {
                it.copy(
                    currentIndex = it.currentIndex + 1,
                    isFlipped = false,
                    alreadyFlipped = false
                )
            }
        } else {
            _state.update {
                it.copy(
                    flashcards = it.flashcards.shuffled(),
                    currentIndex = 0,
                    isFlipped = false,
                    alreadyFlipped = false
                )
            }
        }
    }

}
