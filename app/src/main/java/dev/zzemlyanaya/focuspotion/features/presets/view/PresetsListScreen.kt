package dev.zzemlyanaya.focuspotion.features.presets.view

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.wear.compose.foundation.lazy.items
import androidx.wear.compose.material.ChipDefaults
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text
import androidx.wear.tooling.preview.devices.WearDevices
import com.google.android.horologist.compose.layout.ScalingLazyColumn
import com.google.android.horologist.compose.layout.ScalingLazyColumnDefaults
import com.google.android.horologist.compose.layout.ScalingLazyColumnDefaults.ItemType
import com.google.android.horologist.compose.layout.ScreenScaffold
import com.google.android.horologist.compose.layout.rememberResponsiveColumnState
import com.google.android.horologist.compose.material.Chip
import com.google.android.horologist.compose.material.ListHeaderDefaults.firstItemPadding
import com.google.android.horologist.compose.material.ResponsiveListHeader
import com.google.android.horologist.images.base.paintable.ImageVectorPaintable.Companion.asPaintable
import dev.zzemlyanaya.focuspotion.R
import dev.zzemlyanaya.focuspotion.core.contract.BaseIntent
import dev.zzemlyanaya.focuspotion.core.ui.BaseScreen
import dev.zzemlyanaya.focuspotion.features.presets.model.PresetUiModel
import dev.zzemlyanaya.focuspotion.features.presets.model.PresetsListContract
import dev.zzemlyanaya.focuspotion.features.presets.viewModel.PresetsListViewModel
import dev.zzemlyanaya.focuspotion.uikit.FocusPotionTheme
import dev.zzemlyanaya.focuspotion.uikit.constants.LocalSpacing


@Composable
fun PresetsListScreen(modifier: Modifier = Modifier) {
    val viewModel = hiltViewModel<PresetsListViewModel>()

    BaseScreen(
        modifier = modifier,
        uiFlow = viewModel.screenState,
        sendIntent = viewModel::sendIntent,
        content = { mModifier, uiState, sendEvent ->
            PresetsListScreen(
                mModifier,
                uiState,
                sendEvent
            ) },
    )
}

@Composable
private fun PresetsListScreen(
    modifier: Modifier = Modifier,
    uiState: PresetsListContract.UiState,
    sendIntent: (BaseIntent) -> Unit,
) {
    val columnState = rememberResponsiveColumnState(
        contentPadding = ScalingLazyColumnDefaults.padding(
            first = ItemType.Text,
            last = ItemType.Chip
        )
    )

    ScreenScaffold(
        modifier = modifier.fillMaxSize(),
        scrollState = columnState
    ) {
        ScalingLazyColumn(columnState = columnState) {
            item {
                ResponsiveListHeader(contentPadding = firstItemPadding()) {
                    Text(text = stringResource(R.string.potions))
                }
            }

            if (uiState.isEmptyMessageVisible) {
                item {
                    Text(
                        textAlign = TextAlign.Center,
                        text = stringResource(R.string.no_presets)
                    )
                }
            } else {
                items(uiState.presets) {
                    PresetCard(it) { name -> sendIntent(PresetsListContract.Intent.PresetClick(name)) }
                }
            }

            item {
                Chip(
                    modifier = Modifier.padding(top = LocalSpacing.current.xxLarge),
                    label = stringResource(R.string.btn_create_new),
                    onClick = { sendIntent(PresetsListContract.Intent.CreateNew) }
                )
            }
        }
    }
}


@Composable
private fun PresetCard(
    presetUiModel: PresetUiModel,
    onClick: (String) -> Unit
) {
    Chip(
        label = presetUiModel.name,
        onClick = { onClick(presetUiModel.name) },
        icon = presetUiModel.icon.asPaintable(),
        colors = ChipDefaults.secondaryChipColors(iconColor = MaterialTheme.colors.primary)
    )
}

@Preview(
    device = WearDevices.SMALL_ROUND,
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_WATCH
)
@Composable
fun PresetsListPreview() {
    FocusPotionTheme {
        PresetsListScreen(uiState = PresetsListContract.UiState(
            presets = listOf(
                PresetUiModel("Name", Icons.Filled.Create),
                PresetUiModel("Name", Icons.Filled.Favorite),
                PresetUiModel("Name", Icons.Filled.DateRange),
                PresetUiModel("Name", Icons.Filled.Star),
                PresetUiModel("Name", Icons.Filled.Edit),
                PresetUiModel("Name", Icons.Filled.Call),
                PresetUiModel("Name", Icons.Filled.Person),
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
fun PresetsListPreviewEmpty() {
    FocusPotionTheme {
        PresetsListScreen(uiState = PresetsListContract.UiState()) { }
    }
}