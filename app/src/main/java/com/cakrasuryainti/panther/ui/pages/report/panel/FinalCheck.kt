package com.cakrasuryainti.panther.ui.pages.report.panel

import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AddAPhoto
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.AmbientContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.ui.tooling.preview.Preview
import com.cakrasuryainti.panther.db.model.PanelReport
import com.cakrasuryainti.panther.db.model.ReportImage
import com.cakrasuryainti.panther.registerForActivityResult
import com.cakrasuryainti.panther.ui.components.ReportImageListItem
import com.cakrasuryainti.panther.ui.theme.PantherTheme
import androidx.navigation.compose.navigate
import com.cakrasuryainti.panther.R
import com.cakrasuryainti.panther.domain.saveImagesIntoReport

@Composable
fun FinalCheck(navController: NavHostController, viewModel: PanelViewModel) {
    val reportWithImages by viewModel.currentPanelReport.observeAsState()
    val context = AmbientContext.current

    var isLoading by remember { mutableStateOf(false) }

    Form(
        onNavigateBack = { navController.popBackStack() },
        onDone = {
            isLoading = true
            viewModel.finalizeReport(
                reportWithImages,
                context,
                onSuccess = { navController.navigate("create/panel/done") },
                onCatch = { isLoading = false }
            )
        },
        report = reportWithImages?.report,
        updateReport = { viewModel.updateReport(it) },
        saveImages = { viewModel.saveImages(it) },
        images = reportWithImages?.images ?: listOf(),
        updateImage = { viewModel.updateImage(it) },
        navigateToImageEdit = { navController.navigate("create/panel/image/${it.id}") },
        removeImage = { viewModel.removeImage(it) },
        isLoading = isLoading
    )
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
    removeImage: (ReportImage) -> Unit,
    navigateToImageEdit: (ReportImage) -> Unit,
    isLoading: Boolean,
) {
    val context = AmbientContext.current
    val getImages = registerForActivityResult(
        ActivityResultContracts.GetMultipleContents()
    ) { uris ->
        if (report != null) {
            saveImagesIntoReport(context, uris, report.id, saveImages)
        }
    }

    fun handleRemoveImage(image: ReportImage) {
        if (context.deleteFile(image.id)) removeImage(image)
    }

    fun handleUpdate(report: PanelReport?) {
        if (report != null) updateReport(report)
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
                    TextButton(onClick = { onDone() }) {
                        Text(
                            "Done",
                            color = Color.White,
                        )
                    }
                }
            )
        })
    {
        if (isLoading) {
            LinearProgressIndicator(Modifier.fillMaxWidth())
        }
        ConstraintLayout(modifier = Modifier.fillMaxHeight()) {
            val (fab) = createRefs()

            ScrollableColumn(modifier = Modifier.padding(start = 8.dp, end = 8.dp)) {
                TextField(
                    label = { Text("Catatan / Saran") },
                    value = report?.notesAndRecommendation ?: "",
                    onValueChange = { handleUpdate(report?.copy(notesAndRecommendation = it)) },
                    maxLines = 30,
                    modifier = Modifier.fillMaxWidth().padding(8.dp, top = 16.dp)
                )
                Text(
                    "Gambar",
                    modifier = Modifier.padding(top = 32.dp, start = 8.dp).fillMaxWidth(),
                    style = MaterialTheme.typography.h6
                )

                if (images.isEmpty()) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Image(
                            vectorResource(id = R.drawable.undraw_camera),
                            modifier = Modifier.width(180.dp).padding(top = 72.dp, bottom = 16.dp)
                        )
                        Text(
                            "Belum Ada Gambar Terpasang",
                            style = MaterialTheme.typography.h5,
                            modifier = Modifier.width(240.dp),
                            textAlign = TextAlign.Center
                        )
                    }
                }

                images.sortedByDescending { it.createdAt }.forEach { image ->
                    ReportImageListItem(
                        image = image,
                        modifier = Modifier.padding(
                            start = 12.dp
                        ),
                        updateDescription = { updateImage(it) },
                        removeImage = { handleRemoveImage(it) },
                        onEditClick = { navigateToImageEdit(it) }
                    )
                }
            }
            FloatingActionButton(
                onClick = { getImages.launch("image/*") },
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
            removeImage = {},
            navigateToImageEdit = {},
            isLoading = false
        )
    }
}