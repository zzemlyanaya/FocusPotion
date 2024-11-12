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
private fun LanguageIcon() {
    Icon(AppIcons.Language, AppIcons.Language.name)
}

private var _Language: ImageVector? = null

val AppIcons.Language: ImageVector
    get() {
        if (_Language != null) {
            return _Language!!
        }
        _Language = ImageVector.Builder(
            name = "Language",
            defaultWidth = 512.dp,
            defaultHeight = 512.dp,
            viewportWidth = 512f,
            viewportHeight = 512f
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
                moveTo(371.867188f, 316f)
                horizontalLineToRelative(-21.734376f)
                curveToRelative(2.59770f, 5.69140f, 6.31250f, 11.50f, 10.86720f, 17.28130f)
                curveToRelative(4.55470f, -5.78130f, 8.26950f, -11.58980f, 10.86720f, -17.28130f)
                close()
                moveToRelative(0f, 0f)
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
                moveTo(211f, 452f)
                horizontalLineToRelative(160.003906f)
                lineToRelative(79.996094f, 60f)
                verticalLineToRelative(-60f)
                horizontalLineToRelative(61f)
                verticalLineToRelative(-241f)
                horizontalLineToRelative(-301f)
                close()
                moveToRelative(75f, -166f)
                horizontalLineToRelative(60f)
                verticalLineToRelative(-30f)
                horizontalLineToRelative(30f)
                verticalLineToRelative(30f)
                horizontalLineToRelative(60f)
                verticalLineToRelative(30f)
                horizontalLineToRelative(-32.496094f)
                curveToRelative(-3.59380f, 13.07030f, -11.14450f, 26.53520f, -21.27730f, 39.03910f)
                curveToRelative(14.38280f, 12.1250f, 29.60550f, 20.96090f, 38.77340f, 20.96090f)
                verticalLineToRelative(30f)
                curveToRelative(-17.41020f, 00f, -40.07810f, -12.23440f, -600f, -29.47270f)
                curveToRelative(-19.92190f, 17.23830f, -42.58980f, 29.47270f, -600f, 29.47270f)
                verticalLineToRelative(-30f)
                curveToRelative(9.1680f, 00f, 24.39060f, -8.83590f, 38.77340f, -20.96090f)
                curveToRelative(
                    -10.13280f,
                    -12.50390f,
                    -17.68360f,
                    -25.96880f,
                    -21.27730f,
                    -39.03910f
                )
                horizontalLineToRelative(-32.496094f)
                close()
                moveToRelative(0f, 0f)
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
                moveTo(137.773438f, 136f)
                horizontalLineToRelative(26.453124f)
                lineToRelative(-13.226562f, -26.453125f)
                close()
                moveToRelative(0f, 0f)
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
                moveTo(61f, 301f)
                lineToRelative(79.996094f, -60f)
                horizontalLineToRelative(40.003906f)
                verticalLineToRelative(-60f)
                horizontalLineToRelative(5.726562f)
                lineToRelative(-7.5f, -15f)
                horizontalLineToRelative(-56.453124f)
                lineToRelative(-18.355469f, 36.710938f)
                lineToRelative(-26.835938f, -13.421876f)
                lineToRelative(73.417969f, -146.835937f)
                lineToRelative(69.273438f, 138.546875f)
                horizontalLineToRelative(80.726562f)
                verticalLineToRelative(-181f)
                horizontalLineToRelative(-301f)
                verticalLineToRelative(241f)
                horizontalLineToRelative(61f)
                close()
                moveToRelative(0f, 0f)
            }
        }.build()
        return _Language!!
    }