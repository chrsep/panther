package com.cakrasuryainti.panther.ui.pages.report

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import androidx.ui.tooling.preview.Preview

@Composable
fun CreateReport(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Create Report") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Rounded.ArrowBack)
                    }
                },
            )
        }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth().fillMaxHeight()
        ) {
            Button(
                onClick = { navController.navigate("create/panel") },
                Modifier.padding(bottom = 8.dp)
            ) {
                Text(text = "Panel")
            }
            Button(
                onClick = { navController.navigate("create/genset") },
                Modifier.padding(bottom = 16.dp)
            ) {
                Text(text = "Genset")
            }
        }
    }
}

@Composable
@Preview
fun CreateReportPreview() {
    val navController = rememberNavController()
    CreateReport(navController)
}