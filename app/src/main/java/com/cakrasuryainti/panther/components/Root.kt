package com.cakrasuryainti.panther.components

import androidx.compose.foundation.Text
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.ui.tooling.preview.Preview
import com.cakrasuryainti.panther.components.theme.PantherTheme

@Composable
fun Root() {
    PantherTheme {
        // A surface container using the 'background' color from the theme
        Surface(color = MaterialTheme.colors.background) {
            Greeting("Android")
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PantherTheme {
        Greeting("Android")
    }
}