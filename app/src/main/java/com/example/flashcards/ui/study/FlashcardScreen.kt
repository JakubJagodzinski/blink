package com.example.flashcards.ui.study

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun FlashcardScreen(
    viewModel: StudyViewModel = viewModel()
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "${viewModel.currentIndex + 1} / ${viewModel.totalFlashcards}",
                modifier = Modifier.padding(bottom = 16.dp)
            )

            LinearProgressIndicator(
                progress = { (viewModel.currentIndex + 1) / viewModel.totalFlashcards.toFloat() },
                modifier = Modifier,
                color = ProgressIndicatorDefaults.linearColor,
                trackColor = ProgressIndicatorDefaults.linearTrackColor,
                strokeCap = ProgressIndicatorDefaults.LinearStrokeCap,
            )

            Spacer(modifier = Modifier.height(24.dp))

            FlashcardCard(
                flashcard = viewModel.currentFlashcard,
                isFlipped = viewModel.isFlipped,
                onClick = { viewModel.flipCard() }
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(onClick = { viewModel.nextCard() }) {
                Text("Next")
            }
        }
    }
}
