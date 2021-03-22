package com.cakrasuryainti.panther.ui.pages.report.generator

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate
import com.cakrasuryainti.panther.db.model.PanelReport
import com.cakrasuryainti.panther.ui.theme.PantherTheme


@Composable
fun GeneratorEngineContainer(
    navController: NavHostController,
    viewModel: GeneratorViewModel,
) {
    val reportWithImages by viewModel.currentPanelReport.observeAsState()

    GeneratorEngine(
        onNavigateBack = { navController.popBackStack() },
        onNext = { navController.navigate("create/generator/final") },
        report = reportWithImages?.report,
        updateReport = {
            viewModel.updateReport(it)

        }
    )
}


@Composable
private fun GeneratorEngine(
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
                title = { Text("Check engine start") },
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
                value = "",
                label = { Text("Pre-heating works?") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                onValueChange = {}
            )
            TextField(
                value = "",
                label = { Text("Motor starts esaily?") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                onValueChange = {}
            )
            TextField(
                value = "",
                label = { Text("Oil pressure OK") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                onValueChange = {}
            )
            TextField(
                value = "",
                label = { Text("Battery charging?") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                onValueChange = {}
            )

            Text(
                "Breaker OFF condition",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, end = 8.dp, top = 32.dp, bottom = 8.dp)
            )
            TextField(
                value = "",
                label = { Text("Measurement Metering (Volt)") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                onValueChange = {}
            )
            TextField(
                value = "",
                label = { Text("Measurement Metering (Ampere)") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                onValueChange = {}
            )
            TextField(
                value = "",
                label = { Text("Measurement Metering (HZ)") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                onValueChange = {}
            )

            Text(
                "Breaker ON condition",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, end = 8.dp, top = 32.dp, bottom = 8.dp)
            )
            TextField(
                value = "",
                label = { Text("Measurement Metering (Volt)") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                onValueChange = {}
            )
            TextField(
                value = "",
                label = { Text("Measurement Metering (Ampere)") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                onValueChange = {}
            )
            TextField(
                value = "",
                label = { Text("Measurement Metering (HZ)") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                onValueChange = {}
            )

            Text(
                "Auto start test",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, end = 8.dp, top = 32.dp, bottom = 8.dp)
            )
            TextField(
                value = "",
                label = { Text("Measurement Metering (Volt)") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                onValueChange = {}
            )
            TextField(
                value = "",
                label = { Text("Measurement Metering (Ampere)") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                onValueChange = {}
            )
            TextField(
                value = "",
                label = { Text("Measurement Metering (HZ)") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, end = 8.dp, top = 8.dp, bottom = 32.dp),
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