package com.cakrasuryainti.panther.ui.pages.report.generator

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.accessibilityLabel
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate
import androidx.ui.tooling.preview.Preview
import com.cakrasuryainti.panther.db.model.JobDesc
import com.cakrasuryainti.panther.db.model.PanelReport
import com.cakrasuryainti.panther.ui.components.ChoiceChip
import com.cakrasuryainti.panther.ui.theme.PantherTheme


@ExperimentalLayout
@Composable
fun GeneratorCheckFormContainer(
    navController: NavHostController,
    viewModel: GeneratorViewModel,
) {
    val reportWithImages by viewModel.currentPanelReport.observeAsState()

    GeneratorCheckForm(
        onNavigateBack = { navController.popBackStack() },
        onNext = { navController.navigate("create/generator/equipments") },
        report = reportWithImages?.report,
        updateReport = {
            viewModel.updateReport(it)

        }
    )
}

@ExperimentalLayout
@Composable
private fun GeneratorCheckForm(
    onNavigateBack: () -> Unit,
    onNext: () -> Unit,
    report: PanelReport?,
    updateReport: (PanelReport) -> Unit
) {
    var isDirty by remember { mutableStateOf(false) }

    fun handleNext(report: PanelReport?) {
//        if (
//            report?.customer != "" &&
//            report?.panelName != "" &&
//            report?.model != "" &&
//            report?.serialNumber != "" &&
//            report?.location != ""
//        ) {
        onNext()
//        } else {
//        isDirty = true
//        }
    }

    fun updateState(report: PanelReport?) {
        if (report != null) updateReport(report)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Machine check") },
                navigationIcon = {
                    IconButton(onClick = { onNavigateBack() }) {
                        Icon(Icons.Rounded.ArrowBack)
                    }
                },
                actions = {
                    TextButton(onClick = { handleNext(report) }) {
                        Text(
                            "Next",
                            color = Color.White,
                        )
                    }
                }
            )
        })
    {
        ScrollableColumn(modifier = Modifier.padding(start = 8.dp, end = 8.dp)) {
            OutlinedTextField(
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                value = report?.customer ?: "",
                onValueChange = { updateState(report?.copy(customer = it)) },
                label = { Text("Running Hours") },
                placeholder = { Text("Check the hourly gauge and enter amount") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .semantics { accessibilityLabel = "pekerjaan" },
                isErrorValue = isDirty && report?.customer == "",
            )
            OutlinedTextField(
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                value = report?.panelName ?: "",
                onValueChange = { updateState(report?.copy(panelName = it)) },
                label = { Text("Generator is clean") },
                modifier = Modifier.fillMaxWidth().padding(8.dp),
                isErrorValue = isDirty && report?.panelName == ""
            )
            OutlinedTextField(
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                value = report?.model ?: "",
                onValueChange = { updateState(report?.copy(model = it)) },
                label = { Text("Shed is clean") },
                modifier = Modifier.fillMaxWidth().padding(8.dp),
                isErrorValue = isDirty && report?.model == ""
            )
            OutlinedTextField(
                value = report?.serialNumber ?: "",
                onValueChange = { updateState(report?.copy(serialNumber = it)) },
                label = { Text("Fuel tank at least 50% full") },
                modifier = Modifier.fillMaxWidth().padding(8.dp),
                isErrorValue = isDirty && report?.serialNumber == ""
            )
            OutlinedTextField(
                value = report?.location ?: "",
                onValueChange = { updateState(report?.copy(location = it)) },
                label = { Text("Engine oil level is okay?") },
                modifier = Modifier.fillMaxWidth().padding(8.dp),
                isErrorValue = isDirty && report?.location == ""
            )
            OutlinedTextField(
                value = "",
                label = { Text("Engine oil condition?") },
                modifier = Modifier.fillMaxWidth().padding(8.dp),
                onValueChange = {}
            )
            OutlinedTextField(
                value = "",
                label = { Text("Filter oil check condition") },
                modifier = Modifier.fillMaxWidth().padding(8.dp),
                onValueChange = {}
            )
            OutlinedTextField(
                value = "",
                label = { Text("Filter air check condition") },
                modifier = Modifier.fillMaxWidth().padding(8.dp),
                onValueChange = {}
            )
            OutlinedTextField(
                value = "",
                label = { Text("Radiator, no leaks?") },
                modifier = Modifier.fillMaxWidth().padding(8.dp),
                onValueChange = {}
            )
            OutlinedTextField(
                value = "",
                label = { Text("Radiator coolant level okay?") },
                modifier = Modifier.fillMaxWidth().padding(8.dp),
                onValueChange = {}
            )
            OutlinedTextField(
                value = "",
                label = { Text("Battery connections good?") },
                modifier = Modifier.fillMaxWidth().padding(8.dp),
                onValueChange = {}
            )
            OutlinedTextField(
                value = "",
                label = { Text("Battery water level okay?") },
                modifier = Modifier.fillMaxWidth().padding(8.dp),
                onValueChange = {}
            )
            OutlinedTextField(
                value = "",
                label = { Text("Battery charger is charging?") },
                modifier = Modifier.fillMaxWidth().padding(8.dp),
                onValueChange = {}
            )
            OutlinedTextField(
                value = "",
                label = { Text("Exhaust system is functioning normally?") },
                modifier = Modifier.fillMaxWidth().padding(8.dp),
                onValueChange = {}
            )
            OutlinedTextField(
                value = "",
                label = { Text("Manual start is working?") },
                modifier = Modifier.fillMaxWidth().padding(8.dp),
                onValueChange = {}
            )
            OutlinedTextField(
                value = "",
                label = { Text("Auto-start is working?") },
                modifier = Modifier.fillMaxWidth().padding(8.dp),
                onValueChange = {}
            )
        }
    }
}

@ExperimentalLayout
@Preview
@Composable
private fun FormPreview() {
    var report by remember { mutableStateOf(PanelReport("")) }
    PantherTheme {
        MetaForm(onNavigateBack = {}, onNext = {}, report = report, updateReport = { report = it })
    }
}