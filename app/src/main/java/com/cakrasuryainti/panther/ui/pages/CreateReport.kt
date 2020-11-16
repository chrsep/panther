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
fun CreateReport(navController: NavHostController) {
    Column {
        Button(onClick = { navController.navigate("create/panel") }, Modifier.padding(16.dp)) {
            Text(text = "Panel Maintenance ")
        }
        Button(onClick = { navController.navigate("create/genset") }, Modifier.padding(16.dp)) {
            Text(text = "Genset Maintenance")
        }
    }
}