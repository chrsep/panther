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
import androidx.compose.ui.tooling.preview.Preview
import com.cakrasuryainti.panther.db.model.PanelReport
import com.cakrasuryainti.panther.db.model.Status
import com.cakrasuryainti.panther.ui.components.StatusCheckField

@Composable
fun VisualCheckForm(navController: NavHostController, viewModel: PanelViewModel) {
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
                    statusValue = report?.relay ?: Status.NotAvailable,
                    onStatusChange = { handleUpdate(report?.copy(relay = it)) },
                    keteranganValue = report?.keteranganRelay ?: "",
                    onKeteranganChange = { handleUpdate(report?.copy(keteranganRelay = it)) },
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                StatusCheckField(
                    label = "2. Kabel instalasi",
                    statusValue = report?.kabel ?: Status.NotAvailable,
                    onStatusChange = { handleUpdate(report?.copy(kabel = it)) },
                    keteranganValue = report?.keteranganKabelInstalasi ?: "",
                    onKeteranganChange = { handleUpdate(report?.copy(keteranganKabelInstalasi = it)) },
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                StatusCheckField(
                    label = "3. ACB/MCCB/MCB (input)",
                    statusValue = report?.MCBInput ?: Status.NotAvailable,
                    onStatusChange = { handleUpdate(report?.copy(MCBInput = it)) },
                    keteranganValue = report?.keteranganMCBInput ?: "",
                    onKeteranganChange = { handleUpdate(report?.copy(keteranganMCBInput = it)) },
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                StatusCheckField(
                    label = "4. ACB/MCCB/MCB (output)",
                    statusValue = report?.MCBOutput ?: Status.NotAvailable,
                    onStatusChange = { handleUpdate(report?.copy(MCBOutput = it)) },
                    keteranganValue = report?.keteranganMCBOutput ?: "",
                    onKeteranganChange = { handleUpdate(report?.copy(keteranganMCBOutput = it)) },
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                StatusCheckField(
                    label = "5. Lampu indikator panel",
                    statusValue = report?.lampuIndikatorPanel ?: Status.NotAvailable,
                    onStatusChange = { handleUpdate(report?.copy(lampuIndikatorPanel = it)) },
                    keteranganValue = report?.keteranganLampuIndikatorPanel ?: "",
                    onKeteranganChange = { handleUpdate(report?.copy(keteranganLampuIndikatorPanel = it)) },
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                StatusCheckField(
                    label = "6. Fuse",
                    statusValue = report?.fuse ?: Status.NotAvailable,
                    onStatusChange = { handleUpdate(report?.copy(fuse = it)) },
                    keteranganValue = report?.keteranganFuse ?: "",
                    onKeteranganChange = { handleUpdate(report?.copy(keteranganFuse = it)) },
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                StatusCheckField(
                    label = "7. Terminal power",
                    statusValue = report?.terminalPower ?: Status.NotAvailable,
                    onStatusChange = { handleUpdate(report?.copy(terminalPower = it)) },
                    keteranganValue = report?.keteranganTerminalPower ?: "",
                    onKeteranganChange = { handleUpdate(report?.copy(keteranganTerminalPower = it)) },
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                StatusCheckField(
                    label = "8. Amper meter",
                    statusValue = report?.ampereMeter ?: Status.NotAvailable,
                    onStatusChange = { handleUpdate(report?.copy(ampereMeter = it)) },
                    keteranganValue = report?.keteranganAmpereMeter ?: "",
                    onKeteranganChange = { handleUpdate(report?.copy(keteranganAmpereMeter = it)) },
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                StatusCheckField(
                    label = "9. Volt meter",
                    statusValue = report?.voltMeter ?: Status.NotAvailable,
                    onStatusChange = { handleUpdate(report?.copy(voltMeter = it)) },
                    keteranganValue = report?.keteranganVoltMeter ?: "",
                    onKeteranganChange = { handleUpdate(report?.copy(keteranganVoltMeter = it)) },
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                StatusCheckField(
                    label = "10. Modul control status",
                    statusValue = report?.modulControlStatus ?: Status.NotAvailable,
                    onStatusChange = { handleUpdate(report?.copy(modulControlStatus = it)) },
                    keteranganValue = report?.keteranganModulControlStatus ?: "",
                    onKeteranganChange = { handleUpdate(report?.copy(keteranganModulControlStatus = it)) },
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                StatusCheckField(
                    label = "11. Timer (hour counter)",
                    statusValue = report?.timer ?: Status.NotAvailable,
                    onStatusChange = { handleUpdate(report?.copy(timer = it)) },
                    keteranganValue = report?.keteranganTimer ?: "",
                    onKeteranganChange = { handleUpdate(report?.copy(keteranganTimer = it)) },
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                StatusCheckField(
                    label = "12. Push button ON",
                    statusValue = report?.pushButtonOn ?: Status.NotAvailable,
                    onStatusChange = { handleUpdate(report?.copy(pushButtonOn = it)) },
                    keteranganValue = report?.keteranganPushButtonOn ?: "",
                    onKeteranganChange = { handleUpdate(report?.copy(keteranganPushButtonOn = it)) },
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                StatusCheckField(
                    label = "13. Push button OFF",
                    statusValue = report?.pushButtonOff ?: Status.NotAvailable,
                    onStatusChange = { handleUpdate(report?.copy(pushButtonOff = it)) },
                    keteranganValue = report?.keteranganPushButtonOff ?: "",
                    onKeteranganChange = { handleUpdate(report?.copy(keteranganPushButtonOff = it)) },
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                StatusCheckField(
                    label = "14. Selector MOA",
                    statusValue = report?.selectorMOA ?: Status.NotAvailable,
                    onStatusChange = { handleUpdate(report?.copy(selectorMOA = it)) },
                    keteranganValue = report?.keteranganSelectorMOA ?: "",
                    onKeteranganChange = { handleUpdate(report?.copy(keteranganSelectorMOA = it)) },
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                StatusCheckField(
                    label = "15. Status Indikator",
                    statusValue = report?.statusIndikator ?: Status.NotAvailable,
                    onStatusChange = { handleUpdate(report?.copy(statusIndikator = it)) },
                    keteranganValue = report?.keteranganStatusIndikator ?: "",
                    onKeteranganChange = { handleUpdate(report?.copy(keteranganStatusIndikator = it)) },
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
