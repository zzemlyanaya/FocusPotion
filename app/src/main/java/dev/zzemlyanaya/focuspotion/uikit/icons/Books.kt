package dev.zzemlyanaya.focuspotion.uikit.icons

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.vector.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Icon


@Preview
@Composable
private fun BooksIcon() {
    Icon(AppIcons.Books, AppIcons.Books.name)
}

private var _Books: ImageVector? = null

val AppIcons.Books: ImageVector
    get() {
        if (_Books != null) {
            return _Books!!
        }
        _Books = ImageVector.Builder(
            name = "Books",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 16.933333f,
            viewportHeight = 16.933334f
        ).apply {
            group(
                scaleX = 1f,
                scaleY = 1f,
                translationX = 0f,
                translationY = -280.067f,
                pivotX = 0f,
                pivotY = 0f,
            ) {
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
                    moveTo(14.82131f, 293.0312f)
                    curveToRelative(0.08980f, 0.00030f, 0.17890f, 0.01590f, 0.26350f, 0.0460f)
                    curveToRelative(
                        -0.00110f,
                        -1.70570f,
                        0.00210f,
                        -11.95220f,
                        0.00210f,
                        -11.95220f
                    )
                    curveToRelative(
                        -0.00010f,
                        -0.29220f,
                        -0.24510f,
                        -0.52770f,
                        -0.53120f,
                        -0.52760f
                    )
                    lineToRelative(-11.6426985f, 0.002f)
                    curveToRelative(-0.2860f, 0.00010f, -0.53920f, 0.22880f, -0.5390f, 0.52760f)
                    lineToRelative(0.010345f, 12.42663f)
                    curveToRelative(0.31790f, -0.3090f, 0.7960f, -0.52160f, 1.32240f, -0.52240f)
                    close()
                    moveToRelative(-8.8351204f, -9.40562f)
                    horizontalLineToRelative(5.4885534f)
                    curveToRelative(0.30230f, 00f, 0.55450f, 0.25270f, 0.55450f, 0.5550f)
                    verticalLineToRelative(1.5565f)
                    curveToRelative(00f, 0.30230f, -0.25220f, 0.5550f, -0.55450f, 0.5550f)
                    horizontalLineToRelative(-5.4885534f)
                    curveToRelative(-0.30230f, 00f, -0.55450f, -0.25270f, -0.55450f, -0.5550f)
                    verticalLineToRelative(-1.5565f)
                    curveToRelative(00f, -0.30230f, 0.25220f, -0.5550f, 0.55450f, -0.5550f)
                    close()
                    moveToRelative(5.5012144f, 2.12416f)
                    verticalLineToRelative(-1.58182f)
                    horizontalLineToRelative(-5.5138748f)
                    verticalLineToRelative(1.58182f)
                    close()
                    moveToRelative(-8.8188408f, 8.27572f)
                    curveToRelative(-0.21490f, 0.26720f, -0.29650f, 0.58580f, -0.28470f, 0.86870f)
                    curveToRelative(0.01080f, 0.26160f, 0.07870f, 0.56190f, 0.23820f, 0.82420f)
                    curveToRelative(0.15950f, 0.26240f, 0.44460f, 0.49480f, 0.8160f, 0.49450f)
                    lineToRelative(11.3832855f, -0.006f)
                    curveToRelative(0.30050f, 0.00030f, 0.36890f, -0.4210f, 0.08370f, -0.51570f)
                    curveToRelative(00f, 00f, -0.61340f, -0.19090f, -0.61340f, -0.80820f)
                    reflectiveCurveToRelative(0.6134f, -0.80667f, 0.6134f, -0.80667f)
                    curveToRelative(0.28520f, -0.09470f, 0.21680f, -0.51610f, -0.08370f, -0.51570f)
                    horizontalLineToRelative(-11.1145694f)
                    curveToRelative(-0.46440f, 0.00080f, -0.82320f, 0.19790f, -1.03820f, 0.46510f)
                    close()
                    moveToRelative(1.0392119f, 0.59376f)
                    horizontalLineToRelative(9.6505739f)
                    curveToRelative(-0.07720f, 0.09030f, -0.12510f, 0.13870f, -0.12510f, 0.26360f)
                    curveToRelative(00f, 0.12490f, 0.04230f, 0.17460f, 0.12710f, 0.26560f)
                    lineToRelative(-9.6510894f, 0.003f)
                    curveToRelative(-0.12310f, 0.0030f, -0.26620f, -0.09460f, -0.26820f, -0.26920f)
                    curveToRelative(-0.00210f, -0.17460f, 0.12360f, -0.26170f, 0.26660f, -0.2630f)
                    close()
                }
            }
        }.build()
        return _Books!!
    }