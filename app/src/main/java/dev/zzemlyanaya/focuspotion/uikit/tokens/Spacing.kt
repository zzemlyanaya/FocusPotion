package dev.zzemlyanaya.focuspotion.uikit.tokens

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

val LocalSpacing = staticCompositionLocalOf<Spacing> { error("Spacing wasn't provided") }

@Immutable
object ConstantSpacing {
    val zero: Dp = 0.dp
    val two: Dp = 2.dp
    val four: Dp = 4.dp
    val six: Dp = 6.dp
    val eight: Dp = 8.dp
    val ten: Dp = 10.dp
    val twelve: Dp = 12.dp
    val sixteen: Dp = 16.dp
    val twentyFour: Dp = 24.dp
    val thirtyTwo: Dp = 32.dp
    val forty: Dp = 40.dp
    val fortyEight: Dp = 48.dp
}

@Immutable
object Spacing {
    val zero = ConstantSpacing.zero
    val xxSmall = ConstantSpacing.two
    val xSmall = ConstantSpacing.four
    val small = ConstantSpacing.six
    val medium = ConstantSpacing.eight
    val large = ConstantSpacing.ten
    val xLarge = ConstantSpacing.twelve
    val xxLarge = ConstantSpacing.sixteen
}