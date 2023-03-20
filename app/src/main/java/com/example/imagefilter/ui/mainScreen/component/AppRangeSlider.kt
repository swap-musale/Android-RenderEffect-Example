package com.example.imagefilter.ui.mainScreen.component

import androidx.compose.material.Slider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.NonRestartableComposable
import androidx.compose.ui.tooling.preview.Preview

@NonRestartableComposable
@Composable
fun AppRangeSlider(
    enabled: Boolean,
    sliderValue: Float,
    onValueChange: (Float) -> Unit,
) {
    Slider(
        enabled = enabled,
        value = sliderValue,
        onValueChange = onValueChange,
        steps = 10,
        valueRange = 0f..100f,
    )
}

@Preview
@Composable
private fun AppRangeSliderPreview() {
    AppRangeSlider(
        enabled = true,
        sliderValue = 10f,
        onValueChange = {},
    )
}
