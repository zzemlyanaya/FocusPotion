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
private fun TimerIcon() {
    Icon(AppIcons.Timer, AppIcons.Timer.name)
}

private var _Timer: ImageVector? = null

val AppIcons.Timer: ImageVector
    get() {
        if (_Timer != null) {
            return _Timer!!
        }
        _Timer = ImageVector.Builder(
            name = "Timer",
            defaultWidth = 612.dp,
            defaultHeight = 612.dp,
            viewportWidth = 612f,
            viewportHeight = 612f
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
                        moveTo(432.272f, 68.692f)
                        lineToRelative(-20.554f, 35.567f)
                        lineToRelative(71.221f, 41.109f)
                        lineToRelative(20.555f, -35.568f)
                        curveToRelative(5.6610f, -9.8730f, 2.290f, -22.40f, -7.5450f, -28.1040f)
                        lineToRelative(-35.567f, -20.555f)
                        curveTo(450.5420f, 55.4430f, 437.9770f, 58.8140f, 432.2720f, 68.6920f)
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
                        moveTo(306f, 92.56f)
                        curveToRelative(130f, 00f, 25.6990f, 1.2720f, 38.250f, 3.0650f)
                        verticalLineTo(62.357f)
                        lineToRelative(26.297f, -0.201f)
                        verticalLineTo(19.125f)
                        curveTo(370.5470f, 8.5540f, 361.9930f, 00f, 351.4220f, 00f)
                        horizontalLineToRelative(-90.643f)
                        curveToRelative(-10.5710f, 00f, -19.1250f, 8.5540f, -19.1250f, 19.1250f)
                        verticalLineToRelative(43.031f)
                        lineToRelative(26.096f, 0.201f)
                        verticalLineToRelative(33.268f)
                        curveTo(280.3010f, 93.8320f, 2930f, 92.560f, 3060f, 92.560f)
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
                        moveTo(306f, 114.75f)
                        curveToRelative(
                            -137.3120f,
                            00f,
                            -248.6250f,
                            111.3120f,
                            -248.6250f,
                            248.6250f
                        )
                        reflectiveCurveTo(168.688f, 612f, 306f, 612f)
                        reflectiveCurveToRelative(248.625f, -111.312f, 248.625f, -248.625f)
                        reflectiveCurveTo(443.312f, 114.75f, 306f, 114.75f)
                        close()
                        moveTo(422.185f, 480.229f)
                        lineTo(277.312f, 379.933f)
                        verticalLineTo(238.34f)
                        horizontalLineToRelative(41.808f)
                        verticalLineToRelative(119.689f)
                        lineToRelative(126.86f, 87.827f)
                        lineTo(422.185f, 480.229f)
                        close()
                    }
                }
            }
        }.build()
        return _Timer!!
    }