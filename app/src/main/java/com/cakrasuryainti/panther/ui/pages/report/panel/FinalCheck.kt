package com.cakrasuryainti.panther.ui.pages.report.panel

import android.content.Context
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AddAPhoto
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ContextAmbient
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.ui.tooling.preview.Preview
import com.cakrasuryainti.panther.db.model.PanelReport
import com.cakrasuryainti.panther.db.model.ReportImage
import com.cakrasuryainti.panther.registerForActivityResult
import com.cakrasuryainti.panther.ui.RootViewModel
import com.cakrasuryainti.panther.ui.components.ReportImageListItem
import com.cakrasuryainti.panther.ui.theme.PantherTheme
import timber.log.Timber
import java.util.*

@Composable
fun FinalCheck(navController: NavHostController, viewModel: RootViewModel) {
    val reportWithImages by viewModel.currentPanelReport.observeAsState()

    Form(
        onNavigateBack = { navController.popBackStack() },
        onDone = {},
        report = reportWithImages?.report,
        updateReport = { viewModel.updateReport(it) },
        saveImages = { viewModel.saveImages(it) },
        images = reportWithImages?.images ?: listOf(),
        updateImage = { viewModel.updateImage(it) }
    ) { viewModel.removeImage(it) }
}

@Composable
private fun Form(
    onNavigateBack: () -> Unit,
    onDone: () -> Unit,
    report: PanelReport?,
    updateReport: (PanelReport) -> Unit,
    saveImages: (List<ReportImage>) -> Unit,
    images: List<ReportImage>,
    updateImage: (ReportImage) -> Unit,
    removeImage: (ReportImage) -> Unit
) {
    val context = ContextAmbient.current
    val getContentsLauncher = registerForActivityResult(
        ActivityResultContracts.GetMultipleContents()
    ) { uris ->
        Timber.d("callback called")
        val newImages = uris.map { uri ->
            if (uri.path != null) {
                // Open internal file to save to
                val id = UUID.randomUUID().toString()
                context.openFileOutput(id, Context.MODE_PRIVATE).use { outputStream ->
                    // Open selected image
                    context.contentResolver.openInputStream(uri).use { stream ->
                        // Save it to internal file.
                        outputStream.write(stream?.readBytes())
                    }
                }
                return@map ReportImage(
                    id = id,
                    reportId = report?.id ?: "",
                    filePath = context.filesDir.absolutePath + "/" + id,
                )
            } else {
                return@map null
            }
        }.filterNotNull()
        saveImages(newImages)
    }

    fun handleRemoveImage(image: ReportImage) {
        val success = context.deleteFile(image.id)
        if (success) {
            removeImage(image)
        }
    }

    fun handleDone() {
        onDone()
    }

    fun handleUpdate(report: PanelReport?) {
        if (report != null) {
            updateReport(report)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Data Tambahan") },
                navigationIcon = {
                    IconButton(onClick = { onNavigateBack() }) {
                        Icon(Icons.Rounded.ArrowBack)
                    }
                },
                actions = {
                    TextButton(onClick = { handleDone() }) {
                        Text(
                            "Done",
                            color = Color.White,
                        )
                    }
                }
            )
        })
    {
        ConstraintLayout(modifier = Modifier.fillMaxHeight()) {
            val (fab) = createRefs()

            ScrollableColumn(modifier = Modifier.padding(start = 8.dp, end = 8.dp)) {
                OutlinedTextField(
                    label = { Text("Catatan / Saran") },
                    value = report?.notesAndRecommendation ?: "",
                    onValueChange = { handleUpdate(report?.copy(notesAndRecommendation = it)) },
                    maxLines = 30,
                    modifier = Modifier.fillMaxWidth().padding(8.dp)
                )
                Text(
                    "Gambar",
                    modifier = Modifier.padding(top = 16.dp, start = 8.dp),
                    style = MaterialTheme.typography.body2
                )
                images.sortedByDescending { it.createdAt }.forEach { image ->
                    ReportImageListItem(
                        image = image,
                        modifier = Modifier.padding(
                            start = 12.dp
                        ),
                        updateDescription = { updateImage(it) },
                        removeImage = { handleRemoveImage(it) },
                    )
                }
            }
            FloatingActionButton(
                onClick = { getContentsLauncher.launch("image/*") },
                modifier = Modifier.constrainAs(fab) {
                    bottom.linkTo(parent.bottom, 16.dp)
                    end.linkTo(parent.end, 16.dp)
                },
            ) { Icon(Icons.Rounded.AddAPhoto) }
        }
    }
}

@Preview
@Composable
private fun FormPreview() {
    var report by remember { mutableStateOf(PanelReport("")) }
    var images by remember { mutableStateOf(listOf(ReportImage("", "", ""))) }

    PantherTheme {
        Form(
            onNavigateBack = {},
            onDone = {},
            report = report,
            updateReport = { report = it },
            saveImages = { images = images.plus(it) },
            images = images,
            updateImage = {},
        ) {}
    }
}