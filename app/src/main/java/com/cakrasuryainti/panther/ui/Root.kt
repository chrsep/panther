package com.cakrasuryainti.panther.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.cakrasuryainti.panther.ui.pages.CreatePanelReport
import com.cakrasuryainti.panther.ui.pages.CreateReport
import com.cakrasuryainti.panther.ui.pages.Home
import com.cakrasuryainti.panther.ui.theme.PantherTheme

@Composable
fun Root() {
    val navController = rememberNavController()
    PantherTheme {
        // A surface container using the 'background' color from the theme
        NavHost(navController, startDestination = "home") {
            composable("home") { Home(navController) }
            composable("create") { CreateReport(navController) }
            composable("create/panel") { CreatePanelReport(navController) }
        }
    }
}
