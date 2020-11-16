package com.cakrasuryainti.panther.ui.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate

@Composable
fun Home(navController: NavHostController) {
    Column {
        Button(onClick = { navController.navigate("create") }, Modifier.padding(16.dp)) {
            Text(text = "Create Report")
        }
        Button(onClick = { navController.navigate("saved") }, Modifier.padding(16.dp)) {
            Text(text = "Saved Report")
        }
        Button(onClick = { navController.navigate("help") }, Modifier.padding(16.dp)) {
            Text(text = "Saved Report")
        }
    }
}