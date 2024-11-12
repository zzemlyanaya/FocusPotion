package dev.zzemlyanaya.focuspotion.uikit.components.wheelPicker

import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.nestedscroll.*
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.*

interface WheelPickerContentScope {
    val state: WheelPickerState
}

interface WheelPickerDisplayScope : WheelPickerContentScope {
    @Composable
    fun Content(index: Int)
}

@Composable
fun VerticalWheelPicker(
    modifier: Modifier = Modifier,
    count: Int,
    state: WheelPickerState = rememberWheelPickerState(),
    key: ((index: Int) -> Any)? = null,
    itemHeight: Dp = 35.dp,
    unfocusedCount: Int = 2,
    userScrollEnabled: Boolean = true,
    reverseLayout: Boolean = false,
    focus: @Composable () -> Unit = { WheelPickerFocusVertical() },
    display: @Composable WheelPickerDisplayScope.(index: Int) -> Unit = { DefaultWheelPickerDisplay(it) },
    content: @Composable WheelPickerContentScope.(index: Int) -> Unit,
) {
    WheelPicker(
        modifier = modifier,
        isVertical = true,
        count = count,
        state = state,
        key = key,
        itemSize = itemHeight,
        unfocusedCount = unfocusedCount,
        userScrollEnabled = userScrollEnabled,
        reverseLayout = reverseLayout,
        focus = focus,
        display = display,
        content = content,
    )
}

@Composable
fun HorizontalWheelPicker(
    modifier: Modifier = Modifier,
    count: Int,
    state: WheelPickerState = rememberWheelPickerState(),
    key: ((index: Int) -> Any)? = null,
    itemWidth: Dp = 35.dp,
    unfocusedCount: Int = 2,
    userScrollEnabled: Boolean = true,
    reverseLayout: Boolean = false,
    focus: @Composable () -> Unit = { WheelPickerFocusHorizontal() },
    display: @Composable WheelPickerDisplayScope.(index: Int) -> Unit = { DefaultWheelPickerDisplay(it) },
    content: @Composable WheelPickerContentScope.(index: Int) -> Unit,
) {
    WheelPicker(
        modifier = modifier,
        isVertical = false,
        count = count,
        state = state,
        key = key,
        itemSize = itemWidth,
        unfocusedCount = unfocusedCount,
        userScrollEnabled = userScrollEnabled,
        reverseLayout = reverseLayout,
        focus = focus,
        display = display,
        content = content,
    )
}

@Composable
private fun WheelPicker(
    modifier: Modifier,
    isVertical: Boolean,
    count: Int,
    state: WheelPickerState,
    key: ((index: Int) -> Any)?,
    itemSize: Dp,
    unfocusedCount: Int,
    userScrollEnabled: Boolean,
    reverseLayout: Boolean,
    focus: @Composable () -> Unit,
    display: @Composable WheelPickerDisplayScope.(index: Int) -> Unit,
    content: @Composable WheelPickerContentScope.(index: Int) -> Unit,
) {
    require(count >= 0) { "Require count >= 0" }
    require(unfocusedCount >= 0) { "Require unfocusedCount >= 0" }
    require(itemSize > 0.dp) { "Require itemSize > 0.dp" }

    SafeBox(
        modifier = modifier,
        isVertical = isVertical,
        itemSize = itemSize,
        unfocusedCount = unfocusedCount,
    ) { safeUnfocusedCount ->
        InternalWheelPicker(
            isVertical = isVertical,
            count = count,
            state = state,
            key = key,
            itemSize = itemSize,
            unfocusedCount = safeUnfocusedCount,
            userScrollEnabled = userScrollEnabled,
            reverseLayout = reverseLayout,
            focus = focus,
            display = display,
            content = content,
        )
    }
}

@Composable
private fun InternalWheelPicker(
    isVertical: Boolean,
    count: Int,
    state: WheelPickerState,
    key: ((index: Int) -> Any)?,
    itemSize: Dp,
    unfocusedCount: Int,
    userScrollEnabled: Boolean,
    reverseLayout: Boolean,
    focus: @Composable () -> Unit,
    display: @Composable WheelPickerDisplayScope.(index: Int) -> Unit,
    content: @Composable WheelPickerContentScope.(index: Int) -> Unit,
) {
    LaunchedEffect(state, count) {
        state.updateCount(count)
    }

    val nestedScrollConnection = remember(state) {
        WheelPickerNestedScrollConnection(state)
    }.apply {
        this.isVertical = isVertical
        this.itemSizePx = with(LocalDensity.current) { itemSize.roundToPx() }
        this.reverseLayout = reverseLayout
    }

    val totalSize = remember(itemSize, unfocusedCount) {
        itemSize * (unfocusedCount * 2 + 1)
    }

    val displayScope = remember(state) {
        WheelPickerDisplayScopeImpl(state)
    }.apply {
        this.content = content
    }

    Box(
        modifier = Modifier
            .nestedScroll(nestedScrollConnection)
            .graphicsLayer {
                this.alpha = if (state.isReady) 1f else 0f
            }
            .run {
                if (totalSize > 0.dp) {
                    if (isVertical) {
                        height(totalSize).widthIn(40.dp)
                    } else {
                        width(totalSize).heightIn(40.dp)
                    }
                } else {
                    this
                }
            },
        contentAlignment = Alignment.Center,
    ) {

        val lazyListScope: LazyListScope.() -> Unit = {

            repeat(unfocusedCount) {
                item(contentType = "placeholder") {
                    ItemSizeBox(
                        isVertical = isVertical,
                        itemSize = itemSize,
                    )
                }
            }

            items(
                count = count,
                key = key,
            ) { index ->
                ItemSizeBox(
                    isVertical = isVertical,
                    itemSize = itemSize,
                ) {
                    displayScope.display(index)
                }
            }

            repeat(unfocusedCount) {
                item(contentType = "placeholder") {
                    ItemSizeBox(
                        isVertical = isVertical,
                        itemSize = itemSize,
                    )
                }
            }
        }

        if (isVertical) {
            LazyColumn(
                state = state.lazyListState,
                horizontalAlignment = Alignment.CenterHorizontally,
                reverseLayout = reverseLayout,
                userScrollEnabled = userScrollEnabled && state.isReady,
                modifier = Modifier.matchParentSize(),
                content = lazyListScope,
            )
        } else {
            LazyRow(
                state = state.lazyListState,
                verticalAlignment = Alignment.CenterVertically,
                reverseLayout = reverseLayout,
                userScrollEnabled = userScrollEnabled && state.isReady,
                modifier = Modifier.matchParentSize(),
                content = lazyListScope,
            )
        }

        ItemSizeBox(
            modifier = Modifier.align(Alignment.Center),
            isVertical = isVertical,
            itemSize = itemSize,
        ) {
            focus()
        }
    }
}

@Composable
private fun SafeBox(
    modifier: Modifier = Modifier,
    isVertical: Boolean,
    itemSize: Dp,
    unfocusedCount: Int,
    content: @Composable (safeUnfocusedCount: Int) -> Unit,
) {
    BoxWithConstraints(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        val maxSize = if (isVertical) maxHeight else maxWidth
        val result = remember(maxSize, itemSize, unfocusedCount) {
            val totalSize = itemSize * (unfocusedCount * 2 + 1)
            if (totalSize <= maxSize) {
                unfocusedCount
            } else {
                (((maxSize - itemSize) / 2f) / itemSize).toInt().coerceAtLeast(0)
            }
        }
        content(result)
    }
}

@Composable
private fun ItemSizeBox(
    modifier: Modifier = Modifier,
    isVertical: Boolean,
    itemSize: Dp,
    content: @Composable () -> Unit = { },
) {
    Box(
        modifier
            .run {
                if (isVertical) {
                    height(itemSize)
                } else {
                    width(itemSize)
                }
            },
        contentAlignment = Alignment.Center,
    ) {
        content()
    }
}

private class WheelPickerNestedScrollConnection(
    private val state: WheelPickerState,
) : NestedScrollConnection {
    var isVertical: Boolean = true
    var itemSizePx: Int = 0
    var reverseLayout: Boolean = false

    override fun onPostScroll(consumed: Offset, available: Offset, source: NestedScrollSource): Offset {
        state.synchronizeCurrentIndexSnapshot()
        return super.onPostScroll(consumed, available, source)
    }

    override suspend fun onPreFling(available: Velocity): Velocity {
        val currentIndex = state.synchronizeCurrentIndexSnapshot()
        return if (currentIndex >= 0) {
            available.flingItemCount(
                isVertical = isVertical,
                itemSize = itemSizePx,
                decay = exponentialDecay(2f),
                reverseLayout = reverseLayout,
            ).let { flingItemCount ->
                if (flingItemCount == 0) {
                    state.animateScrollToIndex(currentIndex)
                } else {
                    state.animateScrollToIndex(currentIndex - flingItemCount)
                }
            }
            available
        } else {
            super.onPreFling(available)
        }
    }
}

private fun Velocity.flingItemCount(
    isVertical: Boolean,
    itemSize: Int,
    decay: DecayAnimationSpec<Float>,
    reverseLayout: Boolean,
): Int {
    if (itemSize <= 0) return 0
    val velocity = if (isVertical) y else x
    val targetValue = decay.calculateTargetValue(0f, velocity)
    val flingItemCount = (targetValue / itemSize).toInt()
    return if (reverseLayout) -flingItemCount else flingItemCount
}

private class WheelPickerDisplayScopeImpl(
    override val state: WheelPickerState,
) : WheelPickerDisplayScope {

    var content: @Composable WheelPickerContentScope.(index: Int) -> Unit by mutableStateOf({})

    @Composable
    override fun Content(index: Int) {
        content(index)
    }
}