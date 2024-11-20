package dev.zzemlyanaya.focuspotion.uikit.icons

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.vector.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Icon


@Preview
@Composable
private fun PlayIcon() {
    Icon(AppIcons.Play, AppIcons.Play.name)
}

private var _Play: ImageVector? = null

val AppIcons.Play: ImageVector
    get() {
        if (_Play != null) {
            return _Play!!
        }
        _Play = ImageVector.Builder(
            name = "Play",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 443.307f,
            viewportHeight = 443.306f
        ).apply {
            group {
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
                    moveTo(415.934f, 212.799f)
                    lineTo(36.788f, 2.097f)
                    curveTo(32.4110f, -0.3770f, 28.650f, -0.6610f, 25.510f, 1.2420f)
                    curveToRelative(-3.140f, 1.9020f, -4.7080f, 5.3280f, -4.7080f, 10.2760f)
                    verticalLineTo(431.78f)
                    curveToRelative(00f, 4.9520f, 1.5690f, 8.3810f, 4.7080f, 10.2840f)
                    curveToRelative(3.140f, 1.9020f, 6.9010f, 1.6220f, 11.2780f, -0.8550f)
                    lineToRelative(379.146f, -210.703f)
                    curveToRelative(4.3810f, -2.4780f, 6.5710f, -5.4340f, 6.5710f, -8.8560f)
                    curveTo(422.5050f, 218.2240f, 420.3140f, 215.2740f, 415.9340f, 212.7990f)
                    close()
                }
            }
        }.build()
        return _Play!!
    }