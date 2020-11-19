package com.cakrasuryainti.panther.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.cakrasuryainti.panther.db.model.ReportImage
import com.cakrasuryainti.panther.ui.theme.PantherTheme
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
        Column {
            Text("Deskripsi", modifier = Modifier.padding(start = 8.dp))
            Text(image.description, modifier = Modifier.padding(start = 8.dp))
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