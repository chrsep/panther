package com.cakrasuryainti.panther.ui.pages.report.panel

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate
import androidx.ui.tooling.preview.Preview

@Composable
fun PanelMeasurementForm(navController: NavHostController) {
    MeasurementForm(
        onNavigateBack = { navController.popBackStack() }
    ) { navController.navigate("create/panel/checks") }
}

@Composable
fun MeasurementForm(onNavigateBack: () -> Unit, onNext: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Create Panel Report") },
                navigationIcon = {
                    IconButton(onClick = { onNavigateBack() }) {
                        Icon(Icons.Rounded.ArrowBack)
                    }
                },
                actions = {
                    TextButton(onClick = { onNext() }) {
                        Text(
                            "Next",
                            color = Color.White,
                            style = MaterialTheme.typography.button
                        )
                    }
                }
            )
        })
    {
        Column(modifier = Modifier.padding(16.dp)) {
            // Tegangan P2P
            Text(
                "Tegangan Phase to Phase",
                modifier = Modifier.padding(top = 8.dp),
                style = MaterialTheme.typography.body2
            )
            Row(horizontalArrangement = Arrangement.SpaceEvenly) {
                OutlinedTextField(
                    label = { Text(text = "R - S") },
                    value = "",
                    onValueChange = {},
                    modifier = Modifier.weight(1f).padding(end = 8.dp)
                )
                OutlinedTextField(
                    label = { Text(text = "S - T") },
                    value = "",
                    onValueChange = {},
                    modifier = Modifier.weight(1f).padding(end = 8.dp)
                )
                OutlinedTextField(
                    label = { Text(text = "T - R") },
                    value = "",
                    onValueChange = {},
                    modifier = Modifier.weight(1f).padding(bottom = 16.dp)
                )
            }

            // Tegangan P2N
            Text(
                "Tegangan Phase to Neutral",
                modifier = Modifier.padding(top = 8.dp),
                style = MaterialTheme.typography.body2
            )
            Row(horizontalArrangement = Arrangement.SpaceEvenly) {
                OutlinedTextField(
                    label = { Text(text = "R - S") },
                    value = "",
                    onValueChange = {},
                    modifier = Modifier.weight(1f).padding(end = 8.dp)
                )
                OutlinedTextField(
                    label = { Text(text = "S - T") },
                    value = "",
                    onValueChange = {},
                    modifier = Modifier.weight(1f).padding(end = 8.dp),
                )
                OutlinedTextField(
                    label = { Text(text = "T - R") },
                    value = "",
                    onValueChange = {},
                    modifier = Modifier.weight(1f)
                )
            }
            OutlinedTextField(
                label = { Text(text = "G - N") },
                value = "",
                onValueChange = {},
                modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
            )

            // Ampere
            Text(
                "Arus / Current",
                modifier = Modifier.padding(top = 8.dp),
                style = MaterialTheme.typography.body2
            )
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.padding(bottom = 16.dp)
            ) {
                OutlinedTextField(
                    label = { Text(text = "R") },
                    value = "",
                    onValueChange = {},
                    modifier = Modifier.weight(1f).padding(end = 8.dp)
                )
                OutlinedTextField(
                    label = { Text(text = "S") },
                    value = "",
                    onValueChange = {},
                    modifier = Modifier.weight(1f).padding(end = 8.dp)
                )
                OutlinedTextField(
                    label = { Text(text = "T") },
                    value = "",
                    onValueChange = {},
                    modifier = Modifier.weight(1f).padding()
                )
            }

            OutlinedTextField(
                label = { Text(text = "Frekuensi") },
                value = "",
                onValueChange = {},
                modifier = Modifier.padding(end = 8.dp).fillMaxWidth()
            )
            OutlinedTextField(
                label = { Text(text = "Power Factor") },
                value = "",
                onValueChange = {},
                modifier = Modifier.padding(end = 8.dp).fillMaxWidth()
            )
        }
    }
}


@Preview
@Composable
fun MeasurementFormPreview() {
    MeasurementForm(onNavigateBack = { }, onNext = {})
}