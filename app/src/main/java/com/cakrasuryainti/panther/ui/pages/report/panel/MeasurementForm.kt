package com.cakrasuryainti.panther.ui.pages.report.panel

import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.layout.*
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
import com.cakrasuryainti.panther.ui.RootViewModel
import com.cakrasuryainti.panther.ui.components.FloatField

@Composable
fun PanelMeasurementForm(navController: NavHostController, viewModel: RootViewModel) {
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
        ScrollableColumn(modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {
            // Tegangan P2P
            Text(
                "Tegangan Phase to Phase",
                modifier = Modifier.padding(top = 16.dp, bottom = 4.dp),
                style = MaterialTheme.typography.body2
            )
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.padding(bottom = 32.dp)
            ) {
                FloatField(
                    label = "R - S",
                    modifier = Modifier.weight(1f).padding(end = 16.dp),
                    value = report?.teganganPhaseToPhaseRS ?: 0f,
                    onValueChange = { handleUpdate(report?.copy(teganganPhaseToPhaseRS = it)) },
                )
                FloatField(
                    label = "S - T",
                    value = report?.teganganPhaseToPhaseST ?: 0f,
                    modifier = Modifier.weight(1f).padding(end = 8.dp),
                    onValueChange = { handleUpdate(report?.copy(teganganPhaseToPhaseST = it)) },
                )
                FloatField(
                    label = "T - R",
                    value = report?.teganganPhaseToPhaseTR ?: 0f,
                    modifier = Modifier.weight(1f),
                    onValueChange = { handleUpdate(report?.copy(teganganPhaseToPhaseTR = it)) },
                )
            }

            // Tegangan P2N
            Divider()
            Text(
                "Tegangan Phase to Neutral",
                modifier = Modifier.padding(top = 8.dp, bottom = 4.dp),
                style = MaterialTheme.typography.body2
            )
            Row(horizontalArrangement = Arrangement.SpaceEvenly) {
                FloatField(
                    label = "R - N",
                    modifier = Modifier.weight(1f).padding(end = 8.dp),
                    value = report?.teganganPhaseToNeutralRN ?: 0f,
                    onValueChange = { handleUpdate(report?.copy(teganganPhaseToNeutralRN = it)) },
                )
                FloatField(
                    label = "S - N",
                    modifier = Modifier.weight(1f).padding(end = 8.dp),
                    value = report?.teganganPhaseToNeutralSN ?: 0f,
                    onValueChange = { handleUpdate(report?.copy(teganganPhaseToNeutralSN = it)) },
                )
                FloatField(
                    label = "T - N",
                    modifier = Modifier.weight(1f),
                    value = report?.teganganPhaseToNeutralTN ?: 0f,
                    onValueChange = { handleUpdate(report?.copy(teganganPhaseToNeutralTN = it)) },
                )
            }
            FloatField(
                label = "G - N",
                modifier = Modifier.fillMaxWidth().padding(bottom = 32.dp, top = 8.dp),
                value = report?.teganganPhaseToNeutralGN ?: 0f,
                onValueChange = { handleUpdate(report?.copy(teganganPhaseToNeutralGN = it)) },
            )

            // Ampere
            Divider()
            Text(
                "Arus / Current",
                modifier = Modifier.padding(top = 8.dp, bottom = 4.dp),
                style = MaterialTheme.typography.body2
            )
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.padding(bottom = 32.dp)
            ) {
                FloatField(
                    label = "R",
                    modifier = Modifier.weight(1f).padding(end = 8.dp),
                    value = report?.arusR ?: 0f,
                    onValueChange = { handleUpdate(report?.copy(arusR = it)) },
                )
                FloatField(
                    label = "S",
                    modifier = Modifier.weight(1f).padding(end = 8.dp),
                    value = report?.arusS ?: 0f,
                    onValueChange = { handleUpdate(report?.copy(arusS = it)) },
                )
                FloatField(
                    label = "T",
                    modifier = Modifier.weight(1f).padding(),
                    value = report?.arusT ?: 0f,
                    onValueChange = { handleUpdate(report?.copy(arusT = it)) },
                )
            }

            Divider()
            FloatField(
                label = "Frekuensi",
                modifier = Modifier.padding(top = 8.dp, end = 8.dp, bottom = 8.dp).fillMaxWidth(),
                value = report?.frekuensi ?: 0f,
                onValueChange = { handleUpdate(report?.copy(frekuensi = it)) },
            )
            FloatField(
                label = "Power Factor",
                modifier = Modifier.padding(end = 8.dp, bottom = 32.dp).fillMaxWidth(),
                value = report?.powerFactor ?: 0f,
                onValueChange = { handleUpdate(report?.copy(powerFactor = it)) },
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