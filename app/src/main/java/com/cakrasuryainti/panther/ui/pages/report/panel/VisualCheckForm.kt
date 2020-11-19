package com.cakrasuryainti.panther.ui.pages.report.panel

import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.layout.Column
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
import androidx.navigation.compose.navigate
import androidx.ui.tooling.preview.Preview
import com.cakrasuryainti.panther.db.model.PanelReport
import com.cakrasuryainti.panther.db.model.Status
import com.cakrasuryainti.panther.ui.RootViewModel
import com.cakrasuryainti.panther.ui.components.StatusCheckField

@Composable
fun VisualCheckForm(navController: NavHostController, viewModel: RootViewModel) {
    val reportWithImages by viewModel.currentPanelReport.observeAsState()

    Form(
        onNavigateBack = { navController.popBackStack() },
        onNext = { navController.navigate("create/panel/cleanliness") },
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
                title = { Text("Pengecekan Visual") },
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
                    label = "1. Relay / time control",
                    value = report?.relay ?: Status.NotAvailable,
                    onChange = { handleUpdate(report?.copy(relay = it)) },
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                StatusCheckField(
                    label = "2. Kabel instalasi",
                    value = report?.kabel ?: Status.NotAvailable,
                    onChange = { handleUpdate(report?.copy(kabel = it)) },
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                StatusCheckField(
                    label = "3. ACB/MCCB/MCB (input)",
                    value = report?.MCBInput ?: Status.NotAvailable,
                    onChange = { handleUpdate(report?.copy(MCBInput = it)) },
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                StatusCheckField(
                    label = "4. ACB/MCCB/MCB (output)",
                    value = report?.MCBOutput ?: Status.NotAvailable,
                    onChange = { handleUpdate(report?.copy(MCBOutput = it)) },
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                StatusCheckField(
                    label = "5. Lampu indikator panel",
                    value = report?.lampuIndikatorPanel ?: Status.NotAvailable,
                    onChange = { handleUpdate(report?.copy(lampuIndikatorPanel = it)) },
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                StatusCheckField(
                    label = "6. Fuse",
                    value = report?.fuse ?: Status.NotAvailable,
                    onChange = { handleUpdate(report?.copy(fuse = it)) },
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                StatusCheckField(
                    label = "7. Terminal power",
                    value = report?.terminalPower ?: Status.NotAvailable,
                    onChange = { handleUpdate(report?.copy(terminalPower = it)) },
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                StatusCheckField(
                    label = "8. Amper meter",
                    value = report?.ampereMeter ?: Status.NotAvailable,
                    onChange = { handleUpdate(report?.copy(ampereMeter = it)) },
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                StatusCheckField(
                    label = "9. Volt meter",
                    value = report?.voltMeter ?: Status.NotAvailable,
                    onChange = { handleUpdate(report?.copy(voltMeter = it)) },
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                StatusCheckField(
                    label = "10. Modul control status",
                    value = report?.modulControlStatus ?: Status.NotAvailable,
                    onChange = { handleUpdate(report?.copy(modulControlStatus = it)) },
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                StatusCheckField(
                    label = "11. Timer (hour counter)",
                    value = report?.timer ?: Status.NotAvailable,
                    onChange = { handleUpdate(report?.copy(timer = it)) },
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                StatusCheckField(
                    label = "12. Push button ON",
                    value = report?.pushButtonOn ?: Status.NotAvailable,
                    onChange = { handleUpdate(report?.copy(pushButtonOn = it)) },
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                StatusCheckField(
                    label = "13. Push button OFF",
                    value = report?.pushButtonOff ?: Status.NotAvailable,
                    onChange = { handleUpdate(report?.copy(pushButtonOff = it)) },
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                StatusCheckField(
                    label = "14. Selector MOA",
                    value = report?.selectorMOA ?: Status.NotAvailable,
                    onChange = { handleUpdate(report?.copy(selectorMOA = it)) },
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                StatusCheckField(
                    label = "15. Status Indikator",
                    value = report?.statusIndikator ?: Status.NotAvailable,
                    onChange = { handleUpdate(report?.copy(statusIndikator = it)) },
                    modifier = Modifier.padding(bottom = 16.dp)
                )
            }
        }
    }
}


@Preview
@Composable
fun CheckFormPreview() {
    var report by remember { mutableStateOf(PanelReport("")) }
    Form(onNavigateBack = {}, onNext = {}, updateReport = { report = it }, report = report)
}
