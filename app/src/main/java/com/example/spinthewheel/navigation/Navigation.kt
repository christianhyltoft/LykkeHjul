package com.example.spinthewheel.navigation

import com.example.spinthewheel.screens.GuessTheWordScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.spinthewheel.screens.Screen

import com.example.spinthewheel.screens.WinScreen
import com.example.spinthewheel.screens.LooseScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController,
        startDestination = Screen.GuessScreen.route) {
        composable(route = Screen.GuessScreen.route) {
            GuessScreen(navController = navController)
        }
        composable(route = Screen.WinScreen.route) {
            WinScreen(navController = navController)
        }
        composable(route = Screen.LoseScreen.route) {
            LooseScreen(navController = navController)
        }
        composable(route = Screen.SpinScreen.route) {
            SpinScreen(navController = navController)
        }
    }
}

@Composable
fun GuessScreen(navController: NavHostController) {
    GuessTheWordScreen(navController = navController)
}

@Composable
fun SpinScreen(navController: NavHostController) {
    SpinScreen(navController = navController)
}


