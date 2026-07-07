package com.example.flashcards.data.database

import com.example.flashcards.data.Flashcard

fun FlashcardEntity.toDomain(): Flashcard {
    return Flashcard(
        front = front,
        back = back
    )
}
