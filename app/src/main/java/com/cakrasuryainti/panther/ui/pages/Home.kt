package com.cakrasuryainti.panther.ui.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.tooling.preview.Preview
import com.cakrasuryainti.panther.R

@ExperimentalMaterialApi
@Composable
fun HomeContainer(navController: NavHostController) {
    Home(navController)
}

@ExperimentalMaterialApi
@Composable
fun Home(navController: NavHostController) {
    Scaffold {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth().fillMaxHeight()
        ) {
            Image(
                imageResource(id = R.drawable.background),
                modifier = Modifier.fillMaxWidth()
            )
            Surface(
                modifier = Modifier.offset(y = (-70).dp),
                shape = CircleShape
            ) {
                Image(
                    imageResource(id = R.drawable.csi_logo_full),
                    modifier = Modifier.height(140.dp).padding(8.dp)
                )
            }
            Button(
                onClick = { navController.navigate("create") },
                Modifier.padding(bottom = 8.dp)
            ) {
                Text(text = "Create Report")
            }
            OutlinedButton(onClick = { navController.navigate("saved") }, Modifier.padding(8.dp)) {
                Text(text = "Saved Report")
            }
//            OutlinedButton(onClick = { navController.navigate("help") }, Modifier.padding(8.dp)) {
//                Text(text = "Help")
//            }
        }
    }
}


@ExperimentalMaterialApi
@Composable
@Preview
fun HomePreview() {
    val navController = rememberNavController()
    Home(navController)
}