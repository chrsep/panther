package com.cakrasuryainti.panther.ui.pages

import androidx.compose.foundation.Text
import androidx.compose.material.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ContextAmbient
import androidx.compose.ui.viewinterop.viewModel
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.cakrasuryainti.panther.JobDesc
import com.cakrasuryainti.panther.PanelReport
import com.cakrasuryainti.panther.Status
import com.cakrasuryainti.panther.generatePanelReport
import java.time.Instant
import java.util.*

class HomeViewModel @ViewModelInject constructor() : ViewModel() {
    fun generateReport(outputPath: String) {
        generatePanelReport(
            PanelReport(
                UUID.randomUUID().toString(),
                "Test",
                "test",
                "test",
                "test",
                "test",
                JobDesc.Maintenance,
                Instant.now(),
                377f,
                379f,
                378f,
                217f,
                220f,
                217f,
                0f,
                87.24f,
                573f,
                65.5f,
                49.93f,
                0.95f,
                Status.Ok,
                Status.Ok,
                Status.Ok,
                Status.Ok,
                Status.Ok,
                Status.Ok,
                Status.Ok,
                Status.Ok,
                Status.Ok,
                Status.Ok,
                Status.Ok,
                Status.Ok,
                Status.Ok,
                Status.Ok,
                Status.Ok,
                Status.Ok,
                Status.Ok,
                Status.Ok,
                Status.Ok,
                Status.Ok,
                Status.Ok,
                Status.Ok,
                "it's all good"
            ),
            listOf(),
            outputPath,
        )
    }
}

@Composable
fun Home() {
    val outputPath = ContextAmbient.current.filesDir.absolutePath + "output.pdf"
    val viewModel: HomeViewModel = viewModel()
    Button(onClick = { viewModel.generateReport(outputPath) }) {
        Text(text = "Generate")
    }
}