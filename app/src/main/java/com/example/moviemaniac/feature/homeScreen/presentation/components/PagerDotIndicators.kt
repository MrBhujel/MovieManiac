package com.example.moviemaniac.feature.homeScreen.presentation.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun PagerDotIndicators(
    pagerState: PagerState,
    modifier: Modifier = Modifier,
    onDotClick: (Int) -> Unit,
    activeColor: Color = Color.Red,
    inactiveColor: Color = Color.Gray.copy(alpha = 0.4f),
    dotSize: Dp = 10.dp,
    spacing: Dp = 6.dp
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {

        repeat(pagerState.pageCount) { index ->

            val isSelected = pagerState.currentPage == index

            val animatedSize by animateDpAsState(
                targetValue = if (isSelected) dotSize * 1.2f else dotSize,
                label = "dot animation"
            )

            IconButton(
                onClick = { onDotClick(index) },
                modifier = Modifier.size(dotSize + spacing)
            ) {
                Box(
                    modifier = Modifier
                        .size(animatedSize)
                        .clip(CircleShape)
                        .background(if (isSelected) activeColor else inactiveColor)
                )
            }
        }
    }

}