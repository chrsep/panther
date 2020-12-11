package com.cakrasuryainti.panther.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Position
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import com.cakrasuryainti.panther.db.model.Status

@Composable
fun StatusCheckField(
    label: String,
    statusValue: Status,
    onStatusChange: (Status) -> Unit,
    keteranganValue: String,
    onKeteranganChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var openDropdown by remember { mutableStateOf(false) }

    Surface(modifier = modifier.fillMaxWidth()) {
        Column(modifier = Modifier.fillMaxWidth()) {
            ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
                val (text, row) = createRefs()

                Text(
                    label,
                    modifier = Modifier.constrainAs(text) {
                        start.linkTo(parent.start)
                        end.linkTo(row.start)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        width = Dimension.fillToConstraints
                    },
                    style = MaterialTheme.typography.body1
                )

                Card(
                    modifier = Modifier.constrainAs(row) {
                        end.linkTo(parent.end)
                    }.clickable(onClick = { openDropdown = true }),
                    border = BorderStroke(1.dp, Color.LightGray)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = when (statusValue) {
                                Status.Ok -> "OK"
                                Status.NotAvailable -> "N/A"
                                Status.NotOk -> "NOK"
                            }, modifier = Modifier.padding(start = 16.dp)
                        )
                        DropdownMenu(
                            dropdownOffset = Position((-50).dp, 0.dp),
                            toggle = {
                                IconButton(onClick = { openDropdown = true }) {
                                    Icon(Icons.Rounded.ArrowDropDown)
                                }
                            },
                            expanded = openDropdown,
                            onDismissRequest = { openDropdown = false }
                        ) {
                            DropdownMenuItem(onClick = {
                                onStatusChange(Status.Ok)
                                openDropdown = false
                            }) {
                                Text("OK")
                            }

                            DropdownMenuItem(onClick = {
                                onStatusChange(Status.NotOk)
                                openDropdown = false
                            }) {
                                Text("NOK")
                            }

                            DropdownMenuItem(onClick = {
                                onStatusChange(Status.NotAvailable)
                                openDropdown = false
                            }) {
                                Text("N/A")
                            }
                        }
                    }
//                Status.values().forEach { status ->
//                    ChoiceChip(
//                        when (status) {
//                            Status.Ok -> "OK"
//                            Status.NotAvailable -> "N/A"
//                            Status.NotOk -> "NOK"
//                        },
//                        onClick = { onChange(status) },
//                        modifier = Modifier.padding(end = 8.dp),
//                        isActive = status == value
//                    )
                }
            }
            TextField(
                label = { Text("Keterangan") },
                onValueChange = { onKeteranganChange(it) },
                value = keteranganValue,
                modifier = Modifier.fillMaxWidth().padding(top = 8.dp)
            )
            Divider(modifier = Modifier.padding(top = 16.dp))
        }
    }
}

@Preview
@Composable
fun NotAvailableStatusFieldPreview() {
    Surface {
        var status by remember { mutableStateOf(Status.NotAvailable) }
        StatusCheckField("1. Relay/timer control control control", status, {
            status = it
        }, "", {}, modifier = Modifier.padding(16.dp))
    }
}

@Preview
@Composable
fun NotOkStatusFieldPreview() {
    Surface {
        var status by remember { mutableStateOf(Status.NotOk) }
        StatusCheckField("1. Relay/timer control", status, {
            status = it
        }, "", {}, modifier = Modifier.padding(16.dp))
    }
}

@Preview
@Composable
fun OkStatusFieldPreview() {
    Surface {
        var status by remember { mutableStateOf(Status.Ok) }
        StatusCheckField("1. Relay/timer control", status, {
            status = it
        }, "", {}, modifier = Modifier.padding(16.dp))
    }
}
