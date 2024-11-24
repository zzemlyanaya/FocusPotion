package dev.zzemlyanaya.focuspotion.features.presets.impl.view

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.wear.compose.material.*
import androidx.wear.tooling.preview.devices.WearDevices
import com.google.android.horologist.compose.layout.ScreenScaffold
import com.google.android.horologist.compose.material.ResponsiveListHeader
import dev.zzemlyanaya.focuspotion.R
import dev.zzemlyanaya.focuspotion.core.contract.BaseIntent
import dev.zzemlyanaya.focuspotion.core.ui.BaseScreen
import dev.zzemlyanaya.focuspotion.features.presets.impl.model.contract.NumberPickerContract
import dev.zzemlyanaya.focuspotion.features.presets.impl.viewModel.NumberPickerViewModel
import dev.zzemlyanaya.focuspotion.uikit.FocusPotionTheme
import dev.zzemlyanaya.focuspotion.uikit.components.ControlButtons
import dev.zzemlyanaya.focuspotion.uikit.components.wheelPicker.VerticalWheelPicker
import dev.zzemlyanaya.focuspotion.uikit.components.wheelPicker.rememberWheelPickerState
import dev.zzemlyanaya.focuspotion.uikit.tokens.LocalSpacing
import kotlinx.coroutines.delay


@Composable
fun NumberPickerScreen(
    modifier: Modifier = Modifier
) {
    val viewModel = hiltViewModel<NumberPickerViewModel>()

    BaseScreen(
        modifier = modifier,
        uiFlow = viewModel.screenState,
        sendIntent = viewModel::sendIntent,
        content = { mModifier, uiState, sendEvent ->
            NumberPickerScreen(
                mModifier,
                uiState,
                sendEvent
            ) },
    )
}

@Composable
private fun NumberPickerScreen(
    modifier: Modifier = Modifier,
    uiState: NumberPickerContract.UiState,
    sendIntent: (BaseIntent) -> Unit,
) {
    val wheelState = rememberWheelPickerState(0)
    uiState.scrollToCurrent?.let {
        LaunchedEffect(wheelState) {
            delay(200)
            wheelState.animateScrollToIndex(it)
        }
    }

    ScreenScaffold(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ResponsiveListHeader {
                Text(text = stringResource(uiState.title))
            }

            VerticalWheelPicker(
                modifier = Modifier.weight(1f),
                state = wheelState,
                count = uiState.range.size,
                key = { it },
                itemHeight = 38.dp
            ) {
                Text(
                    uiState.range[it],
                    style = MaterialTheme.typography.display1,
                    color = MaterialTheme.colors.secondary
                )
            }

            ControlButtons(
                modifier = Modifier.padding(bottom = LocalSpacing.current.medium).height(ButtonDefaults.ExtraSmallButtonSize),
                onCancel = { sendIntent.invoke(NumberPickerContract.Intent.CancelClick) },
                onSave = { sendIntent.invoke(NumberPickerContract.Intent.SaveClick(wheelState.currentIndex)) }
            )
        }
    }
}

@Preview(
    device = WearDevices.SMALL_ROUND,
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_WATCH
)
@Composable
fun NumberPickerScreenPreview() {
    FocusPotionTheme {
        NumberPickerScreen(uiState =
            NumberPickerContract.UiState(
                R.string.focus_for,
                (1..60).map { it.toString() }
            )
        ) {}
    }
}