package com.cakrasuryainti.panther.ui.pages.report.panel

import android.content.Intent
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.ContextAmbient
import androidx.core.content.ContextCompat.startActivity
import androidx.core.content.FileProvider.getUriForFile
import androidx.navigation.NavHostController
import com.cakrasuryainti.panther.db.model.PanelReport
import com.cakrasuryainti.panther.ui.RootViewModel
import java.io.File

@Composable
fun ReportDone(navController: NavHostController, viewModel: RootViewModel) {
    val reportWithImages by viewModel.currentPanelReport.observeAsState()

    ReportDaoView(reportWithImages?.report)
}

@Composable
private fun ReportDaoView(report: PanelReport?) {
    val context = ContextAmbient.current
    Row {
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
            Text("Share")
        }
    }
}
