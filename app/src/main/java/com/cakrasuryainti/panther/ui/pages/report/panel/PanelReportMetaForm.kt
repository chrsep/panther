package com.cakrasuryainti.panther.ui.pages.report.panel

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.semantics.text
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate
import androidx.ui.tooling.preview.Preview
import com.cakrasuryainti.panther.db.model.JobDesc
import com.cakrasuryainti.panther.db.model.PanelReport
import com.cakrasuryainti.panther.ui.RootViewModel
import com.cakrasuryainti.panther.ui.theme.PantherTheme
import com.google.android.material.chip.Chip


@Composable
fun PanelReportMetaForm(
    navController: NavHostController,
    viewModel: RootViewModel,
) {
    val report by viewModel.currentPanelReport.observeAsState()

    MetaForm(
        onNavigateBack = { navController.popBackStack() },
        onNext = { navController.navigate("create/panel/measurements") },
        report = report,
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
        Log.d("test", report?.model.toString())
        if (
            report?.pekerjaan != "" &&
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
                title = { Text("Create Panel Report") },
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
        Column(modifier = Modifier.padding(8.dp)) {
            Surface(
                modifier = Modifier.fillMaxWidth()
                    .padding(start = 8.dp, end = 8.dp, top = 16.dp, bottom = 8.dp),
                border = BorderStroke(1.dp, MaterialTheme.colors.onBackground.copy(alpha = 0.4f)),
                shape = RoundedCornerShape(8.dp)
            ) {
                Column(
                    modifier = Modifier.padding(8.dp)
                ) {
                    Text(
                        "Job Desc",
                        modifier = Modifier.padding(start = 8.dp, top = 8.dp),
                    )
                    Row {
                        JobDesc.values().forEach { jobDesc ->
                            AndroidView(
                                modifier = Modifier.padding(start = 8.dp),
                                viewBlock = { context ->
                                    Chip(context).apply {
                                        isCheckable = true
                                        setOnClickListener {
                                            updateState(report?.copy(jobDesc = jobDesc))
                                            isChecked = true
                                        }
                                    }
                                }
                            ) { chip ->
                                Log.d(
                                    "test",
                                    (jobDesc == report?.jobDesc ?: JobDesc.Maintenance).toString()
                                )
                                chip.text = jobDesc.name
                                chip.isChecked = jobDesc == report?.jobDesc ?: JobDesc.Maintenance
                            }
                        }
                    }
                }
            }
            OutlinedTextField(
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                value = report?.pekerjaan ?: "",
                onValueChange = {
                    updateState(report?.copy(pekerjaan = it))
                },
                label = { Text("Pekerjaan") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .semantics { accessibilityLabel = "pekerjaan" },
                isErrorValue = isDirty && report?.pekerjaan == "",
            )
            OutlinedTextField(
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                value = report?.panelName ?: "",
                onValueChange = {
                    updateState(report?.copy(panelName = it))
                },
                label = { Text("Panel") },
                modifier = Modifier.fillMaxWidth().padding(8.dp),
                isErrorValue = isDirty && report?.panelName == ""
            )
            OutlinedTextField(
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                value = report?.model ?: "",
                onValueChange = {
                    updateState(report?.copy(model = it))
                },
                label = { Text("Type / Model") },
                modifier = Modifier.fillMaxWidth().padding(8.dp),
                isErrorValue = isDirty && report?.model == ""
            )
            OutlinedTextField(
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                value = report?.serialNumber ?: "",
                onValueChange = {
                    updateState(report?.copy(serialNumber = it))
                },
                label = { Text("Serial Number") },
                modifier = Modifier.fillMaxWidth().padding(8.dp),
                isErrorValue = isDirty && report?.serialNumber == ""
            )
            OutlinedTextField(
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                onImeActionPerformed = { imeAction, _ ->
                    if (imeAction == ImeAction.Done) handleNext(report)
                },
                value = report?.location ?: "",
                onValueChange = {
                    updateState(report?.copy(location = it))
                },
                label = { Text("Lokasi") },
                modifier = Modifier.fillMaxWidth().padding(8.dp),
                isErrorValue = isDirty && report?.location == ""
            )
        }
    }
}

@Preview
@Composable
fun FormPreview() {
    var report by remember { mutableStateOf(PanelReport("")) }
    PantherTheme {
        MetaForm(onNavigateBack = {}, onNext = {}, report = report, updateReport = { report = it })
    }
}