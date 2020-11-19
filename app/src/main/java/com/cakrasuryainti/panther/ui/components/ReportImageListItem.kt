package com.cakrasuryainti.panther.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawOpacity
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.cakrasuryainti.panther.db.model.ReportImage
import com.cakrasuryainti.panther.ui.theme.PantherTheme
import com.itextpdf.layout.property.VerticalAlignment
import dev.chrisbanes.accompanist.coil.CoilImage

@Composable
fun ReportImageListItem(image: ReportImage, modifier: Modifier = Modifier) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        Surface(
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.height(48.dp).width(48.dp),
        ) {
            CoilImage(
                data = "file://" + image.filePath,
                fadeIn = true,
                contentScale = ContentScale.Crop,
            )
        }
        ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
            val (text, edit) = createRefs()
            if (image.description != "") {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.constrainAs(text) {
                        start.linkTo(parent.start, 16.dp)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        end.linkTo(edit.start)
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
                        end.linkTo(edit.start)
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
            IconButton(
                onClick = {},
                modifier = Modifier.constrainAs(edit) {
                    end.linkTo(parent.end)
                }
            ) {
                Icon(Icons.Rounded.Edit, modifier = Modifier.drawOpacity(0.7f))
            }
        }
    }
}

@Preview
@Composable
private fun ReportImagePreview() {
    PantherTheme {
        Surface(modifier = Modifier.padding(8.dp)) {
            ReportImageListItem(
                ReportImage(
                    description = "some description",
                    reportId = "test-image.jpg",
                    filePath = "test-image.jpg",
                    id = ""
                ),
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Preview
@Composable
private fun ReportImageWithoutDescriptionPreview() {
    PantherTheme {
        Surface(modifier = Modifier.padding(8.dp)) {
            ReportImageListItem(
                ReportImage(
                    reportId = "test-image.jpg",
                    filePath = "test-image.jpg",
                    id = ""
                ),
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}