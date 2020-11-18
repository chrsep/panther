package com.cakrasuryainti.panther.ui

import androidx.compose.foundation.layout.ExperimentalLayout
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.cakrasuryainti.panther.ui.pages.Home
import com.cakrasuryainti.panther.ui.pages.report.CreateReport
import com.cakrasuryainti.panther.ui.pages.report.panel.*
import com.cakrasuryainti.panther.ui.theme.PantherTheme


@ExperimentalLayout
@Composable
fun Root() {
    val navController = rememberNavController()
    val viewModel: RootViewModel = viewModel()
    PantherTheme {
        // A surface container using the 'background' color from the theme
        NavHost(navController, startDestination = "home") {
            composable("home") { Home(navController) }
            composable("create") { CreateReport(navController) }
            composable("create/panel") { PanelReportMetaForm(navController, viewModel) }
            composable("create/panel/measurements") { PanelMeasurementForm(navController, viewModel) }
            composable("create/panel/checks") { VisualCheckForm(navController, viewModel) }
            composable("create/panel/cleanliness") { CleanlinessForm(navController, viewModel) }
            composable("create/panel/final") { FinalCheck(navController, viewModel) }
        }
    }
}
