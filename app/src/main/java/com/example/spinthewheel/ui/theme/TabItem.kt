package com.example.spinthewheel.ui.theme

import com.example.spinthewheel.screens.GuessTheWordScreen
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.compose.rememberNavController

typealias ComposableFun = @Composable () -> Unit

sealed class TabItem(val title: String, val icon: ImageVector, val screens: ComposableFun) {
    object GuessTheWord : TabItem(
        "Gæt ordet",
        icon = Icons.Default.Info,
        { GuessTheWordScreen(navController = rememberNavController()) })

}
