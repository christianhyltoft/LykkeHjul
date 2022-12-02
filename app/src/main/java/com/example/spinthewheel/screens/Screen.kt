package com.example.spinthewheel.screens

import androidx.navigation.NavHostController


sealed class Screen(val route: String) {

    object GuessScreen : Screen("guessscreen")
    object WinScreen : Screen("win_screen")
    object LoseScreen : Screen("lose_screen")
    object SpinScreen : Screen("spin_screen")


    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}
