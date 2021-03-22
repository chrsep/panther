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
fun GeneratorEquipmentContainer(
    navController: NavHostController,
    viewModel: GeneratorViewModel,
) {
    val reportWithImages by viewModel.currentPanelReport.observeAsState()

    GeneratorEquipmentForm(
        onNavigateBack = { navController.popBackStack() },
        onNext = { navController.navigate("create/generator/documents") },
        report = reportWithImages?.report,
        updateReport = {
            viewModel.updateReport(it)

        }
    )
}

@Composable
private fun GeneratorEquipmentForm(
    onNavigateBack: () -> Unit,
    onNext: () -> Unit,
    report: PanelReport?,
    updateReport: (PanelReport) -> Unit
) {
    var otherEquipments by remember { mutableStateOf(0) }
//    var isDirty by remember { mutableStateOf(false) }

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
//            isDirty = true
//        }
    }

    fun updateState(report: PanelReport?) {
        if (report != null) updateReport(report)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Equipments and Tools") },
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
                .verticalScroll(
                    rememberScrollState()
                )
        ) {
            TextField(
                value = "",
                label = { Text("All tools presents and in good conditions?") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                onValueChange = {}
            )
            TextField(
                value = "",
                label = { Text("Wrenches") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                onValueChange = {}
            )
            Text(
                "Fire extinguisher",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, end = 8.dp, top = 32.dp, bottom = 8.dp)
            )
            TextField(
                value = "",
                label = { Text("Fire extinguisher present") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                onValueChange = {}
            )
            TextField(
                value = "",
                label = { Text("Fire extinguisher working") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                onValueChange = {}
            )
            Text(
                "First aid kit",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, end = 8.dp, top = 32.dp, bottom = 8.dp)
            )
            TextField(
                value = "",
                label = { Text("First aid kit present?") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                onValueChange = {}
            )
            TextField(
                value = "",
                label = { Text("First aid kit complete?") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                onValueChange = {}
            )
            TextField(
                value = "",
                label = { Text("Water decanter?") },
                placeholder = { Text("Does it need to be drained?") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                onValueChange = {}
            )
            Text(
                "Other Equipments",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, end = 8.dp, top = 32.dp, bottom = 8.dp)
            )
            for (i in 1..otherEquipments) {
                TextField(
                    value = "",
                    label = { Text("Equipment name") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 8.dp, end = 8.dp, bottom = 8.dp),
                    onValueChange = {}
                )
                TextField(
                    value = "",
                    label = { Text("Equipment condition") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 8.dp, end = 8.dp, bottom = 32.dp),
                    onValueChange = {}
                )
            }
            Button(
                onClick = { otherEquipments += 1 },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, end = 8.dp, top = 8.dp, bottom = 32.dp)
            ) {
                Text("Add more equipments")
            }
        }
    }
}

@Preview
@Composable
private fun FormPreview() {
    var report by remember { mutableStateOf(PanelReport("")) }
    PantherTheme {
        GeneratorEquipmentForm(
            onNavigateBack = {},
            onNext = {},
            report = report,
            updateReport = { report = it })
    }
}