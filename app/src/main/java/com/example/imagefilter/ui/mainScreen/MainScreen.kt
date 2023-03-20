package com.example.imagefilter.ui.mainScreen

import android.graphics.RenderEffect
import android.graphics.Shader
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asComposeRenderEffect
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.imagefilter.R
import com.example.imagefilter.ui.mainScreen.component.AppRangeSlider
import com.example.imagefilter.ui.mainScreen.component.AppToolBar
import com.example.imagefilter.ui.mainScreen.component.LabelledCheckbox
import com.example.imagefilter.ui.theme.spacing
import com.example.imagefilter.util.RenderEffectUtil

@Composable
fun MainScreen() {
    Scaffold(topBar = { AppToolBar() }) { paddingValues ->

        var blurCheckBoxState by remember { mutableStateOf(value = false) }
        var saturationCheckBoxState by remember { mutableStateOf(value = false) }
        var offsetCheckBoxState by remember { mutableStateOf(value = false) }
        var chainEffectCheckBoxState by remember { mutableStateOf(value = false) }
        var fullScreenCheckBoxState by remember { mutableStateOf(value = false) }

        var blurSliderValue by remember { mutableStateOf(value = RenderEffectUtil.DEFAULT_BLUR) }
        var saturationSliderValue by remember { mutableStateOf(value = RenderEffectUtil.DEFAULT_SATURATION) }
        var offsetSliderValue by remember { mutableStateOf(value = RenderEffectUtil.DEFAULT_OFFSET) }
        var chainEffectSliderValue by remember { mutableStateOf(value = 0f) }

        var fullScreenRenderEffect by remember { mutableStateOf<RenderEffect?>(value = null) }
        var imageRenderEffect by remember { mutableStateOf<RenderEffect?>(value = null) }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingValues = paddingValues)
                .graphicsLayer {
                    renderEffect = fullScreenRenderEffect?.asComposeRenderEffect()
                    clip = true
                },
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = MaterialTheme.spacing.extraLarge),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                LabelledCheckbox(
                    checkBoxLabel = stringResource(id = R.string.str_blur),
                    enabled = !saturationCheckBoxState && !offsetCheckBoxState && !chainEffectCheckBoxState,
                    checked = blurCheckBoxState,
                    onCheckedChange = {
                        blurCheckBoxState = it
                        imageRenderEffect = if (blurCheckBoxState) {
                            RenderEffectUtil.createBlurEffect(
                                radiusX = RenderEffectUtil.DEFAULT_BLUR,
                                radiusY = RenderEffectUtil.DEFAULT_BLUR,
                                shader = Shader.TileMode.DECAL,
                            )
                        } else {
                            null
                        }
                    },
                )
                LabelledCheckbox(
                    checkBoxLabel = stringResource(id = R.string.str_saturation),
                    enabled = !blurCheckBoxState && !offsetCheckBoxState && !chainEffectCheckBoxState,
                    checked = saturationCheckBoxState,
                    onCheckedChange = {
                        saturationCheckBoxState = it
                        imageRenderEffect = if (saturationCheckBoxState) {
                            RenderEffectUtil.createSaturationEffect(saturation = RenderEffectUtil.DEFAULT_SATURATION)
                        } else {
                            null
                        }
                    },
                )
                LabelledCheckbox(
                    checkBoxLabel = stringResource(id = R.string.str_offset),
                    enabled = !blurCheckBoxState && !saturationCheckBoxState && !chainEffectCheckBoxState,
                    checked = offsetCheckBoxState,
                    onCheckedChange = {
                        offsetCheckBoxState = it
                        imageRenderEffect = if (offsetCheckBoxState) {
                            RenderEffectUtil.createOffsetEffect(
                                offsetX = RenderEffectUtil.DEFAULT_OFFSET,
                                offsetY = RenderEffectUtil.DEFAULT_OFFSET,
                            )
                        } else {
                            null
                        }
                    },
                )
            }

            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(height = MaterialTheme.spacing.dp_300)
                    .padding(horizontal = MaterialTheme.spacing.extraLarge)
                    .background(color = Color.Gray)
                    .graphicsLayer {
                        renderEffect = imageRenderEffect?.asComposeRenderEffect()
                        clip = true
                    },
                painter = painterResource(id = R.drawable.ic_dog),
                contentScale = ContentScale.Inside,
                contentDescription = null,
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = MaterialTheme.spacing.extraLarge),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                LabelledCheckbox(
                    checkBoxLabel = stringResource(id = R.string.str_chain_effect),
                    enabled = !blurCheckBoxState && !saturationCheckBoxState && !offsetCheckBoxState,
                    checked = chainEffectCheckBoxState,
                    onCheckedChange = {
                        chainEffectCheckBoxState = it
                        imageRenderEffect = if (chainEffectCheckBoxState) {
                            RenderEffectUtil.createChainEffect(
                                blur = RenderEffectUtil.DEFAULT_BLUR,
                                saturation = RenderEffectUtil.DEFAULT_SATURATION,
                            )
                        } else {
                            null
                        }
                    },
                )
                LabelledCheckbox(
                    checkBoxLabel = stringResource(R.string.str_fullscreen_blur),
                    checked = fullScreenCheckBoxState,
                    onCheckedChange = {
                        fullScreenCheckBoxState = it
                        fullScreenRenderEffect = if (fullScreenCheckBoxState) {
                            RenderEffectUtil.createBlurEffect(
                                radiusX = RenderEffectUtil.DEFAULT_FULLSCREEN_BLUR,
                                radiusY = RenderEffectUtil.DEFAULT_FULLSCREEN_BLUR,
                                shader = Shader.TileMode.DECAL,
                            )
                        } else {
                            null
                        }
                    },
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = MaterialTheme.spacing.extraLarge),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = stringResource(R.string.str_blur),
                    modifier = Modifier.padding(end = MaterialTheme.spacing.medium),
                )
                AppRangeSlider(
                    sliderValue = blurSliderValue,
                    enabled = blurCheckBoxState,
                    onValueChange = {
                        blurSliderValue = it
                        if (it > 0f) {
                            imageRenderEffect = RenderEffectUtil.createBlurEffect(
                                radiusX = it,
                                radiusY = it,
                                shader = Shader.TileMode.MIRROR,
                            )
                        }
                    },
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = MaterialTheme.spacing.extraLarge),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = stringResource(R.string.str_saturation),
                    modifier = Modifier.padding(end = MaterialTheme.spacing.medium),
                )
                AppRangeSlider(
                    sliderValue = saturationSliderValue,
                    enabled = saturationCheckBoxState,
                    onValueChange = {
                        saturationSliderValue = it
                        if (it > 0f) {
                            imageRenderEffect =
                                RenderEffectUtil.createSaturationEffect(saturation = it)
                        }
                    },
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = MaterialTheme.spacing.extraLarge),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = stringResource(R.string.str_offset_effect),
                    modifier = Modifier.padding(end = MaterialTheme.spacing.medium),
                )
                AppRangeSlider(
                    sliderValue = offsetSliderValue,
                    enabled = offsetCheckBoxState,
                    onValueChange = {
                        offsetSliderValue = it
                        if (it > 0) {
                            imageRenderEffect =
                                RenderEffectUtil.createOffsetEffect(offsetX = it, offsetY = it)
                        }
                    },
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = MaterialTheme.spacing.extraLarge),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = stringResource(R.string.str_chain_effect),
                    modifier = Modifier.padding(end = MaterialTheme.spacing.medium),
                )
                AppRangeSlider(
                    sliderValue = chainEffectSliderValue,
                    enabled = chainEffectCheckBoxState,
                    onValueChange = {
                        chainEffectSliderValue = it
                        if (it > 0) {
                            imageRenderEffect = RenderEffectUtil.createChainEffect(
                                blur = it,
                                saturation = it,
                            )
                        }
                    },
                )
            }
        }
    }
}
