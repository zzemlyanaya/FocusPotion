package dev.zzemlyanaya.focuspotion.features.presets.view

import android.app.RemoteInput
import android.content.res.Configuration
import android.view.inputmethod.EditorInfo
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.ButtonDefaults
import androidx.wear.compose.material.Icon
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.TitleCard
import androidx.wear.input.RemoteInputIntentHelper
import androidx.wear.input.wearableExtender
import androidx.wear.tooling.preview.devices.WearDevices
import com.google.android.horologist.compose.layout.*
import com.google.android.horologist.compose.layout.ScalingLazyColumnDefaults.ItemType
import com.google.android.horologist.compose.material.*
import com.google.android.horologist.compose.material.Card
import com.google.android.horologist.compose.material.ListHeaderDefaults.firstItemPadding
import dev.zzemlyanaya.focuspotion.R
import dev.zzemlyanaya.focuspotion.core.contract.BaseIntent
import dev.zzemlyanaya.focuspotion.core.ui.BaseScreen
import dev.zzemlyanaya.focuspotion.features.presets.model.contract.NewPresetContract
import dev.zzemlyanaya.focuspotion.features.presets.viewModel.NewPresetViewModel
import dev.zzemlyanaya.focuspotion.uikit.FocusPotionTheme
import dev.zzemlyanaya.focuspotion.uikit.components.ControlButtons
import dev.zzemlyanaya.focuspotion.uikit.tokens.LocalSpacing

const val NAME_INPUT = "name"

@Composable
fun NewPresetScreen(modifier: Modifier = Modifier) {
    val viewModel = hiltViewModel<NewPresetViewModel>()

    BaseScreen(
        modifier = modifier,
        uiFlow = viewModel.screenState,
        sendIntent = viewModel::sendIntent,
        content = { mModifier, uiState, sendEvent ->
            NewPresetScreen(
                mModifier,
                uiState,
                sendEvent
            ) },
    )
}

@Composable
private fun NewPresetScreen(
    modifier: Modifier = Modifier,
    uiState: NewPresetContract.UiState,
    sendIntent: (BaseIntent) -> Unit,
) {
    val columnState = rememberResponsiveColumnState(
        contentPadding = ScalingLazyColumnDefaults.padding(
            first = ItemType.Text,
            last = ItemType.MultiButton
        )
    )

    val remoteInputs: List<RemoteInput> = listOf(
        RemoteInput.Builder(NAME_INPUT)
            .setLabel(stringResource(id = R.string.potion_name))
            .wearableExtender {
                setEmojisAllowed(false)
                setInputActionType(EditorInfo.IME_ACTION_DONE)
            }.build(),
    )

    val nameInputLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        it.data?.let { data ->
           RemoteInput.getResultsFromIntent(data)
               ?.getCharSequence(NAME_INPUT)
               ?.toString()
               ?.let { sendIntent.invoke(NewPresetContract.Intent.NameEdit(it)) }
        }
    }

    val nameInputIntent = RemoteInputIntentHelper.createActionRemoteInputIntent()
    RemoteInputIntentHelper.putRemoteInputsExtra(nameInputIntent, remoteInputs)

    ScreenScaffold(
        modifier = modifier.fillMaxSize(),
        scrollState = columnState
    ) {
        ScalingLazyColumn(columnState = columnState) {
            item {
                ResponsiveListHeader(contentPadding = firstItemPadding()) {
                    Text(text = stringResource(uiState.title))
                }
            }

            item { NameCard(uiState.name) { nameInputLauncher.launch(nameInputIntent) } }

            item { SettingsCard(uiState, sendIntent) }

            item { LongBreakCard(uiState, sendIntent) }

            item {
                ControlButtons(
                    modifier = Modifier.padding(top = LocalSpacing.current.xLarge),
                    onCancel = { sendIntent.invoke(NewPresetContract.Intent.CancelClick) },
                    onSave = { sendIntent.invoke(NewPresetContract.Intent.SavePresetClick) }
                )
            }
        }
    }
}

@Composable
private fun SettingsCard(uiState: NewPresetContract.UiState, sendIntent: (BaseIntent) -> Unit) {
    Card(onClick = {}, Modifier.padding(vertical = LocalSpacing.current.small)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(LocalSpacing.current.small),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                SettingsButton(
                    name = stringResource(R.string.icon),
                    icon = uiState.icon
                ) { sendIntent.invoke(NewPresetContract.Intent.IconClick) }

                SettingsButton(
                    name = stringResource(R.string.focus_for),
                    value = stringResource(R.string.min_watch, uiState.focusTime)
                ) { sendIntent.invoke(NewPresetContract.Intent.FocusClick) }
            }

            Column(
                verticalArrangement = Arrangement.spacedBy(LocalSpacing.current.medium),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                SettingsButton(
                    name = stringResource(R.string.sessions),
                    value = uiState.sessions
                ) { sendIntent.invoke(NewPresetContract.Intent.SessionsClick) }

                SettingsButton(
                    name = stringResource(R.string.short_break),
                    value = stringResource(R.string.min_watch, uiState.shortBreak)
                ) { sendIntent.invoke(NewPresetContract.Intent.ShortBreakClick) }
            }
        }
    }
}

@Composable
private fun SettingsButton(
    name: String,
    value: String? = null,
    icon: ImageVector? = null,
    onClick: () -> Unit
) {
    Column(
        Modifier.width(ButtonDefaults.DefaultButtonSize + 12.dp),
        verticalArrangement = Arrangement.spacedBy(LocalSpacing.current.xSmall),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = onClick) {
            value?.let { Text(it) }
            icon?.let {
                Icon(
                    modifier = Modifier.size(ButtonDefaults.DefaultIconSize),
                    imageVector = it,
                    contentDescription = it.name
                )
            }
        }

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = name,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.caption2
        )
    }
}

@Composable
private fun LongBreakCard(uiState: NewPresetContract.UiState, sendIntent: (BaseIntent) -> Unit) {
    SplitToggleChip(
        checked = uiState.repeatAfterLongBreak,
        onClick = { sendIntent.invoke(NewPresetContract.Intent.LongBreakClick) },
        onCheckedChanged = { sendIntent.invoke(NewPresetContract.Intent.RepeatSwitch(it)) },
        label = stringResource(R.string.repeat_after),
        secondaryLabel = stringResource(R.string.min_text, uiState.longBreak),
        toggleControl = ToggleChipToggleControl.Switch
    )
}

@Composable
private fun NameCard(name: String, onClick: () -> Unit) {
    TitleCard(
        title = { Text(text = stringResource(R.string.potion_name)) },
        onClick = onClick,
    ) {
        Text(text = name)
    }
}



@Preview
@Composable
private fun ItemsPreview() {
    FocusPotionTheme {
       Column {
           NameCard("Name") { }

           Spacer(modifier = Modifier.height(16.dp))

           LongBreakCard(NewPresetContract.UiState(repeatAfterLongBreak = true)) {}

           Spacer(modifier = Modifier.height(16.dp))

           ControlButtons({}, {})
       }
    }
}

@Preview
@Composable
private fun SettingsCardPreview() {
    FocusPotionTheme {
        SettingsCard(NewPresetContract.UiState(), {})
    }
}

@Preview(
    device = WearDevices.SMALL_ROUND,
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_WATCH
)
@Composable
fun NewPresetScreenPreview() {
    FocusPotionTheme {
        NewPresetScreen(uiState = NewPresetContract.UiState()) { }
    }
}