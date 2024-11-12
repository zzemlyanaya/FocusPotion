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
private fun StarIcon() {
	Icon(AppIcons.Star, AppIcons.Star.name)
}

private var _Star: ImageVector? = null

val AppIcons.Star: ImageVector
	get() {
		if (_Star != null) {
			return _Star!!
		}
		_Star = ImageVector.Builder(
            name = "Star",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
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
				moveTo(5.574f, 15.362f)
				lineToRelative(-1.267f, 7.767f)
				curveToRelative(-0.1010f, 0.6170f, 0.5580f, 1.080f, 1.1030f, 0.7770f)
				lineToRelative(6.59f, -3.642f)
				lineToRelative(6.59f, 3.643f)
				curveToRelative(0.540f, 0.30f, 1.2050f, -0.1540f, 1.1030f, -0.7770f)
				lineToRelative(-1.267f, -7.767f)
				lineToRelative(5.36f, -5.494f)
				curveToRelative(0.4250f, -0.4350f, 0.1810f, -1.1730f, -0.4230f, -1.2650f)
				lineToRelative(-7.378f, -1.127f)
				lineToRelative(-3.307f, -7.044f)
				curveToRelative(-0.2470f, -0.5260f, -1.110f, -0.5260f, -1.3570f, 00f)
				lineToRelative(-3.306f, 7.043f)
				lineToRelative(-7.378f, 1.127f)
				curveToRelative(-0.6060f, 0.0930f, -0.8480f, 0.830f, -0.4230f, 1.2650f)
				close()
			}
		}.build()
		return _Star!!
	}