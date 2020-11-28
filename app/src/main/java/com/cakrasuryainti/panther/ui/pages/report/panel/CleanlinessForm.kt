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
import com.cakrasuryainti.panther.ui.components.StatusCheckField

@Composable
fun CleanlinessForm(navController: NavHostController, viewModel: PanelViewModel) {
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
                    statusValue = report?.luarPanel ?: Status.NotAvailable,
                    onStatusChange = { handleUpdate(report?.copy(luarPanel = it)) },
                    keteranganValue = report?.keteranganLuarPanel ?: "",
                    onKeteranganChange = { handleUpdate(report?.copy(keteranganLuarPanel = it)) },
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                StatusCheckField(
                    label = "2. Dalam panel",
                    statusValue = report?.dalamPanel ?: Status.NotAvailable,
                    onStatusChange = { handleUpdate(report?.copy(dalamPanel = it)) },
                    keteranganValue = report?.keteranganDalamPanel ?: "",
                    onKeteranganChange = { handleUpdate(report?.copy(keteranganDalamPanel = it)) },
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                StatusCheckField(
                    label = "3. Jalur kabel power",
                    statusValue = report?.jalurKabelPower ?: Status.NotAvailable,
                    onStatusChange = { handleUpdate(report?.copy(jalurKabelPower = it)) },
                    keteranganValue = report?.keteranganJalurKabelPower ?: "",
                    onKeteranganChange = { handleUpdate(report?.copy(keteranganJalurKabelPower = it)) },
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                StatusCheckField(
                    label = "4. Kondisi ruangan",
                    statusValue = report?.kondisiRuangan ?: Status.NotAvailable,
                    onStatusChange = { handleUpdate(report?.copy(kondisiRuangan = it)) },
                    keteranganValue = report?.keteranganKondisiRuangan ?: "",
                    onKeteranganChange = { handleUpdate(report?.copy(keteranganKondisiRuangan = it)) },
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                StatusCheckField(
                    label = "5. Ruangan / Lingkungan",
                    statusValue = report?.lingkungan ?: Status.NotAvailable,
                    onStatusChange = { handleUpdate(report?.copy(lingkungan = it)) },
                    keteranganValue = report?.keteranganLingkungan ?: "",
                    onKeteranganChange = { handleUpdate(report?.copy(keteranganLingkungan = it)) },
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                StatusCheckField(
                    label = "6. Penerangan",
                    statusValue = report?.penerangan ?: Status.NotAvailable,
                    onStatusChange = { handleUpdate(report?.copy(penerangan = it)) },
                    keteranganValue = report?.keteranganPenerangan ?: "",
                    onKeteranganChange = { handleUpdate(report?.copy(keteranganPenerangan = it)) },
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                StatusCheckField(
                    label = "7. Fan Ruangan",
                    statusValue = report?.fanRuangan ?: Status.NotAvailable,
                    onStatusChange = { handleUpdate(report?.copy(fanRuangan = it)) },
                    keteranganValue = report?.keteranganFanRuangan ?: "",
                    onKeteranganChange = { handleUpdate(report?.copy(keteranganFanRuangan = it)) },
                    modifier = Modifier.padding(bottom = 16.dp)
                )
            }
        }
    }
}