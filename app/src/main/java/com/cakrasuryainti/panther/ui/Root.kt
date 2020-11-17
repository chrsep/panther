package com.cakrasuryainti.panther.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.cakrasuryainti.panther.ui.pages.Home
import com.cakrasuryainti.panther.ui.pages.report.CreateReport
import com.cakrasuryainti.panther.ui.pages.report.panel.PanelMeasurementForm
import com.cakrasuryainti.panther.ui.pages.report.panel.PanelReportMetaForm
import com.cakrasuryainti.panther.ui.theme.PantherTheme



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
            composable("create/panel/measurements") { PanelMeasurementForm(navController) }
        }
    }
}
