package com.cakrasuryainti.panther.ui.pages.report.panel

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.viewinterop.viewModel
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate
import androidx.ui.tooling.preview.Preview
import com.cakrasuryainti.panther.db.PanelReportDao
import com.cakrasuryainti.panther.db.model.JobDesc
import com.cakrasuryainti.panther.ui.theme.PantherTheme
import com.google.android.material.chip.Chip

class PanelReportMetaFormViewModel @ViewModelInject constructor(dao: PanelReportDao) : ViewModel() {

}

@Composable
fun PanelReportMetaForm(navController: NavHostController) {
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
        Column {
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

            Surface(
                modifier = Modifier.fillMaxWidth().padding(8.dp),
                border = BorderStroke(1.dp, Color.LightGray),
                shape = RoundedCornerShape(8.dp)
            ) {
                Column(
                    modifier = Modifier.padding(8.dp)
                ) {
                    Text(
                        "Job Description",
                        modifier = Modifier.padding(start = 8.dp, top = 8.dp),
                        style = MaterialTheme.typography.body2
                    )
                    Row {
                        JobDesc.values().forEach {
                            AndroidView(
                                modifier = Modifier.padding(start = 8.dp),
                                viewBlock = { context ->
                                    Chip(context).apply {
                                        setOnClickListener {

                                        }
                                    }
                                }
                            ) { chip ->
                                chip.isEnabled = true
                                chip.isCheckable = true
                                chip.text = it.name
                            }
                        }
                    }
                }
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