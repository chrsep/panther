package com.cakrasuryainti.panther.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.InteractionState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ChoiceChip(
    label: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    enabled: Boolean = true,
    isActive: Boolean = false,
    interactionState: InteractionState = remember { InteractionState() },
    activeColor: Color = Color.Black
) {
    val backgroundColor = if (isActive) activeColor.copy(alpha = 0.2f)
    else MaterialTheme.colors.surface

    val borderColor = if (isActive) activeColor.copy(alpha = 0.8f)
    else MaterialTheme.colors.onSurface.copy(alpha = 0.2f)

    val textColor = if (isActive) activeColor
    else MaterialTheme.colors.onSurface

    Surface(
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(1.dp, borderColor),
        color = backgroundColor,
        modifier = modifier.clickable(
            onClick = onClick,
            enabled = enabled,
            interactionState = interactionState,
            indication = null
        )
    ) {
        Text(
            label,
            modifier = Modifier.padding(start = 12.dp, end = 12.dp, top = 8.dp, bottom = 8.dp),
            style = MaterialTheme.typography.body2,
            color = textColor
        )
    }
}

@Preview
@Composable
private fun InactiveChipPreview() {
    Surface {
        ChoiceChip("Not Available", onClick = {})
    }
}

@Preview
@Composable
private fun ActiveChipPreview() {
    Surface {
        ChoiceChip("Not Available", isActive = true, onClick = {})
    }
}