package com.cakrasuryainti.panther.ui.pages.report.panel

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate
import androidx.compose.ui.tooling.preview.Preview
import com.cakrasuryainti.panther.R
import com.cakrasuryainti.panther.db.model.PanelReport
import com.cakrasuryainti.panther.domain.shareReportPdf
import com.cakrasuryainti.panther.ui.theme.PantherTheme

@Composable
fun ReportDone(navController: NavHostController, viewModel: PanelViewModel) {
    val reportWithImages by viewModel.currentPanelReport.observeAsState()

    ReportDaoView(reportWithImages?.report) {
        viewModel.newReport()
        // pop all backstack
        while (navController.popBackStack()) {
        }
        navController.navigate("home")
    }
}

@Composable
private fun ReportDaoView(report: PanelReport?, navigateToHome: () -> Unit) {
    val context = LocalContext.current
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
                painterResource(id = R.drawable.undraw_done),
                "placeholoder",
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
            ReportDaoView(PanelReport("", customer = "PT Luar Biasa")) {

            }
        }
    }
}