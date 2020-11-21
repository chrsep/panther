package com.cakrasuryainti.panther.ui

import androidx.compose.foundation.layout.ExperimentalLayout
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.cakrasuryainti.panther.ui.pages.HomeContainer
import com.cakrasuryainti.panther.ui.pages.report.CreateReport
import com.cakrasuryainti.panther.ui.pages.report.HomeViewModel
import com.cakrasuryainti.panther.ui.pages.report.panel.*
import com.cakrasuryainti.panther.ui.theme.PantherTheme


@ExperimentalMaterialApi
@ExperimentalLayout
@Composable
fun Root() {
    val navController = rememberNavController()
    val panelViewModel: PanelViewModel = viewModel()
    val homeViewModel: HomeViewModel = viewModel()
    PantherTheme {
        // A surface container using the 'background' color from the theme
        NavHost(navController, startDestination = "home") {
            composable("home") { HomeContainer(navController, homeViewModel) }
            composable("create") { CreateReport(navController) }

            composable("create/panel") { PanelReportMetaForm(navController, panelViewModel) }
            composable("create/panel/measurements") {
                PanelMeasurementForm(
                    navController,
                    panelViewModel
                )
            }
            composable("create/panel/checks") { VisualCheckForm(navController, panelViewModel) }
            composable("create/panel/cleanliness") {
                CleanlinessForm(
                    navController,
                    panelViewModel
                )
            }
            composable("create/panel/final") { FinalCheck(navController, panelViewModel) }
            composable(
                "create/panel/image/{imageId}",
                listOf(navArgument("imageId") { type = NavType.StringType })
            ) {
                EditImageContainer(navController, panelViewModel, it.arguments?.getString("imageId"))
            }
            composable("create/panel/done") { ReportDone(navController, panelViewModel) }
        }
    }
}
