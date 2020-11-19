package com.cakrasuryainti.panther.ui.pages

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ContextAmbient
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import androidx.ui.tooling.preview.Preview

@Composable
fun Home(navController: NavHostController) {
    val context = ContextAmbient.current
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Surya Cakra Inti") },
            )
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
            OutlinedButton(
                onClick = { },
                modifier = Modifier.padding(8.dp)
            ) { Text(text = "Help") }
        }
    }
}

@Composable
@Preview
fun HomePreview() {
    val navController = rememberNavController()
    Home(navController)
}