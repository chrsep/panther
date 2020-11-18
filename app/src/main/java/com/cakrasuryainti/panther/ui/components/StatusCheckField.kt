package com.cakrasuryainti.panther.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.cakrasuryainti.panther.db.model.Status

@Composable
fun StatusCheckField(
    label: String,
    value: Status,
    onChange: (Status) -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        border = BorderStroke(1.dp, MaterialTheme.colors.onSurface.copy(alpha = 0.4f)),
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                label,
                modifier = Modifier.padding(start = 8.dp, top = 8.dp, bottom = 8.dp),
            )
            Row {
                Status.values().forEach { status ->
                    ChoiceChip(
                        when (status) {
                            Status.Ok -> "OK"
                            Status.NotAvailable -> "N/A"
                            Status.NotOk -> "NOK"
                        },
                        onClick = { onChange(status) },
                        modifier = Modifier.padding(start = 8.dp),
                        isActive = status == value
                    )
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
