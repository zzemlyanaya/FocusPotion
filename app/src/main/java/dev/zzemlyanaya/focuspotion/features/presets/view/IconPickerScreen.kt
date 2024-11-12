package dev.zzemlyanaya.focuspotion.features.presets.view

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.wear.compose.material.ButtonDefaults
import androidx.wear.compose.material.Text
import androidx.wear.tooling.preview.devices.WearDevices
import com.google.android.horologist.compose.layout.ScreenScaffold
import com.google.android.horologist.compose.material.*
import dev.zzemlyanaya.focuspotion.R
import dev.zzemlyanaya.focuspotion.core.contract.BaseIntent
import dev.zzemlyanaya.focuspotion.core.ui.BaseScreen
import dev.zzemlyanaya.focuspotion.features.presets.model.contract.IconPickerContract
import dev.zzemlyanaya.focuspotion.features.presets.viewModel.IconPickerViewModel
import dev.zzemlyanaya.focuspotion.uikit.FocusPotionTheme
import dev.zzemlyanaya.focuspotion.uikit.components.ControlButtons
import dev.zzemlyanaya.focuspotion.uikit.components.wheelPicker.HorizontalWheelPicker
import dev.zzemlyanaya.focuspotion.uikit.components.wheelPicker.rememberWheelPickerState
import dev.zzemlyanaya.focuspotion.uikit.icons.AppIcons
import dev.zzemlyanaya.focuspotion.uikit.icons.all
import dev.zzemlyanaya.focuspotion.uikit.tokens.LocalSpacing
import kotlinx.coroutines.delay


@Composable
fun IconPickerScreen(
    modifier: Modifier = Modifier
) {
    val viewModel = hiltViewModel<IconPickerViewModel>()

    BaseScreen(
        modifier = modifier,
        uiFlow = viewModel.screenState,
        sendIntent = viewModel::sendIntent,
        content = { mModifier, uiState, sendEvent ->
            IconPickerScreen(
                mModifier,
                uiState,
                sendEvent
            ) },
    )
}

@Composable
private fun IconPickerScreen(
    modifier: Modifier = Modifier,
    uiState: IconPickerContract.UiState,
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
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ResponsiveListHeader(Modifier.padding(top = LocalSpacing.current.medium)) {
                Text(text = stringResource(R.string.pick_icon))
            }

            HorizontalWheelPicker(
                modifier = Modifier.height(ButtonDefaults.LargeButtonSize),
                state = wheelState,
                count = uiState.icons.size,
                key = { it },
                itemWidth = ButtonDefaults.LargeButtonSize
            ) {
                Button(
                    imageVector = uiState.icons[it],
                    contentDescription = uiState.icons[it].name,
                    onClick = {},
                    buttonSize = ButtonSize.Large
                )
            }

            ControlButtons(
                modifier = Modifier.padding(bottom = LocalSpacing.current.medium).height(ButtonDefaults.SmallButtonSize),
                onCancel = { sendIntent.invoke(IconPickerContract.Intent.CancelClick) },
                onSave = { sendIntent.invoke(IconPickerContract.Intent.SaveClick(wheelState.currentIndex)) }
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
fun IconPickerScreenPreview() {
    FocusPotionTheme {
        IconPickerScreen(uiState = IconPickerContract.UiState(AppIcons.all())) {}
    }
}