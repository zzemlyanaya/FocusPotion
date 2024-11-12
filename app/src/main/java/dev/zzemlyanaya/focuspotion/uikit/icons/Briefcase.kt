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
private fun BriefcaseIcon() {
    Icon(AppIcons.Briefcase, AppIcons.Briefcase.name)
}

private var _Briefcase: ImageVector? = null

val AppIcons.Briefcase: ImageVector
    get() {
        if (_Briefcase != null) {
            return _Briefcase!!
        }
        _Briefcase = ImageVector.Builder(
            name = "Briefcase",
            defaultWidth = 64.dp,
            defaultHeight = 64.dp,
            viewportWidth = 64f,
            viewportHeight = 64f
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
                    moveTo(39f, 38f)
                    verticalLineToRelative(1f)
                    arcToRelative(3f, 3f, 0f, isMoreThanHalf = false, isPositiveArc = true, -3f, 3f)
                    horizontalLineToRelative(-8f)
                    arcToRelative(
                        3f,
                        3f,
                        0f,
                        isMoreThanHalf = false,
                        isPositiveArc = true,
                        -3f,
                        -3f
                    )
                    verticalLineToRelative(-1f)
                    horizontalLineToRelative(-13f)
                    arcToRelative(
                        10.96f,
                        10.96f,
                        0f,
                        isMoreThanHalf = false,
                        isPositiveArc = true,
                        -8f,
                        -3.474f
                    )
                    verticalLineToRelative(17.474f)
                    arcToRelative(
                        5.006f,
                        5.006f,
                        0f,
                        isMoreThanHalf = false,
                        isPositiveArc = false,
                        5f,
                        5f
                    )
                    horizontalLineToRelative(46f)
                    arcToRelative(
                        5.006f,
                        5.006f,
                        0f,
                        isMoreThanHalf = false,
                        isPositiveArc = false,
                        5f,
                        -5f
                    )
                    verticalLineToRelative(-17.474f)
                    arcToRelative(
                        10.96f,
                        10.96f,
                        0f,
                        isMoreThanHalf = false,
                        isPositiveArc = true,
                        -8f,
                        3.474f
                    )
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
                    moveTo(55f, 17f)
                    horizontalLineToRelative(-46f)
                    arcToRelative(
                        5.006f,
                        5.006f,
                        0f,
                        isMoreThanHalf = false,
                        isPositiveArc = false,
                        -5f,
                        5f
                    )
                    verticalLineToRelative(9.1f)
                    arcToRelative(
                        9f,
                        9f,
                        0f,
                        isMoreThanHalf = false,
                        isPositiveArc = false,
                        8f,
                        4.9f
                    )
                    horizontalLineToRelative(13f)
                    verticalLineToRelative(-1f)
                    arcToRelative(3f, 3f, 0f, isMoreThanHalf = false, isPositiveArc = true, 3f, -3f)
                    horizontalLineToRelative(8f)
                    arcToRelative(3f, 3f, 0f, isMoreThanHalf = false, isPositiveArc = true, 3f, 3f)
                    verticalLineToRelative(1f)
                    horizontalLineToRelative(13f)
                    arcToRelative(
                        9f,
                        9f,
                        0f,
                        isMoreThanHalf = false,
                        isPositiveArc = false,
                        8f,
                        -4.9f
                    )
                    verticalLineToRelative(-9.1f)
                    arcToRelative(
                        5.006f,
                        5.006f,
                        0f,
                        isMoreThanHalf = false,
                        isPositiveArc = false,
                        -5f,
                        -5f
                    )
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
                    moveTo(28f, 34f)
                    horizontalLineTo(36f)
                    arcTo(1f, 1f, 0f, isMoreThanHalf = false, isPositiveArc = true, 37f, 35f)
                    verticalLineTo(39f)
                    arcTo(1f, 1f, 0f, isMoreThanHalf = false, isPositiveArc = true, 36f, 40f)
                    horizontalLineTo(28f)
                    arcTo(1f, 1f, 0f, isMoreThanHalf = false, isPositiveArc = true, 27f, 39f)
                    verticalLineTo(35f)
                    arcTo(1f, 1f, 0f, isMoreThanHalf = false, isPositiveArc = true, 28f, 34f)
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
                    moveTo(24f, 14f)
                    arcToRelative(1f, 1f, 0f, isMoreThanHalf = false, isPositiveArc = true, 1f, -1f)
                    horizontalLineToRelative(14f)
                    arcToRelative(1f, 1f, 0f, isMoreThanHalf = false, isPositiveArc = true, 1f, 1f)
                    verticalLineToRelative(1f)
                    horizontalLineToRelative(6f)
                    verticalLineToRelative(-1f)
                    arcToRelative(
                        7.008f,
                        7.008f,
                        0f,
                        isMoreThanHalf = false,
                        isPositiveArc = false,
                        -7f,
                        -7f
                    )
                    horizontalLineToRelative(-14f)
                    arcToRelative(
                        7.008f,
                        7.008f,
                        0f,
                        isMoreThanHalf = false,
                        isPositiveArc = false,
                        -7f,
                        7f
                    )
                    verticalLineToRelative(1f)
                    horizontalLineToRelative(6f)
                    close()
                }
            }
        }.build()
        return _Briefcase!!
    }