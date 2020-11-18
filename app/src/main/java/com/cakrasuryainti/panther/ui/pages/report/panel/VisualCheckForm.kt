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
                    label = "2. Relay / time control",
                    value = Status.NotAvailable,
                    onChange = {},
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                StatusCheckField(
                    label = "3. Relay / time control",
                    value = Status.NotAvailable,
                    onChange = {},
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                StatusCheckField(
                    label = "4. Relay / time control",
                    value = Status.NotAvailable,
                    onChange = {},
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                StatusCheckField(
                    label = "5. Relay / time control",
                    value = Status.NotAvailable,
                    onChange = {},
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                StatusCheckField(
                    label = "6. Relay / time control",
                    value = Status.NotAvailable,
                    onChange = {},
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                StatusCheckField(
                    label = "7. Relay / time control",
                    value = Status.NotAvailable,
                    onChange = {},
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                StatusCheckField(
                    label = "8. Relay / time control",
                    value = Status.NotAvailable,
                    onChange = {},
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                StatusCheckField(
                    label = "9. Relay / time control",
                    value = Status.NotAvailable,
                    onChange = {},
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                StatusCheckField(
                    label = "10. Relay / time control",
                    value = Status.NotAvailable,
                    onChange = {},
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                StatusCheckField(
                    label = "11. Relay / time control",
                    value = Status.NotAvailable,
                    onChange = {},
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                StatusCheckField(
                    label = "12. Relay / time control",
                    value = Status.NotAvailable,
                    onChange = {},
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                StatusCheckField(
                    label = "13. Relay / time control",
                    value = Status.NotAvailable,
                    onChange = {},
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                StatusCheckField(
                    label = "14. Relay / time control",
                    value = Status.NotAvailable,
                    onChange = {},
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                StatusCheckField(
                    label = "15. Relay / time control",
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
