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
private fun WriterIcon() {
    Icon(AppIcons.Write, AppIcons.Write.name)
}

private var _Write: ImageVector? = null

val AppIcons.Write: ImageVector
    get() {
        if (_Write != null) {
            return _Write!!
        }
        _Write = ImageVector.Builder(
            name = "Write",
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
                        moveTo(73.159f, 481.76f)
                        lineTo(153.28f, 448.642f)
                        lineTo(153.39f, 448.532f)
                        lineTo(63.458f, 358.61f)
                        lineTo(63.358f, 358.71f)
                        lineTo(12.498f, 481.76f)
                        lineTo(0.17f, 481.76f)
                        lineTo(0.17f, 511.59f)
                        lineTo(0f, 512f)
                        lineTo(0.649f, 511.73f)
                        lineTo(436.744f, 511.73f)
                        lineTo(436.744f, 481.76f)
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
                        moveTo(283.803f, 138.265f)
                        lineTo(84.657f, 337.42f)
                        lineTo(174.58f, 427.343f)
                        lineTo(373.735f, 228.197f)
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
                        moveTo(333.804f, 88.264f)
                        lineTo(304.992f, 117.076f)
                        lineTo(394.924f, 206.998f)
                        lineTo(423.726f, 178.196f)
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
                        moveTo(422.078f, 0f)
                        lineTo(354.993f, 67.075f)
                        lineTo(444.925f, 157.007f)
                        lineTo(512f, 89.922f)
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
                        moveTo(270.905f, 412.357f)
                        horizontalLineTo(431.74799999999993f)
                        verticalLineTo(442.32800000000003f)
                        horizontalLineTo(270.905f)
                        verticalLineTo(412.357f)
                        close()
                    }
                }
            }
        }.build()
        return _Write!!
    }