package com.cakrasuryainti.panther.ui.pages.report.panel

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate
import com.cakrasuryainti.panther.db.model.JobDesc
import com.cakrasuryainti.panther.db.model.PanelReport
import com.cakrasuryainti.panther.ui.components.ChoiceChip
import com.cakrasuryainti.panther.ui.theme.PantherTheme
import com.google.accompanist.flowlayout.FlowRow


@Composable
fun PanelReportMetaForm(
    navController: NavHostController,
    viewModel: PanelViewModel,
) {
    val reportWithImages by viewModel.currentPanelReport.observeAsState()

    MetaForm(
        onNavigateBack = { navController.popBackStack() },
        onNext = { navController.navigate("create/panel/measurements") },
        report = reportWithImages?.report,
        updateReport = {
            viewModel.updateReport(it)

        }
    )
}

@Composable
fun MetaForm(
    onNavigateBack: () -> Unit,
    onNext: () -> Unit,
    report: PanelReport?,
    updateReport: (PanelReport) -> Unit
) {
    var isDirty by remember { mutableStateOf(false) }

    fun handleNext(report: PanelReport?) {
        if (
            report?.customer != "" &&
            report?.panelName != "" &&
            report?.model != "" &&
            report?.serialNumber != "" &&
            report?.location != ""
        ) {
            onNext()
        } else {
            isDirty = true
        }
    }

    fun updateState(report: PanelReport?) {
        if (report != null) updateReport(report)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Data Pekerjaan") },
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
                .padding(start = 8.dp, end = 8.dp, top = 8.dp)
                .verticalScroll(rememberScrollState())
        ) {
            TextField(
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                value = report?.customer ?: "",
                onValueChange = { updateState(report?.copy(customer = it)) },
                label = { Text("Customer") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                isError = isDirty && report?.customer == "",
            )
            TextField(
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                value = report?.panelName ?: "",
                onValueChange = { updateState(report?.copy(panelName = it)) },
                label = { Text("Panel") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                isError = isDirty && report?.panelName == ""
            )
            TextField(
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                value = report?.model ?: "",
                onValueChange = { updateState(report?.copy(model = it)) },
                label = { Text("Type / Model") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                isError = isDirty && report?.model == ""
            )
            TextField(
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                value = report?.serialNumber ?: "",
                onValueChange = { updateState(report?.copy(serialNumber = it)) },
                label = { Text("Serial Number") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                isError = isDirty && report?.serialNumber == ""
            )
            TextField(
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                // TODO: Fix later
//                onImeActionPerformed = { imeAction, _ ->
//                    if (imeAction == ImeAction.Done) handleNext(report)
//                },
                value = report?.location ?: "",
                onValueChange = { updateState(report?.copy(location = it)) },
                label = { Text("Lokasi") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                isError = isDirty && report?.location == ""
            )
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, end = 8.dp, top = 8.dp, bottom = 32.dp),
                border = BorderStroke(1.dp, MaterialTheme.colors.onBackground.copy(alpha = 0.4f)),
                shape = RoundedCornerShape(8.dp)
            ) {
                Column(modifier = Modifier.padding(8.dp)) {
                    Text(
                        "Job Desc",
                        modifier = Modifier.padding(start = 8.dp, top = 8.dp, bottom = 8.dp),
                        style = MaterialTheme.typography.body2
                    )
                    // TODO: Row is not good enough here
                    FlowRow {
                        JobDesc.values().forEach { jobDesc ->
                            ChoiceChip(
                                modifier = Modifier.padding(start = 8.dp, bottom = 8.dp),
                                onClick = { updateState(report?.copy(jobDesc = jobDesc)) },
                                label = jobDesc.name,
                                isActive = jobDesc == report?.jobDesc ?: JobDesc.Maintenance
                            )
                        }
                    }
                }
            }
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