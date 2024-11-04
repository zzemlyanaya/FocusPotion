package dev.zzemlyanaya.focuspotion.features.mainScreen.view

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.wear.compose.material.ButtonDefaults
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text
import androidx.wear.tooling.preview.devices.WearDevices
import com.google.android.horologist.compose.material.Button
import com.google.android.horologist.compose.material.CompactChip
import dev.zzemlyanaya.focuspotion.R
import dev.zzemlyanaya.focuspotion.core.contract.BaseIntent
import dev.zzemlyanaya.focuspotion.core.ui.BaseScreen
import dev.zzemlyanaya.focuspotion.features.mainScreen.model.MainScreenContract
import dev.zzemlyanaya.focuspotion.features.mainScreen.viewModel.MainScreenViewModel
import dev.zzemlyanaya.focuspotion.features.presets.model.PresetUiModel
import dev.zzemlyanaya.focuspotion.uikit.FocusPotionTheme
import dev.zzemlyanaya.focuspotion.uikit.constants.LocalSpacing

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    val viewModel = hiltViewModel<MainScreenViewModel>()

    BaseScreen(
        modifier = modifier,
        uiFlow = viewModel.screenState,
        sendIntent = viewModel::sendIntent,
        content = { mModifier, uiState, sendEvent ->
            MainScreen(
                mModifier,
                uiState,
                sendEvent
            ) },
    )
}

@Composable
private fun MainScreen(
    modifier: Modifier = Modifier,
    uiState: MainScreenContract.UiState,
    sendIntent: (BaseIntent) -> Unit,
) {
    Column(
        modifier = modifier.fillMaxSize().padding(LocalSpacing.current.large),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            if (uiState.isEmptyMessageVisible) {
                Text(
                    textAlign = TextAlign.Center,
                    text = stringResource(R.string.no_presets)
                )
            } else {
                // TODO consider using FlowRow for this
                PresetsRow(uiState.firstRow) { sendIntent.invoke(MainScreenContract.Intent.PresetClick(it)) }

                if (uiState.secondRow.isNotEmpty()) {
                    PresetsRow(uiState.secondRow) { sendIntent.invoke(MainScreenContract.Intent.PresetClick(it)) }
                }
            }
        }

        CompactChip(
            onClick = { sendIntent(MainScreenContract.Intent.CreateNew) },
            label = stringResource(R.string.btn_new)
        )
    }
}

@Composable
private fun PresetsRow(
    row: List<PresetUiModel>,
    onClick: (String) -> Unit
) {
    Row(horizontalArrangement = Arrangement.spacedBy(LocalSpacing.current.xSmall)) {
        row.forEach { PresetButton(it, onClick) }
    }
}

@Composable
private fun PresetButton(
    presetUiModel: PresetUiModel,
    onClick: (String) -> Unit
) {
    val colors = if (presetUiModel.isSecondary) {
        ButtonDefaults.secondaryButtonColors(contentColor = MaterialTheme.colors.primary)
    } else {
        ButtonDefaults.primaryButtonColors()
    }

    Button(
        imageVector = presetUiModel.icon,
        contentDescription = presetUiModel.name,
        onClick = { onClick(presetUiModel.name) },
        colors = colors
    )
}

@Preview
@Composable
fun PresetsPreview() {
    Row {
       PresetButton(PresetUiModel("Name", Icons.Filled.Create)) { }
       PresetButton(PresetUiModel.More) { }
    }
}

@Preview(
    device = WearDevices.SMALL_ROUND,
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_WATCH
)
@Composable
fun MainScreenPreview2Rows() {
    FocusPotionTheme {
        MainScreen(uiState = MainScreenContract.UiState(
            firstRow = listOf(
                PresetUiModel("Name", Icons.Filled.Create),
                PresetUiModel("Name", Icons.Filled.Favorite)
            ),
            secondRow = listOf(
                PresetUiModel("Name", Icons.Filled.Face),
                PresetUiModel("Name", Icons.Filled.Star),
                PresetUiModel.More
            )
        )) { }
    }
}

@Preview(
    device = WearDevices.SMALL_ROUND,
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_WATCH
)
@Composable
fun MainScreenPreview1Row() {
    FocusPotionTheme {
        MainScreen(uiState = MainScreenContract.UiState(
            firstRow = listOf(
                PresetUiModel("Name", Icons.Filled.Create),
                PresetUiModel("Name", Icons.Filled.Favorite),
                PresetUiModel.More
            )
        )) { }
    }
}

@Preview(
    device = WearDevices.SMALL_ROUND,
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_WATCH
)
@Composable
fun MainScreenPreviewEmpty() {
    FocusPotionTheme {
        MainScreen(uiState = MainScreenContract.UiState()) { }
    }
}