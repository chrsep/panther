package com.cakrasuryainti.panther.ui.pages.report.panel

import androidx.compose.foundation.layout.Row
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.NavHostController
import com.cakrasuryainti.panther.db.model.PanelReport
import com.cakrasuryainti.panther.ui.RootViewModel

@Composable
fun ReportDone(navController: NavHostController, viewModel: RootViewModel) {
    val reportWithImages by viewModel.currentPanelReport.observeAsState()

    ReportDaoView(reportWithImages?.report)
}

@Composable
private fun ReportDaoView(report: PanelReport?) {
    Row {
        Button(onClick = {}) {
            Text("Share")
        }
    }
}
