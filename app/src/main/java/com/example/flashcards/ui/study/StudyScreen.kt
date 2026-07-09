package com.example.flashcards.ui.study

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun StudyScreen(
    viewModel: StudyViewModel = hiltViewModel(),
    navController: NavController
) {
    val state = viewModel.state.collectAsState().value

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "${state.currentIndex + 1} / ${state.flashcards.size}",
                modifier = Modifier.padding(bottom = 16.dp)
            )

            LinearProgressIndicator(
                progress = {
                    (state.currentIndex + 1) / state.flashcards.size.toFloat()
                }
            )

            Spacer(modifier = Modifier.height(24.dp))

            FlashcardCard(
                flashcard = viewModel.getCurrentFlashcard(),
                isFlipped = state.isFlipped,
                onClick = { viewModel.flipCard() }
            )

            Spacer(modifier = Modifier.height(24.dp))

            Row {
                Button(
                    onClick = { viewModel.markAsKnown() },
                    enabled = state.alreadyFlipped
                ) {
                    Text("Known")
                }

                Spacer(modifier = Modifier.width(16.dp))

                Button(
                    onClick = { viewModel.markAsUnknown() },
                    enabled = state.alreadyFlipped
                ) {
                    Text("Unknown")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    navController.popBackStack()
                }
            ) {
                Text("Back to categories")
            }
        }
    }

}
