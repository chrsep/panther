package com.cakrasuryainti.panther.ui.pages.report.generator

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate
import com.cakrasuryainti.panther.db.model.PanelReport
import com.cakrasuryainti.panther.ui.theme.PantherTheme


@Composable
fun GeneratorMetaForm(
    navController: NavHostController,
    viewModel: GeneratorViewModel,
) {
    val reportWithImages by viewModel.currentPanelReport.observeAsState()

    MetaForm(
        onNavigateBack = { navController.popBackStack() },
        onNext = { navController.navigate("create/generator/generator") },
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
                .padding(start = 8.dp, end = 8.dp)
                .verticalScroll(
                    rememberScrollState()
                )
        ) {
            TextField(
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                value = report?.customer ?: "",
                onValueChange = { customer -> updateState(report?.copy(customer = customer)) },
                label = { Text("Customer") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
//                isError = isDirty && report?.customer == "",
            )
            TextField(
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                value = report?.panelName ?: "",
                onValueChange = { panelName -> updateState(report?.copy(panelName = panelName)) },
                label = { Text("Generator ID") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
//                isError = isDirty && report?.panelName == ""
            )
            TextField(
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                value = report?.model ?: "",
                onValueChange = { model -> updateState(report?.copy(model = model)) },
                label = { Text("Period") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
//                isError = isDirty && report?.model == ""
            )
            Text(
                "Form generator masih tahap Mockup dan belum berfungsi. Mohon feedback/review tentang flow/ui.",
                style = MaterialTheme.typography.h6,
                modifier = Modifier
                    .padding(8.dp)
                    .alpha(0.6f)
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