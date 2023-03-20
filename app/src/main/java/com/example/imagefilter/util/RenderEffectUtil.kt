package com.example.imagefilter.util

import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.graphics.RenderEffect
import android.graphics.Shader

object RenderEffectUtil {

    const val DEFAULT_BLUR = 10f
    const val DEFAULT_SATURATION = 10f
    const val DEFAULT_OFFSET = 20f
    const val DEFAULT_FULLSCREEN_BLUR = 30f

    fun createBlurEffect(
        radiusX: Float,
        radiusY: Float,
        shader: Shader.TileMode = Shader.TileMode.MIRROR,
    ): RenderEffect {
        return RenderEffect.createBlurEffect(radiusX, radiusY, shader)
    }

    fun createSaturationEffect(saturation: Float): RenderEffect {
        return RenderEffect.createColorFilterEffect(
            ColorMatrixColorFilter(
                ColorMatrix().apply {
                    setSaturation(saturation)
                },
            ),
        )
    }

    fun createOffsetEffect(offsetX: Float, offsetY: Float): RenderEffect {
        return RenderEffect.createOffsetEffect(offsetX, offsetY)
    }

    fun createChainEffect(blur: Float, saturation: Float): RenderEffect? {
        return if (blur < 1) {
            null
        } else {
            val blurry = createBlurEffect(blur, blur, Shader.TileMode.MIRROR)
            val saturate = createSaturationEffect(saturation)
            RenderEffect.createChainEffect(blurry, saturate)
        }
    }
}
