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
import androidx.ui.tooling.preview.Preview
import com.cakrasuryainti.panther.db.model.Status
import com.cakrasuryainti.panther.ui.components.StatusCheckField

@Composable
fun VisualCheckForm(navController: NavHostController) {
    Form(
        onNavigateBack = { navController.popBackStack() },
        onNext = { navController.navigate("create/panel/cleanliness") })
}

@Composable
private fun Form(onNavigateBack: () -> Unit, onNext: () -> Unit) {
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
        ScrollableColumn {
            Column(modifier = Modifier.padding(16.dp)) {
                StatusCheckField(
                    label = "1. Relay / time control",
                    value = Status.NotAvailable,
                    onChange = {},
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                StatusCheckField(
                    label = "2. Kabel instalasi",
                    value = Status.NotAvailable,
                    onChange = {},
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                StatusCheckField(
                    label = "3. ACB/MCCB/MCB (input)",
                    value = Status.NotAvailable,
                    onChange = {},
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                StatusCheckField(
                    label = "4. ACB/MCCB/MCB (output)",
                    value = Status.NotAvailable,
                    onChange = {},
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                StatusCheckField(
                    label = "5. Lampu indikator panel",
                    value = Status.NotAvailable,
                    onChange = {},
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                StatusCheckField(
                    label = "6. Fuse",
                    value = Status.NotAvailable,
                    onChange = {},
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                StatusCheckField(
                    label = "7. Terminal power",
                    value = Status.NotAvailable,
                    onChange = {},
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                StatusCheckField(
                    label = "8. Amper meter",
                    value = Status.NotAvailable,
                    onChange = {},
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                StatusCheckField(
                    label = "9. Volt meter",
                    value = Status.NotAvailable,
                    onChange = {},
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                StatusCheckField(
                    label = "10. Modul control status",
                    value = Status.NotAvailable,
                    onChange = {},
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                StatusCheckField(
                    label = "11. Timer (hour counter)",
                    value = Status.NotAvailable,
                    onChange = {},
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                StatusCheckField(
                    label = "12. Push button ON",
                    value = Status.NotAvailable,
                    onChange = {},
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                StatusCheckField(
                    label = "13. Push button OFF",
                    value = Status.NotAvailable,
                    onChange = {},
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                StatusCheckField(
                    label = "14. Selector MOA",
                    value = Status.NotAvailable,
                    onChange = {},
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                StatusCheckField(
                    label = "15. Status Indikator",
                    value = Status.NotAvailable,
                    onChange = {},
                    modifier = Modifier.padding(bottom = 16.dp)
                )
            }
        }
    }
}


@Preview
@Composable
fun CheckFormPreview() {
    Form(onNavigateBack = {}, onNext = {})
}
