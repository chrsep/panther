package com.cakrasuryainti.panther.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import java.lang.NumberFormatException

@Composable
fun FloatField(
    label: String = "",
    value: Float,
    onValueChange: (Float) -> Unit,
    modifier: Modifier = Modifier
) {
    var state by remember { mutableStateOf(if (value == 0f) "" else value.toString()) }
    var isInvalid by remember { mutableStateOf(false) }

    Column(modifier = modifier) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            label = { Text(text = label) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            value = state,
            isErrorValue = isInvalid,
            onValueChange = {
                state = it
                isInvalid = try {
                    onValueChange(it.toFloat())
                    false
                } catch (e: NumberFormatException) {
                    true
                }
            },
        )
        if (isInvalid) Text(
            "Invalid format",
            color = MaterialTheme.colors.error,
            style = MaterialTheme.typography.caption,
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}

@Preview
@Composable
fun FloatFieldPreview() {
    var state by remember { mutableStateOf(0f) }

    Surface {
        FloatField("R - S", onValueChange = { state = it }, value = state)
    }
}