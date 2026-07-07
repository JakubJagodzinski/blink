package com.example.flashcards.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "flashcards")
data class FlashcardEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    val front: String,

    val back: String

)
