package com.cakrasuryainti.panther.ui.pages.report.panel

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import com.cakrasuryainti.panther.ui.components.FloatField

@Composable
fun PanelMeasurementForm(navController: NavHostController, viewModel: PanelViewModel) {
    val reportWithImages by viewModel.currentPanelReport.observeAsState()
    MeasurementForm(
        onNavigateBack = { navController.popBackStack() },
        onNext = { navController.navigate("create/panel/checks") },
        report = reportWithImages?.report,
        updateReport = { viewModel.updateReport(it) }
    )
}

@Composable
fun MeasurementForm(
    onNavigateBack: () -> Unit,
    onNext: () -> Unit,
    report: PanelReport?,
    updateReport: (PanelReport) -> Unit
) {
    fun handleUpdate(newReport: PanelReport?) {
        if (newReport != null) {
            updateReport(newReport)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Pengukuran") },
                navigationIcon = {
                    IconButton(onClick = { onNavigateBack() }) {
                         Icon(Icons.Rounded.ArrowBack, "")
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
        Column(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            // Tegangan P2P
            Text(
                "Tegangan Phase to Phase",
                modifier = Modifier.padding(top = 16.dp, bottom = 4.dp),
                style = MaterialTheme.typography.body2
            )
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.padding(bottom = 16.dp)
            ) {
                FloatField(
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 16.dp),
                    label = "R - S",
                    value = report?.teganganPhaseToPhaseRS ?: 0f,
                ) { handleUpdate(report?.copy(teganganPhaseToPhaseRS = it)) }
                FloatField(
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp),
                    label = "S - T",
                    value = report?.teganganPhaseToPhaseST ?: 0f,
                ) { handleUpdate(report?.copy(teganganPhaseToPhaseST = it)) }
                FloatField(
                    modifier = Modifier.weight(1f),
                    label = "T - R",
                    value = report?.teganganPhaseToPhaseTR ?: 0f,
                ) { handleUpdate(report?.copy(teganganPhaseToPhaseTR = it)) }
            }

            // Tegangan P2N
            Text(
                "Tegangan Phase to Neutral",
                modifier = Modifier.padding(top = 8.dp, bottom = 4.dp),
                style = MaterialTheme.typography.body2
            )
            Row(horizontalArrangement = Arrangement.SpaceEvenly) {
                FloatField(
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp),
                    label = "R - N",
                    value = report?.teganganPhaseToNeutralRN ?: 0f,
                ) { handleUpdate(report?.copy(teganganPhaseToNeutralRN = it)) }
                FloatField(
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp),
                    label = "S - N",
                    value = report?.teganganPhaseToNeutralSN ?: 0f,
                ) { handleUpdate(report?.copy(teganganPhaseToNeutralSN = it)) }
                FloatField(
                    modifier = Modifier.weight(1f),
                    label = "T - N",
                    value = report?.teganganPhaseToNeutralTN ?: 0f,
                ) { handleUpdate(report?.copy(teganganPhaseToNeutralTN = it)) }
            }
            FloatField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                label = "G - N",
                value = report?.teganganPhaseToNeutralGN ?: 0f,
            ) { handleUpdate(report?.copy(teganganPhaseToNeutralGN = it)) }
            // Todo attach dis somewhere
            TextField(
                label = { Text("Keterangan tambahan") },
                modifier = Modifier
                    .padding(top = 8.dp, bottom = 32.dp)
                    .fillMaxWidth(),
                value = report?.keteranganPhase ?: "",
                onValueChange = { handleUpdate(report?.copy(keteranganPhase = it)) },
            )

            // Ampere
            Divider()
            Text(
                "Arus / Current",
                modifier = Modifier.padding(top = 8.dp, bottom = 4.dp),
                style = MaterialTheme.typography.body2
            )
            Row(horizontalArrangement = Arrangement.SpaceEvenly) {
                FloatField(
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp),
                    label = "R",
                    value = report?.arusR ?: 0f,
                ) { handleUpdate(report?.copy(arusR = it)) }
                FloatField(
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp),
                    label = "S",
                    value = report?.arusS ?: 0f,
                ) { handleUpdate(report?.copy(arusS = it)) }
                FloatField(
                    modifier = Modifier
                        .weight(1f)
                        .padding(),
                    label = "T",
                    value = report?.arusT ?: 0f,
                ) { handleUpdate(report?.copy(arusT = it)) }
            }
            // Todo attach dis somewhere
            TextField(
                label = { Text("Keterangan") },
                modifier = Modifier
                    .padding(top = 8.dp, bottom = 32.dp)
                    .fillMaxWidth(),
                value = report?.keteranganArus ?: "",
                onValueChange = { handleUpdate(report?.copy(keteranganArus = it)) },
            )

            Divider()
            FloatField(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .fillMaxWidth(),
                label = "Frekuensi",
                value = report?.frekuensi ?: 0f,
            ) { handleUpdate(report?.copy(frekuensi = it)) }
            // Todo attach dis somewhere
            TextField(
                label = { Text("Keterangan") },
                modifier = Modifier
                    .padding(top = 8.dp, bottom = 32.dp)
                    .fillMaxWidth(),
                value = report?.keteranganFrekuensi ?: "",
                onValueChange = { handleUpdate(report?.copy(keteranganFrekuensi = it)) },
            )

            Divider()
            FloatField(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .fillMaxWidth(),
                label = "Power Factor",
                value = report?.powerFactor ?: 0f,
            ) { handleUpdate(report?.copy(powerFactor = it)) }
            // Todo attach dis somewhere
            TextField(
                label = { Text("Keterangan") },
                modifier = Modifier
                    .padding(top = 8.dp, bottom = 32.dp)
                    .fillMaxWidth(),
                value = report?.keteranganPowerFactor ?: "",
                onValueChange = { handleUpdate(report?.copy(keteranganPowerFactor = it)) },
            )

            Divider()
            TextField(
                label = { Text("Kondisi Perangkat") },
                modifier = Modifier
                    .padding(top = 16.dp)
                    .fillMaxWidth(),
                value = report?.kondisiPerangkat ?: "",
                onValueChange = { handleUpdate(report?.copy(kondisiPerangkat = it)) },
            )
            // Todo attach dis somewhere
            TextField(
                label = { Text("Keterangan") },
                modifier = Modifier
                    .padding(top = 8.dp, bottom = 32.dp)
                    .fillMaxWidth(),
                value = report?.keteranganKondisiPerangkat ?: "",
                onValueChange = { handleUpdate(report?.copy(keteranganKondisiPerangkat = it)) },
            )
        }
    }
}


@Preview
@Composable
private fun MeasurementFormPreview() {
    var report by remember { mutableStateOf(PanelReport("")) }
    MeasurementForm(
        onNavigateBack = { },
        onNext = {},
        report = report,
    ) { report = it }
}