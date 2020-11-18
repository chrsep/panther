package com.cakrasuryainti.panther.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.ui.tooling.preview.Preview
import com.cakrasuryainti.panther.db.model.Status
import com.google.android.material.chip.Chip

@Composable
fun StatusCheckField(
    label: String,
    value: Status,
    onChange: (Status) -> Unit,
    modifier: Modifier = Modifier
) {
    val borderStrokeColor = when (value) {
        Status.NotAvailable -> MaterialTheme.colors.onSurface
        Status.NotOk -> Color(255, 109, 0)
        Status.Ok -> Color(0, 200, 83)
    }

    Surface(
        modifier = modifier.fillMaxWidth(),
        border = BorderStroke(1.dp, borderStrokeColor.copy(alpha = 0.4f)),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Text(
                label,
                modifier = Modifier.padding(start = 8.dp, top = 8.dp),
            )
            Row {
                Status.values().forEach { status ->
                    AndroidView(
                        modifier = Modifier.padding(start = 8.dp),
                        viewBlock = { context ->
                            Chip(context).apply {
                                isCheckable = true
                                setOnClickListener {
                                    onChange(status)
                                    isChecked = true
                                }
                            }
                        }
                    ) { chip ->
                        chip.text = when (status) {
                            Status.Ok -> "Ok"
                            Status.NotAvailable -> "N/A"
                            Status.NotOk -> "Not Ok"
                        }
                        chip.isChecked = status == value
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun NotAvailableStatusFieldPreview() {
    Surface {
        var status by remember { mutableStateOf(Status.NotAvailable) }
        StatusCheckField("1. Relay/timer control", status, {
            status = it
        }, modifier = Modifier.padding(16.dp))
    }
}

@Preview
@Composable
fun NotOkStatusFieldPreview() {
    Surface {
        var status by remember { mutableStateOf(Status.NotOk) }
        StatusCheckField("1. Relay/timer control", status, {
            status = it
        }, modifier = Modifier.padding(16.dp))
    }
}

@Preview
@Composable
fun OkStatusFieldPreview() {
    Surface {
        var status by remember { mutableStateOf(Status.Ok) }
        StatusCheckField("1. Relay/timer control", status, {
            status = it
        }, modifier = Modifier.padding(16.dp))
    }
}
