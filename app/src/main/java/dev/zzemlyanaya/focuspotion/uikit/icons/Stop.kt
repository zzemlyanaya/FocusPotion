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
private fun StopIcon() {
    Icon(AppIcons.Stop, AppIcons.Stop.name)
}

private var _Stop: ImageVector? = null

val AppIcons.Stop: ImageVector
    get() {
        if (_Stop != null) {
            return _Stop!!
        }
        _Stop = ImageVector.Builder(
            name = "Stop",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 36f,
            viewportHeight = 36f
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
                moveTo(35f, 0f)
                horizontalLineTo(1f)
                curveTo(0.4480f, 00f, 00f, 0.4470f, 00f, 10f)
                verticalLineToRelative(34f)
                curveToRelative(00f, 0.5530f, 0.4480f, 10f, 10f, 10f)
                horizontalLineToRelative(34f)
                curveToRelative(0.5520f, 00f, 10f, -0.4470f, 10f, -10f)
                verticalLineTo(1f)
                curveTo(360f, 0.4470f, 35.5520f, 00f, 350f, 00f)
                close()
            }
        }.build()
        return _Stop!!
    }