package com.example.flashcards.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.flashcards.ui.main.MainScreen
import com.example.flashcards.ui.study.StudyScreen

@Composable
fun MyApp() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "main"
    ) {

        composable("main") {
            MainScreen(
                navController = navController
            )
        }

        composable("study") {
            StudyScreen(
                navController = navController
            )
        }
    }

}
