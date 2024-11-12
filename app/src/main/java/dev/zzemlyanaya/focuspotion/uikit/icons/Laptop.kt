package dev.zzemlyanaya.focuspotion.uikit.icons

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Icon


@Preview
@Composable
private fun LaptopIcon() {
    Icon(AppIcons.Laptop, AppIcons.Laptop.name)
}

private var _Laptop: ImageVector? = null

val AppIcons.Laptop: ImageVector
    get() {
        if (_Laptop != null) {
            return _Laptop!!
        }
        _Laptop = ImageVector.Builder(
            name = "Laptop",
            defaultWidth = 100.dp,
            defaultHeight = 100.dp,
            viewportWidth = 100f,
            viewportHeight = 100f
        ).apply {
            path(
                fill = SolidColor(Color.Black),
                fillAlpha = 1.0f,
                stroke = null,
                strokeAlpha = 1.0f,
                strokeLineWidth = 1.0f,
                strokeLineCap = StrokeCap.Butt,
                strokeLineJoin = StrokeJoin.Miter,
                strokeLineMiter = 1.0f,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(14f, 15.6f)
                verticalLineToRelative(45.3f)
                horizontalLineToRelative(72f)
                verticalLineToRelative(-45.3f)
                curveToRelative(00f, -2.50f, -2.10f, -4.60f, -4.60f, -4.60f)
                horizontalLineToRelative(-62.8f)
                curveToRelative(-2.50f, 00f, -4.60f, 2.10f, -4.60f, 4.60f)
                close()
                moveToRelative(63.3f, 36.6f)
                horizontalLineToRelative(-54.6f)
                verticalLineToRelative(-32.5f)
                horizontalLineToRelative(54.5f)
                verticalLineToRelative(32.5f)
                close()
            }
            path(
                fill = SolidColor(Color.Black),
                fillAlpha = 1.0f,
                stroke = null,
                strokeAlpha = 1.0f,
                strokeLineWidth = 1.0f,
                strokeLineCap = StrokeCap.Butt,
                strokeLineJoin = StrokeJoin.Miter,
                strokeLineMiter = 1.0f,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(86f, 65.4f)
                horizontalLineToRelative(-15.1f)
                lineToRelative(0.6f, 3.3f)
                horizontalLineToRelative(-43f)
                verticalLineToRelative(-3.3f)
                horizontalLineToRelative(-14.5f)
                lineToRelative(-6f, 19.1f)
                curveToRelative(00f, 2.50f, 20f, 4.50f, 4.50f, 4.50f)
                horizontalLineToRelative(75f)
                curveToRelative(2.50f, 00f, 4.50f, -20f, 4.50f, -4.50f)
                close()
                moveToRelative(-27.6f, 18.9f)
                horizontalLineToRelative(-17.3f)
                curveToRelative(-0.90f, 00f, -1.70f, -10f, -1.70f, -2.30f)
                reflectiveCurveToRelative(0.7f, -2.3f, 1.7f, -2.3f)
                horizontalLineToRelative(17.3f)
                curveToRelative(0.90f, 00f, 1.70f, 10f, 1.70f, 2.30f)
                reflectiveCurveToRelative(-0.8f, 2.3f, -1.7f, 2.3f)
                close()
            }
        }.build()
        return _Laptop!!
    }