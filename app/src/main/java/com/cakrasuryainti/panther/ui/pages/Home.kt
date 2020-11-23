package com.cakrasuryainti.panther.ui.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AddCircleOutline
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Share
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawOpacity
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ContextAmbient
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.semantics.accessibilityLabel
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import androidx.ui.tooling.preview.Preview
import com.cakrasuryainti.panther.R
import com.cakrasuryainti.panther.db.model.PanelReport
import com.cakrasuryainti.panther.domain.shareReportPdf
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*

@ExperimentalMaterialApi
@Composable
fun HomeContainer(navController: NavHostController) {
    Home(navController)
}

@ExperimentalMaterialApi
@Composable
fun Home(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Report Creator") })
        }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth().fillMaxHeight()
        ) {
            Button(
                onClick = { navController.navigate("create") },
                Modifier.padding(bottom = 8.dp)
            ) {
                Text(text = "Create Report")
            }
            OutlinedButton(onClick = { navController.navigate("saved") }, Modifier.padding(8.dp)) {
                Text(text = "Saved Report")
            }
//            OutlinedButton(onClick = { navController.navigate("help") }, Modifier.padding(8.dp)) {
//                Text(text = "Help")
//            }
        }
    }
}


@ExperimentalMaterialApi
@Composable
@Preview
fun HomePreview() {
    val navController = rememberNavController()
    Home(navController)
}