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
private fun HeartIcon() {
    Icon(AppIcons.Heart, AppIcons.Heart.name)
}

private var _Heart: ImageVector? = null

val AppIcons.Heart: ImageVector
    get() {
        if (_Heart != null) {
            return _Heart!!
        }
        _Heart = ImageVector.Builder(
            name = "Heart",
            defaultWidth = 511.626.dp,
            defaultHeight = 511.627.dp,
            viewportWidth = 511.626f,
            viewportHeight = 511.627f
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
                    moveTo(475.366f, 71.951f)
                    curveToRelative(-24.1750f, -23.6060f, -57.5750f, -35.4040f, -100.2150f, -35.4040f)
                    curveToRelative(-11.80f, 00f, -23.8430f, 2.0460f, -36.1170f, 6.1360f)
                    curveToRelative(-12.2790f, 4.0930f, -23.7020f, 9.6150f, -34.2560f, 16.5620f)
                    curveToRelative(-10.5680f, 6.9450f, -19.650f, 13.4670f, -27.2690f, 19.5560f)
                    curveToRelative(-7.610f, 6.0910f, -14.8450f, 12.5640f, -21.6960f, 19.4140f)
                    curveToRelative(-6.8540f, -6.850f, -14.0870f, -13.3230f, -21.6980f, -19.4140f)
                    curveToRelative(-7.6160f, -6.0890f, -16.7020f, -12.6070f, -27.2680f, -19.5560f)
                    curveToRelative(-10.5640f, -6.950f, -21.9850f, -12.4680f, -34.2610f, -16.5620f)
                    curveToRelative(-12.2750f, -4.0890f, -24.3160f, -6.1360f, -36.1160f, -6.1360f)
                    curveToRelative(-42.6370f, 00f, -76.0390f, 11.8010f, -100.2110f, 35.4040f)
                    curveTo(12.0870f, 95.5520f, 00f, 128.2880f, 00f, 170.1620f)
                    curveToRelative(00f, 12.7530f, 2.240f, 25.8890f, 6.7110f, 39.3980f)
                    curveToRelative(4.4710f, 13.5140f, 9.5660f, 25.0310f, 15.2750f, 34.5460f)
                    curveToRelative(5.7080f, 9.5140f, 12.1810f, 18.7960f, 19.4140f, 27.8370f)
                    curveToRelative(7.2330f, 9.0420f, 12.5190f, 15.270f, 15.8460f, 18.6990f)
                    curveToRelative(3.330f, 3.4220f, 5.9480f, 5.8990f, 7.8510f, 7.4190f)
                    lineTo(243.25f, 469.937f)
                    curveToRelative(3.4270f, 3.4290f, 7.6140f, 5.1440f, 12.5620f, 5.1440f)
                    reflectiveCurveToRelative(9.138f, -1.715f, 12.563f, -5.137f)
                    lineToRelative(177.87f, -171.307f)
                    curveToRelative(43.5880f, -43.5830f, 65.380f, -86.410f, 65.380f, -128.4750f)
                    curveTo(511.6260f, 128.2880f, 499.5370f, 95.5520f, 475.3660f, 71.9510f)
                    close()
                }
            }
        }.build()
        return _Heart!!
    }