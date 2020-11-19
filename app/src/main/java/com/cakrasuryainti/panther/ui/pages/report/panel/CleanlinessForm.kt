package com.cakrasuryainti.panther.ui.pages.report.panel

import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate
import com.cakrasuryainti.panther.db.model.PanelReport
import com.cakrasuryainti.panther.db.model.Status
import com.cakrasuryainti.panther.ui.RootViewModel
import com.cakrasuryainti.panther.ui.components.StatusCheckField

@Composable
fun CleanlinessForm(navController: NavHostController, viewModel: RootViewModel) {
    val reportWithImages by viewModel.currentPanelReport.observeAsState()

    Form(
        onNavigateBack = { navController.popBackStack() },
        onNext = { navController.navigate("create/panel/final") },
        report = reportWithImages?.report,
        updateReport = { viewModel.updateReport(it) }
    )
}

@Composable
private fun Form(
    onNavigateBack: () -> Unit,
    onNext: () -> Unit,
    report: PanelReport?,
    updateReport: (PanelReport) -> Unit
) {
    fun handleUpdate(report: PanelReport?) {
        if (report != null) updateReport(report)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Kebersihan") },
                navigationIcon = {
                    IconButton(onClick = { onNavigateBack() }) {
                        Icon(Icons.Rounded.ArrowBack)
                    }
                },
                actions = {
                    TextButton(onClick = { onNext() }) {
                        Text(
                            "Next",
                            color = Color.White,
                            style = MaterialTheme.typography.button
                        )
                    }
                }
            )
        })
    {
        ScrollableColumn {
            Column(modifier = Modifier.padding(16.dp)) {
                StatusCheckField(
                    label = "1. Luar panel",
                    value = report?.luarPanel ?: Status.NotAvailable,
                    onChange = { handleUpdate(report?.copy(luarPanel = it)) },
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                StatusCheckField(
                    label = "2. Dalam panel",
                    value = report?.dalamPanel ?: Status.NotAvailable,
                    onChange = { handleUpdate(report?.copy(dalamPanel = it)) },
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                StatusCheckField(
                    label = "3. Jalur kabel power",
                    value = report?.jalurKabelPower ?: Status.NotAvailable,
                    onChange = { handleUpdate(report?.copy(jalurKabelPower = it)) },
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                StatusCheckField(
                    label = "4. Kondisi ruangan",
                    value = report?.kondisiRuangan ?: Status.NotAvailable,
                    onChange = { handleUpdate(report?.copy(kondisiRuangan = it)) },
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                StatusCheckField(
                    label = "5. Ruangan / Lingkungan",
                    value = report?.lingkungan ?: Status.NotAvailable,
                    onChange = { handleUpdate(report?.copy(lingkungan = it)) },
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                StatusCheckField(
                    label = "6. Penerangan",
                    value = report?.penerangan ?: Status.NotAvailable,
                    onChange = { handleUpdate(report?.copy(penerangan = it)) },
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                StatusCheckField(
                    label = "7. Fan Ruangan",
                    value = report?.fanRuangan ?: Status.NotAvailable,
                    onChange = { handleUpdate(report?.copy(fanRuangan = it)) },
                    modifier = Modifier.padding(bottom = 16.dp)
                )
            }
        }
    }
}