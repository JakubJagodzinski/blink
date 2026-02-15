package com.example.flashcards.ui.study

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.flashcards.data.Flashcard

class StudyViewModel : ViewModel() {

    private var flashcardsShuffled = listOf(
        Flashcard("dog", "pies"),
        Flashcard("cat", "kot"),
        Flashcard("house", "dom")
    ).shuffled()

    var currentIndex by mutableIntStateOf(0)
        private set

    var isFlipped by mutableStateOf(false)
        private set

    val currentFlashcard: Flashcard
        get() = flashcardsShuffled[currentIndex]

    val totalFlashcards: Int
        get() = flashcardsShuffled.size

    fun flipCard() {
        isFlipped = !isFlipped
    }

    fun nextCard() {
        if (currentIndex + 1 < flashcardsShuffled.size) {
            currentIndex++
        } else {
            flashcardsShuffled = flashcardsShuffled.shuffled()
            currentIndex = 0
        }
        isFlipped = false
    }

}
