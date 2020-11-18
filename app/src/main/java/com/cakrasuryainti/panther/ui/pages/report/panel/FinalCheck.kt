package com.cakrasuryainti.panther.ui.pages.report.panel

import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.ui.tooling.preview.Preview
import com.cakrasuryainti.panther.db.model.PanelReport
import com.cakrasuryainti.panther.ui.RootViewModel

@Composable
fun FinalCheck(navController: NavHostController, viewModel: RootViewModel) {
    val report by viewModel.currentPanelReport.observeAsState()

    Form(
        onNavigateBack = { navController.popBackStack() },
        onDone = {},
        report = report,
        updateReport = { viewModel.updateReport(it) }
    )
}

@Composable
private fun Form(
    onNavigateBack: () -> Unit,
    onDone: () -> Unit,
    report: PanelReport?,
    updateReport: (PanelReport) -> Unit
) {
    fun handleDone(report: PanelReport?) {
        onDone()
    }

    fun handleUpdate(report: PanelReport?) {
        if (report != null) {
            updateReport(report)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Final Check") },
                navigationIcon = {
                    IconButton(onClick = { onNavigateBack() }) {
                        Icon(Icons.Rounded.ArrowBack)
                    }
                },
                actions = {
                    TextButton(onClick = { handleDone(report) }) {
                        Text(
                            "Done",
                            color = Color.White,
                        )
                    }
                }
            )
        })
    {
        ScrollableColumn(modifier = Modifier.padding(start = 8.dp, end = 8.dp)) {
            OutlinedTextField(
                label = { Text("Note / Suggestion") },
                value = report?.notesAndRecommendation ?: "",
                onValueChange = { handleUpdate(report?.copy(notesAndRecommendation = it)) },
                maxLines = 30,
                modifier = Modifier.fillMaxWidth().padding(8.dp)
            )
        }
    }
}

@Preview
@Composable
private fun FormPreview() {
    var report by remember { mutableStateOf(PanelReport("")) }
    Form(
        onNavigateBack = {},
        onDone = {},
        report = report,
        updateReport = { report = it }
    )
}