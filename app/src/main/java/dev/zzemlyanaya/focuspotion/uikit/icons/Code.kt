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
private fun CodeIcon() {
    Icon(AppIcons.Code, AppIcons.Code.name)
}

private var _Code: ImageVector? = null

val AppIcons.Code: ImageVector
    get() {
        if (_Code != null) {
            return _Code!!
        }
        _Code = ImageVector.Builder(
            name = "Code",
            defaultWidth = 522.468.dp,
            defaultHeight = 522.469.dp,
            viewportWidth = 522.468f,
            viewportHeight = 522.469f
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
                        moveTo(325.762f, 70.513f)
                        lineToRelative(-17.706f, -4.854f)
                        curveToRelative(-2.2790f, -0.760f, -4.5240f, -0.5210f, -6.7070f, 0.7150f)
                        curveToRelative(-2.190f, 1.2370f, -3.6690f, 3.0940f, -4.4290f, 5.5680f)
                        lineTo(190.426f, 440.53f)
                        curveToRelative(-0.760f, 2.4750f, -0.5220f, 4.8090f, 0.7150f, 6.9950f)
                        curveToRelative(1.2370f, 2.190f, 3.090f, 3.6650f, 5.5680f, 4.4250f)
                        lineToRelative(17.701f, 4.856f)
                        curveToRelative(2.2840f, 0.7660f, 4.5210f, 0.5260f, 6.710f, -0.7120f)
                        curveToRelative(2.190f, -1.2430f, 3.6660f, -3.0940f, 4.4250f, -5.5640f)
                        lineTo(332.042f, 81.936f)
                        curveToRelative(0.7590f, -2.4740f, 0.5230f, -4.8080f, -0.7160f, -6.9990f)
                        curveTo(330.0880f, 72.7470f, 328.2370f, 71.2720f, 325.7620f, 70.5130f)
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
                        moveTo(166.167f, 142.465f)
                        curveToRelative(00f, -2.4740f, -0.9530f, -4.6650f, -2.8560f, -6.5670f)
                        lineToRelative(-14.277f, -14.276f)
                        curveToRelative(-1.9030f, -1.9030f, -4.0930f, -2.8570f, -6.5670f, -2.8570f)
                        reflectiveCurveToRelative(-4.665f, 0.955f, -6.567f, 2.857f)
                        lineTo(2.856f, 254.666f)
                        curveTo(0.950f, 256.5690f, 00f, 258.7590f, 00f, 261.2330f)
                        curveToRelative(00f, 2.4740f, 0.9530f, 4.6640f, 2.8560f, 6.5660f)
                        lineToRelative(133.043f, 133.044f)
                        curveToRelative(1.9020f, 1.9060f, 4.0890f, 2.8540f, 6.5670f, 2.8540f)
                        reflectiveCurveToRelative(4.665f, -0.951f, 6.567f, -2.854f)
                        lineToRelative(14.277f, -14.268f)
                        curveToRelative(1.9030f, -1.9020f, 2.8560f, -4.0930f, 2.8560f, -6.570f)
                        curveToRelative(00f, -2.4710f, -0.9530f, -4.6610f, -2.8560f, -6.5630f)
                        lineTo(51.107f, 261.233f)
                        lineToRelative(112.204f, -112.201f)
                        curveTo(165.2170f, 147.130f, 166.1670f, 144.9390f, 166.1670f, 142.4650f)
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
                        moveTo(519.614f, 254.663f)
                        lineTo(386.567f, 121.619f)
                        curveToRelative(-1.9020f, -1.9020f, -4.0930f, -2.8570f, -6.5630f, -2.8570f)
                        curveToRelative(-2.4780f, 00f, -4.6610f, 0.9550f, -6.570f, 2.8570f)
                        lineToRelative(-14.271f, 14.275f)
                        curveToRelative(-1.9020f, 1.9030f, -2.8510f, 4.090f, -2.8510f, 6.5670f)
                        reflectiveCurveToRelative(0.948f, 4.665f, 2.851f, 6.567f)
                        lineToRelative(112.206f, 112.204f)
                        lineTo(359.163f, 373.442f)
                        curveToRelative(-1.9020f, 1.9020f, -2.8510f, 4.0930f, -2.8510f, 6.5630f)
                        curveToRelative(00f, 2.4780f, 0.9480f, 4.6680f, 2.8510f, 6.570f)
                        lineToRelative(14.271f, 14.268f)
                        curveToRelative(1.9090f, 1.9060f, 4.0930f, 2.8540f, 6.570f, 2.8540f)
                        curveToRelative(2.4710f, 00f, 4.6610f, -0.9510f, 6.5630f, -2.8540f)
                        lineTo(519.614f, 267.8f)
                        curveToRelative(1.9030f, -1.9020f, 2.8540f, -4.0960f, 2.8540f, -6.570f)
                        curveTo(522.4680f, 258.7550f, 521.5170f, 256.5650f, 519.6140f, 254.6630f)
                        close()
                    }
                }
            }
        }.build()
        return _Code!!
    }