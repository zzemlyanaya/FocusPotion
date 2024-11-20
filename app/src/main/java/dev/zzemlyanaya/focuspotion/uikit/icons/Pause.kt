package dev.zzemlyanaya.focuspotion.uikit.icons

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.vector.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Icon

@Preview
@Composable
private fun PauseIcon() {
    Icon(AppIcons.Pause, AppIcons.Pause.name)
}

private var _Pause: ImageVector? = null

val AppIcons.Pause: ImageVector
    get() {
        if (_Pause != null) {
            return _Pause!!
        }
        _Pause = ImageVector.Builder(
            name = "Pause",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 357f,
            viewportHeight = 357f
        ).apply {
            group {
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
                        moveTo(25.5f, 357f)
                        horizontalLineToRelative(102f)
                        verticalLineTo(0f)
                        horizontalLineToRelative(-102f)
                        verticalLineTo(357f)
                        close()
                        moveTo(229.5f, 0f)
                        verticalLineToRelative(357f)
                        horizontalLineToRelative(102f)
                        verticalLineTo(0f)
                        horizontalLineTo(229.5f)
                        close()
                    }
                }
            }
        }.build()
        return _Pause!!
    }