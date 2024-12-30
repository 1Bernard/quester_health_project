package com.example.questerhealth.features.onboard.presentation.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun IndicatorUI(
    pageSize: Int,
    currentPage: Int,
    selectedColor: Color = MaterialTheme.colorScheme.primary,
    unselectedColor: Color = MaterialTheme.colorScheme.surfaceVariant,
    selectedWidth: Dp = 20.dp,
    unselectedWidth: Dp = 10.dp,
    height: Dp = 8.dp,
    spacing: Dp = 8.dp
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(spacing, Alignment.CenterHorizontally)
    ) {
        repeat(pageSize) { page ->
            val isSelected = page == currentPage

            // Animate width
            val width by animateDpAsState(targetValue = if (isSelected) selectedWidth else unselectedWidth)
            // Animate color
            val color by animateColorAsState(targetValue = if (isSelected) selectedColor else unselectedColor)

            Canvas(
                modifier = Modifier
                    .height(height)
                    .width(width)
            ) {
                drawRoundRect(
                    color = color,
                    size = Size(width.toPx(), height.toPx()),
                    cornerRadius = CornerRadius(height.toPx() / 2, height.toPx() / 2) // Rounded edges
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun IndicatorUIPreview1() {
    IndicatorUI(pageSize = 3, currentPage = 0)
}

@Preview(showBackground = true)
@Composable
fun IndicatorUIPreview2() {
    IndicatorUI(pageSize = 3, currentPage = 1)
}

@Preview(showBackground = true)
@Composable
fun IndicatorUIPreview3() {
    IndicatorUI(pageSize = 3, currentPage = 2)
}