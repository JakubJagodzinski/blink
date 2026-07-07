package com.example.flashcards.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [FlashcardEntity::class],
    version = 1
)
abstract class FlashcardsDatabase : RoomDatabase() {

    abstract fun flashcardDao(): FlashcardDao

}
