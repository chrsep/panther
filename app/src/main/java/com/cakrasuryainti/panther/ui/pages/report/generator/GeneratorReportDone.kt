package com.cakrasuryainti.panther.ui.pages.report.generator

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ContextAmbient
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate
import androidx.ui.tooling.preview.Preview
import com.cakrasuryainti.panther.R
import com.cakrasuryainti.panther.db.model.PanelReport
import com.cakrasuryainti.panther.domain.shareReportPdf
import com.cakrasuryainti.panther.ui.theme.PantherTheme

@Composable
fun GeneratorReportDoneContainer(navController: NavHostController, viewModel: GeneratorViewModel) {
    val reportWithImages by viewModel.currentPanelReport.observeAsState()

    GeneratorReportDone(reportWithImages?.report) {
//        viewModel.newReport()
        // pop all backstack
        while (navController.popBackStack()) {
        }
        navController.navigate("home")
    }
}

@Composable
private fun GeneratorReportDone(report: PanelReport?, navigateToHome: () -> Unit) {
    val context = ContextAmbient.current
    Surface {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth().fillMaxHeight()
        ) {
            Text(
                report?.customer ?: "",
                modifier = Modifier.padding(bottom = 4.dp),
                style = MaterialTheme.typography.h6,
            )
            Text(
                report?.jobDesc?.name ?: "",
                modifier = Modifier.padding(bottom = 32.dp),
                style = MaterialTheme.typography.subtitle1,
            )
            Image(
                asset = vectorResource(id = R.drawable.undraw_done),
                modifier = Modifier.width(180.dp).padding(bottom = 16.dp)
            )
            Text(
                "Laporan Selesai",
                style = MaterialTheme.typography.h5,
                modifier = Modifier.width(240.dp).padding(bottom = 16.dp),
                textAlign = TextAlign.Center
            )
            Row(modifier = Modifier.padding(bottom = 32.dp)) {
                OutlinedButton(
                    modifier = Modifier.padding(end = 8.dp),
                    onClick = { navigateToHome() },
                ) {
                    Text("Kembali")
                }
                Button(onClick = {
                    if (report != null) shareReportPdf(context, report)
                }) {
                    Text("Share PDF")
                }
            }
        }
    }
}

@Preview
@Composable
private fun ReportPreview() {
    PantherTheme {
        Scaffold {
            GeneratorReportDone(PanelReport("", customer = "PT Luar Biasa")) {

            }
        }
    }
}