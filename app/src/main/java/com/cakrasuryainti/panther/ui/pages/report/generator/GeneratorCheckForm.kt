package com.cakrasuryainti.panther.ui.pages.report.generator

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate
import com.cakrasuryainti.panther.db.model.PanelReport
import com.cakrasuryainti.panther.ui.theme.PantherTheme


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
                         Icon(Icons.Rounded.ArrowBack, "")
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
        Column(
            modifier = Modifier
                .padding(start = 8.dp, end = 8.dp)
                .verticalScroll(rememberScrollState())
        ) {
            TextField(
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                value = report?.customer ?: "",
                onValueChange = { updateState(report?.copy(customer = it)) },
                label = { Text("Running Hours") },
                placeholder = { Text("Check the hourly gauge and enter amount") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .semantics { contentDescription = "pekerjaan" },
                isError = isDirty && report?.customer == "",
            )
            TextField(
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                value = report?.panelName ?: "",
                onValueChange = { updateState(report?.copy(panelName = it)) },
                label = { Text("Generator is clean") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                isError = isDirty && report?.panelName == ""
            )
            TextField(
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                value = report?.model ?: "",
                onValueChange = { updateState(report?.copy(model = it)) },
                label = { Text("Shed is clean") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                isError = isDirty && report?.model == ""
            )
            TextField(
                value = report?.serialNumber ?: "",
                onValueChange = { updateState(report?.copy(serialNumber = it)) },
                label = { Text("Fuel tank at least 50% full") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                isError = isDirty && report?.serialNumber == ""
            )
            TextField(
                value = report?.location ?: "",
                onValueChange = { updateState(report?.copy(location = it)) },
                label = { Text("Engine oil level is okay?") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                isError = isDirty && report?.location == ""
            )
            TextField(
                value = "",
                label = { Text("Engine oil condition?") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                onValueChange = {}
            )
            TextField(
                value = "",
                label = { Text("Filter oil check condition") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                onValueChange = {}
            )
            TextField(
                value = "",
                label = { Text("Filter air check condition") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                onValueChange = {}
            )
            TextField(
                value = "",
                label = { Text("Radiator, no leaks?") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                onValueChange = {}
            )
            TextField(
                value = "",
                label = { Text("Radiator coolant level okay?") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                onValueChange = {}
            )
            TextField(
                value = "",
                label = { Text("Battery connections good?") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                onValueChange = {}
            )
            TextField(
                value = "",
                label = { Text("Battery water level okay?") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                onValueChange = {}
            )
            TextField(
                value = "",
                label = { Text("Battery charger is charging?") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                onValueChange = {}
            )
            TextField(
                value = "",
                label = { Text("Exhaust system is functioning normally?") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                onValueChange = {}
            )
            TextField(
                value = "",
                label = { Text("Manual start is working?") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                onValueChange = {}
            )
            TextField(
                value = "",
                label = { Text("Auto-start is working?") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                onValueChange = {}
            )
        }
    }
}

@Preview
@Composable
private fun FormPreview() {
    var report by remember { mutableStateOf(PanelReport("")) }
    PantherTheme {
        MetaForm(onNavigateBack = {}, onNext = {}, report = report, updateReport = { report = it })
    }
}