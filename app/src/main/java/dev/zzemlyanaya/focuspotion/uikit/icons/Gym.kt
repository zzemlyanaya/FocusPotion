package dev.zzemlyanaya.focuspotion.uikit.icons

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.group
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Icon


@Preview
@Composable
private fun GymIcon() {
    Icon(AppIcons.Gym, AppIcons.Gym.name)
}

private var _Gym: ImageVector? = null

val AppIcons.Gym: ImageVector
    get() {
        if (_Gym != null) {
            return _Gym!!
        }
        _Gym = ImageVector.Builder(
            name = "Gym",
            defaultWidth = 512.dp,
            defaultHeight = 512.dp,
            viewportWidth = 512f,
            viewportHeight = 512f
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
                        moveTo(30.183f, 151.899f)
                        curveTo(13.540f, 151.8990f, 00f, 165.4390f, 00f, 182.0820f)
                        verticalLineToRelative(147.835f)
                        curveToRelative(00f, 16.6430f, 13.540f, 30.1830f, 30.1830f, 30.1830f)
                        horizontalLineToRelative(20.253f)
                        verticalLineTo(151.899f)
                        horizontalLineTo(30.183f)
                        close()
                    }
                }
            }
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
                        moveTo(417.164f, 120.41f)
                        horizontalLineToRelative(-65.84f)
                        curveToRelative(-16.6430f, 00f, -30.1830f, 13.540f, -30.1830f, 30.1830f)
                        verticalLineToRelative(87.293f)
                        horizontalLineTo(190.858f)
                        verticalLineToRelative(-87.293f)
                        curveToRelative(00f, -16.6430f, -13.540f, -30.1830f, -30.1820f, -30.1830f)
                        horizontalLineToRelative(-65.84f)
                        curveToRelative(-16.6430f, 00f, -30.1830f, 13.540f, -30.1830f, 30.1830f)
                        verticalLineToRelative(210.814f)
                        curveToRelative(00f, 16.6430f, 13.540f, 30.1830f, 30.1830f, 30.1830f)
                        horizontalLineToRelative(65.84f)
                        curveToRelative(16.6430f, 00f, 30.1820f, -13.540f, 30.1820f, -30.1830f)
                        verticalLineToRelative(-87.301f)
                        horizontalLineToRelative(130.283f)
                        verticalLineToRelative(87.301f)
                        curveToRelative(00f, 16.6430f, 13.540f, 30.1830f, 30.1830f, 30.1830f)
                        horizontalLineToRelative(65.84f)
                        curveToRelative(16.6430f, 00f, 30.1830f, -13.540f, 30.1830f, -30.1830f)
                        verticalLineTo(150.593f)
                        curveTo(447.3470f, 133.950f, 433.8080f, 120.410f, 417.1640f, 120.410f)
                        close()
                    }
                }
            }
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
                        moveTo(481.817f, 151.899f)
                        horizontalLineToRelative(-20.253f)
                        verticalLineToRelative(208.202f)
                        horizontalLineToRelative(20.253f)
                        curveToRelative(16.6430f, 00f, 30.1830f, -13.540f, 30.1830f, -30.1830f)
                        verticalLineTo(182.082f)
                        curveTo(5120f, 165.4390f, 498.460f, 151.8990f, 481.8170f, 151.8990f)
                        close()
                    }
                }
            }
        }.build()
        return _Gym!!
    }