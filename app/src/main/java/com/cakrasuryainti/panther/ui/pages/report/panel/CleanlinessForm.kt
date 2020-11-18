package com.cakrasuryainti.panther.ui.pages.report.panel

import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate
import com.cakrasuryainti.panther.db.model.Status
import com.cakrasuryainti.panther.ui.RootViewModel
import com.cakrasuryainti.panther.ui.components.StatusCheckField

@Composable
fun CleanlinessForm(navController: NavHostController, viewModel: RootViewModel) {
    Form(
        onNavigateBack = { navController.popBackStack() },
        onNext = { navController.navigate("create/panel/photos") })
}

@Composable
private fun Form(onNavigateBack: () -> Unit, onNext: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Kebersihan") },
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
        ScrollableColumn {
            Column(modifier = Modifier.padding(16.dp)) {
                StatusCheckField(
                    label = "1. Luar panel",
                    value = Status.NotAvailable,
                    onChange = {},
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                StatusCheckField(
                    label = "2. Dalam panel",
                    value = Status.NotAvailable,
                    onChange = {},
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                StatusCheckField(
                    label = "3. Jalur kabel power",
                    value = Status.NotAvailable,
                    onChange = {},
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                StatusCheckField(
                    label = "4. Kondisi ruangan",
                    value = Status.NotAvailable,
                    onChange = {},
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                StatusCheckField(
                    label = "5. Ruangan / Lingkungan",
                    value = Status.NotAvailable,
                    onChange = {},
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                StatusCheckField(
                    label = "6. Penerangan",
                    value = Status.NotAvailable,
                    onChange = {},
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                StatusCheckField(
                    label = "7. Fan Ruangan",
                    value = Status.NotAvailable,
                    onChange = {},
                    modifier = Modifier.padding(bottom = 16.dp)
                )
            }
        }
    }
}