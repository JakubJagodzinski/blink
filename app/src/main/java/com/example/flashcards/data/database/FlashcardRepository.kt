package com.example.flashcards.data.database

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class FlashcardRepository @Inject constructor(
    private val dao: FlashcardDao,
    @param:ApplicationContext private val context: Context
) {

    suspend fun getAllFlashcards(): List<FlashcardEntity> {
        return dao.getAll()
    }

    suspend fun initializeDatabase() {
        if (dao.count() > 0) return

        val cards = CsvImporter.loadFlashcards(
            context,
            "dict.csv"
        )

        dao.insertAll(cards)
    }

}
