package com.cakrasuryainti.panther.ui.pages

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate

@Composable
fun SavedReport(navController: NavHostController) {
    var tabIndex by remember { mutableStateOf(0) }

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                         Icon(Icons.Rounded.ArrowBack, "")
                    }
                },
                title = { Text(text = "Saved Reports") },
                elevation = 0.dp,
            )
        },
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth().fillMaxHeight()
        ) {
            Button(
                onClick = { navController.navigate("saved/panel") },
                Modifier.padding(bottom = 8.dp)
            ) {
                Text(text = "LV Panel")
            }
            Button(onClick = { navController.navigate("saved/generator") }, Modifier.padding(8.dp)) {
                Text(text = "Generator")
            }
        }
    }
}
