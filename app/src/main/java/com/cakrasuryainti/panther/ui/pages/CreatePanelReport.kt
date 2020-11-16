package com.cakrasuryainti.panther.ui.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavHostController
import androidx.ui.tooling.preview.Preview
import com.cakrasuryainti.panther.JobDesc
import com.cakrasuryainti.panther.ui.theme.PantherTheme
import com.google.android.material.chip.Chip
import androidx.navigation.compose.navigate

@Composable
fun CreatePanelReport(navController: NavHostController) {
    MetaForm(
        onNavigateBack = { navController.popBackStack() },
        onNext = { navController.navigate("create/panel/checks") }
    )
}

@Composable
fun MetaForm(onNavigateBack: () -> Unit, onNext: () -> Unit) {
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
                        Text("Next", color = Color.White)
                    }
                }
            )
        })
    {
        Column {
            Text("Job Desc", modifier = Modifier.padding(start = 8.dp, top = 8.dp))
            Row {
                JobDesc.values().forEach {
                    AndroidView(
                        modifier = Modifier.padding(start = 8.dp),
                        viewBlock = { context -> Chip(context) }
                    ) { chip ->
                        chip.isChecked = true
                        chip.text = it.name
                    }
                }
            }
            TextField(
                value = "",
                onValueChange = {},
                label = { Text("Pekerjaan") },
                modifier = Modifier.fillMaxWidth().padding(8.dp)
            )
            TextField(
                value = "",
                onValueChange = {},
                label = { Text("Panel") },
                modifier = Modifier.fillMaxWidth().padding(8.dp)
            )
            TextField(
                value = "",
                onValueChange = {},
                label = { Text("Type / Model") },
                modifier = Modifier.fillMaxWidth().padding(8.dp)
            )
            TextField(
                value = "",
                onValueChange = {},
                label = { Text("Serial Number") },
                modifier = Modifier.fillMaxWidth().padding(8.dp)
            )
            TextField(
                value = "",
                onValueChange = {},
                label = { Text("Lokasi") },
                modifier = Modifier.fillMaxWidth().padding(8.dp)
            )
            Dialog(onDismissRequest = {}) {
                Text(text = "test")
            }
        }
    }
}

@Preview
@Composable
fun FormPreview() {
    PantherTheme {
        MetaForm(onNavigateBack = {}, onNext = {})
    }
}