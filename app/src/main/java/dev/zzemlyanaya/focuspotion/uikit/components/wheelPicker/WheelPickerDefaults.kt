package dev.zzemlyanaya.focuspotion.uikit.components.wheelPicker

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer

@Composable
fun WheelPickerFocusVertical(
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.fillMaxSize())
}

@Composable
fun WheelPickerFocusHorizontal(
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.fillMaxSize())
}



@Composable
fun WheelPickerDisplayScope.DefaultWheelPickerDisplay(
    index: Int,
) {
    val focused = index == state.currentIndexSnapshot
    val animateScale by animateFloatAsState(
        targetValue = if (focused) 1.0f else 0.8f,
        label = "Wheel picker item scale",
    )
    Box(
        modifier = Modifier.graphicsLayer {
            this.alpha = if (focused) 1.0f else 0.3f
            this.scaleX = animateScale
            this.scaleY = animateScale
        }
    ) {
        Content(index)
    }
}