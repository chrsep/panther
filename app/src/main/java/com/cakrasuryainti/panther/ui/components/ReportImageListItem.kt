package com.cakrasuryainti.panther.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawOpacity
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.cakrasuryainti.panther.db.model.ReportImage
import com.cakrasuryainti.panther.ui.theme.PantherTheme
import dev.chrisbanes.accompanist.coil.CoilImage

@Composable
fun ReportImageListItem(
    image: ReportImage,
    modifier: Modifier = Modifier,
    removeImage: (ReportImage) -> Unit,
    updateDescription: (ReportImage) -> Unit,
) {
    var isEditing by remember { mutableStateOf(false) }
    var isDeleting by remember { mutableStateOf(false) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.height(72.dp)
    ) {
        Surface(shape = RoundedCornerShape(8.dp)) {
            CoilImage(
                data = "file://" + image.filePath,
                fadeIn = true,
                contentScale = ContentScale.Crop,
                modifier = Modifier.height(48.dp).width(48.dp),
            )
        }
        if (!isEditing) {
            ConstraintLayout(modifier = Modifier.fillMaxWidth().height(56.dp)) {
                val (text, button) = createRefs()
                if (image.description != "") {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.constrainAs(text) {
                            start.linkTo(parent.start, 16.dp)
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                            end.linkTo(button.start)
                            height = Dimension.fillToConstraints
                            width = Dimension.fillToConstraints
                        }
                    ) {
                        Text(image.description)
                    }
                } else {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.constrainAs(text) {
                            start.linkTo(parent.start, 16.dp)
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                            end.linkTo(button.start)
                            height = Dimension.fillToConstraints
                            width = Dimension.fillToConstraints
                        },
                    ) {
                        Text(
                            "Belum ada deskripsi",
                            color = MaterialTheme.colors.onSurface.copy(alpha = 0.6f),
                        )
                    }
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.constrainAs(button) {
                        bottom.linkTo(parent.bottom)
                        top.linkTo(parent.top)
                        end.linkTo(parent.end)
                    }
                ) {
                    IconButton(onClick = { isEditing = true }) {
                        Icon(Icons.Rounded.Edit, modifier = Modifier.drawOpacity(0.7f))
                    }
                    IconButton(onClick = {
                        isDeleting = true
                    }) {
                        Icon(Icons.Rounded.Remove, modifier = Modifier.drawOpacity(0.7f))
                    }
                }
            }
        } else {
            EditDescription(
                description = image.description,
                onSave = {
                    isEditing = false
                    updateDescription(image.copy(description = it))
                }
            )
        }

        if (isDeleting) {
            ConfirmDeleteDialog(
                onDismissRequest = { isDeleting = false },
                description = image.description,
                onDelete = { removeImage(image) },
            )
        }
    }
}

@Composable
fun EditDescription(description: String, onSave: (String) -> Unit) {
    var newDescription by remember { mutableStateOf(description) }

    ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
        val (textField, button) = createRefs()

        OutlinedTextField(
            value = newDescription,
            onValueChange = { newDescription = it },
            label = { Text("Deskripsi") },
            modifier = Modifier.padding(start = 8.dp, bottom = 8.dp)
                .constrainAs(textField) {
                    end.linkTo(button.start)
                    start.linkTo(parent.start)
                    width = Dimension.fillToConstraints
                }
        )
        IconButton(
            onClick = { onSave(newDescription) },
            modifier = Modifier.width(60.dp).constrainAs(button) {
                end.linkTo(parent.end)
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
            }
        ) {
            Icon(Icons.Rounded.Check, modifier = Modifier.drawOpacity(0.7f))
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
            TextButton(
                onClick = {
                    onDismissRequest()
                },
            ) {
                Text("Jangan")
            }
        }
    )
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

@Preview
@Composable
private fun ReportImagePreview() {
    PantherTheme {
        Scaffold {
            Surface(modifier = Modifier.padding(8.dp)) {
                ReportImageListItem(
                    ReportImage(
                        description = "some description is a very long text really imprevies ass",
                        reportId = "test-image.jpg",
                        filePath = "test-image.jpg",
                        id = ""
                    ),
                    modifier = Modifier.padding(8.dp),
                    removeImage = {},
                    updateDescription = {}
                )
            }
        }
    }
}

@Preview
@Composable
private fun ReportImageWithoutDescriptionPreview() {
    PantherTheme {
        Scaffold {
            Surface(modifier = Modifier.padding(8.dp)) {
                ReportImageListItem(
                    ReportImage(
                        reportId = "test-image.jpg",
                        filePath = "test-image.jpg",
                        id = ""
                    ),
                    modifier = Modifier.padding(8.dp),
                    removeImage = {},
                    updateDescription = {}
                )
            }
        }
    }
}