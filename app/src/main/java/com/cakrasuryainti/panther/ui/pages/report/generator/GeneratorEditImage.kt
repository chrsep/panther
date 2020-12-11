package com.cakrasuryainti.panther.ui.pages.report.generator

import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Save
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.compose.ui.tooling.preview.Preview
import com.cakrasuryainti.panther.db.model.ReportImage
import com.cakrasuryainti.panther.ui.theme.PantherTheme
import dev.chrisbanes.accompanist.coil.CoilImage

@Composable
fun GeneratorEditImageContainer(
    navController: NavHostController,
    panelViewModel: GeneratorViewModel,
    imageId: String?
) {
    val image by panelViewModel.getImage(imageId).collectAsState(initial = ReportImage("", "", ""))

    GeneratorEditImage(
        image,
        onNavigateBack = {
            navController.popBackStack()
        },
        onSave = { newImage ->
            panelViewModel.updateImage(newImage)
            navController.popBackStack()
        },
        onDelete = { target ->
            panelViewModel.removeImage(target)
            navController.popBackStack()
        }
    )
}

@Composable
private fun GeneratorEditImage(
    image: ReportImage,
    onNavigateBack: () -> Unit,
    onSave: (ReportImage) -> Unit,
    onDelete: (ReportImage) -> Unit,
) {
    var isDeleting by remember { mutableStateOf(false) }
    var newImage by remember { mutableStateOf(image) }

    onCommit(image) {
        newImage = image
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Edit Gambar") },
                navigationIcon = {
                    IconButton(onClick = { onNavigateBack() }) {
                        Icon(Icons.Rounded.ArrowBack)
                    }
                },
                actions = {
                    IconButton(onClick = { isDeleting = true }) {
                        Icon(Icons.Rounded.Delete)
                    }
                    IconButton(onClick = { onSave(newImage) }) {
                        Icon(Icons.Rounded.Save)
                    }
                }
            )
        })
    {
        ScrollableColumn {
            TextField(
                value = newImage.description,
                onValueChange = {
                    newImage = newImage.copy(description = it)
                },
                label = { Text("Deskripsi") },
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
            )
            CoilImage(
                data = "file://" + image.filePath,
                fadeIn = true,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxWidth(),
            )
        }
    }
    if (isDeleting) {
        ConfirmDeleteDialog(
            onDismissRequest = { isDeleting = false },
            description = image.description,
            onDelete = { onDelete(newImage) },
        )
    }
}

@Preview
@Composable
fun ConfirmDeleteDialogPreview() {
    PantherTheme {
        Scaffold {
            ConfirmDeleteDialog(onDismissRequest = {}, onDelete = {}, "Ini adalah gambar")
        }
    }
}

@Composable
fun ConfirmDeleteDialog(onDismissRequest: () -> Unit, onDelete: () -> Unit, description: String) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        title = { Text("Hapus Gambar?") },
        text = {
            if (description != "") Text("""Gambar dengan deskripsi "$description" akan dihapus.""")
        },
        confirmButton = {
            Button(
                onClick = {
                    onDelete()
                    onDismissRequest()
                },
            ) {
                Text("Hapus")
            }
        },
        dismissButton = {
            TextButton( onClick = { onDismissRequest() }, ) {
                Text("Jangan")
            }
        }
    )
}


@Preview
@Composable
private fun EditImagePreview() {
    GeneratorEditImage(
        image = ReportImage("", "", ""),
        onNavigateBack = {},
        onSave = {},
        onDelete = {}
    )
}