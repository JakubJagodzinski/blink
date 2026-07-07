package com.example.flashcards.data.database

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): FlashcardsDatabase {
        return Room.databaseBuilder(
            context,
            FlashcardsDatabase::class.java,
            "flashcardsDatabase"
        ).build()
    }

    @Provides
    fun provideDao(db: FlashcardsDatabase): FlashcardDao {
        return db.flashcardDao()
    }

}
