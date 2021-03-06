package com.cakrasuryainti.panther.ui

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.cakrasuryainti.panther.ui.pages.HomeContainer
import com.cakrasuryainti.panther.ui.pages.SavedReport
import com.cakrasuryainti.panther.ui.pages.SavedViewModel
import com.cakrasuryainti.panther.ui.pages.report.CreateReport
import com.cakrasuryainti.panther.ui.pages.report.generator.*
import com.cakrasuryainti.panther.ui.pages.report.panel.*
import com.cakrasuryainti.panther.ui.pages.saved.ListGeneratorReport
import com.cakrasuryainti.panther.ui.pages.saved.ListLVPanel
import com.cakrasuryainti.panther.ui.theme.PantherTheme


@ExperimentalMaterialApi

@Composable
fun Root() {
    val navController = rememberNavController()
    val panelViewModel: PanelViewModel = viewModel()
    val generatorViewModel: GeneratorViewModel = viewModel()
    val savedViewModel: SavedViewModel = viewModel()

    PantherTheme {
        // A surface container using the 'background' color from the theme
        NavHost(navController, startDestination = "home") {
            composable("home") { HomeContainer(navController) }
            composable("saved") { SavedReport(navController) }
            composable("saved/panel") { ListLVPanel(navController, savedViewModel) }
            composable("saved/generator") {
                ListGeneratorReport(
                    navController,
                    savedViewModel
                )
            }


            composable("create") { CreateReport(navController) }

            // LV Panel Checks
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
                EditImageContainer(
                    navController,
                    panelViewModel,
                    it.arguments?.getString("imageId")
                )
            }
            composable("create/panel/done") { ReportDone(navController, panelViewModel) }

            // Generator
            composable("create/generator") { GeneratorMetaForm(navController, generatorViewModel) }
            composable("create/generator/generator") {
                GeneratorCheckFormContainer(
                    navController,
                    generatorViewModel
                )
            }
            composable("create/generator/equipments") {
                GeneratorEquipmentContainer(
                    navController,
                    generatorViewModel
                )
            }
            composable("create/generator/documents") {
                GeneratorDocumentsContainer(
                    navController,
                    generatorViewModel
                )
            }
            composable("create/generator/engine") {
                GeneratorEngineContainer(
                    navController,
                    generatorViewModel
                )
            }
            composable("create/generator/final") {
                GeneratorFinalCheckContainer(
                    navController,
                    generatorViewModel
                )
            }
            composable(
                "create/generator/image/{imageId}",
                listOf(navArgument("imageId") { type = NavType.StringType })
            ) {
                GeneratorEditImageContainer(
                    navController,
                    generatorViewModel,
                    it.arguments?.getString("imageId")
                )
            }
            composable("create/generator/done") {
                GeneratorReportDoneContainer(
                    navController,
                    generatorViewModel
                )
            }
        }
    }
}
