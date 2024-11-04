package dev.zzemlyanaya.focuspotion.uikit.icons

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.EvenOdd
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Icon

@Preview
@Composable
private fun MorePreview() {
	Icon(AppIcons.More, null)
}

private var _more: ImageVector? = null

val AppIcons.More: ImageVector
	get() {
		if (_more != null) {
			return _more!!
		}
		_more = ImageVector.Builder(
			name = "More",
			defaultWidth = 24.0.dp,
			defaultHeight = 24.0.dp,
			viewportWidth = 24.0f,
			viewportHeight = 24.0f
		).apply {
			path(
				fill = SolidColor(Color(0xFFFFFFFF)),
				stroke = null,
				strokeLineWidth = 0.0f,
				strokeLineCap = Butt,
				strokeLineJoin = Miter,
				strokeLineMiter = 4.0f,
				pathFillType = EvenOdd
			) {
				moveTo(6.0f, 10.0f)
				curveTo(7.1046f, 10.0f, 8.0f, 10.8954f, 8.0f, 12.0f)
				curveTo(8.0f, 13.1046f, 7.1046f, 14.0f, 6.0f, 14.0f)
				curveTo(4.8954f, 14.0f, 4.0f, 13.1046f, 4.0f, 12.0f)
				curveTo(4.0f, 10.8954f, 4.8954f, 10.0f, 6.0f, 10.0f)
				close()
				moveTo(12.0f, 10.0f)
				curveTo(13.1046f, 10.0f, 14.0f, 10.8954f, 14.0f, 12.0f)
				curveTo(14.0f, 13.1046f, 13.1046f, 14.0f, 12.0f, 14.0f)
				curveTo(10.8954f, 14.0f, 10.0f, 13.1046f, 10.0f, 12.0f)
				curveTo(10.0f, 10.8954f, 10.8954f, 10.0f, 12.0f, 10.0f)
				close()
				moveTo(18.0f, 10.0f)
				curveTo(19.1046f, 10.0f, 20.0f, 10.8954f, 20.0f, 12.0f)
				curveTo(20.0f, 13.1046f, 19.1046f, 14.0f, 18.0f, 14.0f)
				curveTo(16.8954f, 14.0f, 16.0f, 13.1046f, 16.0f, 12.0f)
				curveTo(16.0f, 10.8954f, 16.8954f, 10.0f, 18.0f, 10.0f)
				close()
			}
		}.build()
		return _more!!
	}