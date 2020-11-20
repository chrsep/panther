package com.cakrasuryainti.panther.ui.pages.report.panel

import android.content.Intent
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
import androidx.core.content.ContextCompat.startActivity
import androidx.core.content.FileProvider.getUriForFile
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate
import androidx.ui.tooling.preview.Preview
import com.cakrasuryainti.panther.R
import com.cakrasuryainti.panther.db.model.PanelReport
import com.cakrasuryainti.panther.ui.theme.PantherTheme
import java.io.File

@Composable
fun ReportDone(navController: NavHostController, viewModel: PanelViewModel) {
    val reportWithImages by viewModel.currentPanelReport.observeAsState()

    ReportDaoView(reportWithImages?.report) {
        // pop all backstack
        while (navController.popBackStack()) {
        }
        navController.navigate("home")
    }
}

@Composable
private fun ReportDaoView(report: PanelReport?, navigateToHome: () -> Unit) {
    val context = ContextAmbient.current
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
                if (report != null) {
                    val file = File(report.pdfFilePath)
                    val uri = getUriForFile(context, "com.cakrasuryainti.panther.provider", file)
                    val sendIntent: Intent = Intent().apply {
                        action = Intent.ACTION_SEND
                        putExtra(Intent.EXTRA_STREAM, uri)
                        flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
                        type = "application/pdf"
                    }

                    val shareIntent = Intent.createChooser(sendIntent, "Share Report")
                    startActivity(context, shareIntent, null)
                }
            }) {
                Text("Share PDF")
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