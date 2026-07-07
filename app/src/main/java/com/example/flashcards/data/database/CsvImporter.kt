package com.example.flashcards.data.database

import android.content.Context

object CsvImporter {

    fun loadFlashcards(
        context: Context,
        fileName: String
    ): List<FlashcardEntity> {
        return context.assets.open(fileName)
            .bufferedReader()
            .readLines()
            .mapNotNull { line ->
                val parts = line.split(",")

                if (parts.size < 2) {
                    null
                } else {
                    FlashcardEntity(
                        front = parts[0].trim(),
                        back = parts[1].trim()
                    )
                }
            }
    }

}
