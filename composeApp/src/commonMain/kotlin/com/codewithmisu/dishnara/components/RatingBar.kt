package com.codewithmisu.dishnara.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.StarHalf
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun CustomRatingBar(
    rating: Double,
    maxRating: Int = 5,
    modifier: Modifier = Modifier,
    activeColor: Color = Color.Magenta,
    inactiveColor: Color = Color.Gray
) {
    Row {
        for (i in 1..maxRating) {
            val icon = when {
                rating >= i -> Icons.Filled.Star
                rating >= i - 0.5 -> Icons.AutoMirrored.Filled.StarHalf
                else -> Icons.Filled.StarBorder
            }

            val tint = if (rating >= i - 0.5) activeColor else inactiveColor

            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = tint,
                modifier = modifier
            )
        }
    }
}
