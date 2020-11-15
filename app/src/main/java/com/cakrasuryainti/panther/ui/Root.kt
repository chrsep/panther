package com.cakrasuryainti.panther.ui

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.ui.tooling.preview.Preview
import com.cakrasuryainti.panther.ui.pages.Home
import com.cakrasuryainti.panther.ui.theme.PantherTheme

@Composable
fun Root() {
    PantherTheme {
        // A surface container using the 'background' color from the theme
        Surface(color = MaterialTheme.colors.background) {
            Home()
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PantherTheme {
        Home()
    }
}