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
private fun WebIcon() {
	Icon(AppIcons.Web, AppIcons.Web.name)
}

private var _Web: ImageVector? = null

val AppIcons.Web: ImageVector
	get() {
		if (_Web != null) {
			return _Web!!
		}
		_Web = ImageVector.Builder(
            name = "Web",
            defaultWidth = 32.dp,
            defaultHeight = 32.dp,
            viewportWidth = 32f,
            viewportHeight = 32f
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
				moveTo(16f, 1f)
				arcTo(15f, 15f, 0f, isMoreThanHalf = true, isPositiveArc = false, 31f, 16f)
				arcTo(15.017f, 15.017f, 0f, isMoreThanHalf = false, isPositiveArc = false, 16f, 1f)
				close()
				moveToRelative(0f, 28f)
				curveToRelative(-1.5440f, 00f, -3.20f, -2.2870f, -4.1650f, -60f)
				horizontalLineToRelative(8.33f)
				curveTo(19.20f, 26.7130f, 17.5440f, 290f, 160f, 290f)
				close()
				moveToRelative(-4.592f, -8f)
				arcToRelative(30.841f, 30.841f, 0f, isMoreThanHalf = false, isPositiveArc = true, 0f, -10f)
				horizontalLineToRelative(9.184f)
				arcTo(29.9f, 29.9f, 0f, isMoreThanHalf = false, isPositiveArc = true, 21f, 16f)
				arcToRelative(29.9f, 29.9f, 0f, isMoreThanHalf = false, isPositiveArc = true, -0.408f, 5f)
				close()
				moveTo(16f, 3f)
				curveToRelative(1.5440f, 00f, 3.20f, 2.2870f, 4.1650f, 60f)
				horizontalLineToRelative(-8.33f)
				curveTo(12.80f, 5.2870f, 14.4560f, 30f, 160f, 30f)
				close()
				moveToRelative(6.624f, 8f)
				horizontalLineTo(28f)
				arcToRelative(12.964f, 12.964f, 0f, isMoreThanHalf = false, isPositiveArc = true, 0f, 10f)
				horizontalLineTo(22.624f)
				arcTo(32.787f, 32.787f, 0f, isMoreThanHalf = false, isPositiveArc = false, 23f, 16f)
				arcTo(32.787f, 32.787f, 0f, isMoreThanHalf = false, isPositiveArc = false, 22.624f, 11f)
				close()
				moveToRelative(4.313f, -2f)
				horizontalLineToRelative(-4.7f)
				arcToRelative(16.35f, 16.35f, 0f, isMoreThanHalf = false, isPositiveArc = false, -2.161f, -5.335f)
				arcTo(13.042f, 13.042f, 0f, isMoreThanHalf = false, isPositiveArc = true, 26.937f, 9f)
				close()
				moveTo(11.922f, 3.665f)
				arcTo(16.35f, 16.35f, 0f, isMoreThanHalf = false, isPositiveArc = false, 9.761f, 9f)
				horizontalLineToRelative(-4.7f)
				arcTo(13.042f, 13.042f, 0f, isMoreThanHalf = false, isPositiveArc = true, 11.922f, 3.665f)
				close()
				moveTo(4f, 11f)
				horizontalLineTo(9.376f)
				arcToRelative(33.433f, 33.433f, 0f, isMoreThanHalf = false, isPositiveArc = false, 0f, 10f)
				horizontalLineTo(4f)
				arcTo(12.964f, 12.964f, 0f, isMoreThanHalf = false, isPositiveArc = true, 4f, 11f)
				close()
				moveToRelative(1.06f, 12f)
				horizontalLineToRelative(4.7f)
				arcToRelative(16.35f, 16.35f, 0f, isMoreThanHalf = false, isPositiveArc = false, 2.161f, 5.335f)
				arcTo(13.042f, 13.042f, 0f, isMoreThanHalf = false, isPositiveArc = true, 5.063f, 23f)
				close()
				moveToRelative(15.015f, 5.335f)
				arcTo(16.35f, 16.35f, 0f, isMoreThanHalf = false, isPositiveArc = false, 22.239f, 23f)
				horizontalLineToRelative(4.7f)
				arcTo(13.042f, 13.042f, 0f, isMoreThanHalf = false, isPositiveArc = true, 20.078f, 28.335f)
				close()
			}
		}.build()
		return _Web!!
	}