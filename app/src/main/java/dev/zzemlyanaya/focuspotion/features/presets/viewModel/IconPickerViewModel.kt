package dev.zzemlyanaya.focuspotion.features.presets.viewModel

import dagger.hilt.android.lifecycle.HiltViewModel
import dev.zzemlyanaya.focuspotion.app.navigation.NavigationRouter
import dev.zzemlyanaya.focuspotion.core.contract.BaseIntent
import dev.zzemlyanaya.focuspotion.core.viewModel.BaseViewModel
import dev.zzemlyanaya.focuspotion.features.presets.model.IconPickerArgs
import dev.zzemlyanaya.focuspotion.features.presets.model.contract.IconPickerContract
import dev.zzemlyanaya.focuspotion.uikit.icons.AppIcons
import dev.zzemlyanaya.focuspotion.uikit.icons.all
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class IconPickerViewModel @Inject constructor(
    private val router: NavigationRouter
) : BaseViewModel<IconPickerContract.UiState, IconPickerContract.Intent>(router) {

    override fun getInitialState() = IconPickerContract.UiState(AppIcons.all())

    init {
        router.getCurrentArgs<IconPickerArgs>()?.let { args ->
            updateScreenState { it.copy(scrollToCurrent = args.currentId) }
        }
    }

    override fun handleIntent(intent: BaseIntent) {
        when (intent) {
            is IconPickerContract.Intent.CancelClick -> router.back()
            is IconPickerContract.Intent.SaveClick -> onIconSelected(intent.currentId)
            else -> super.handleIntent(intent)
        }
    }

    private fun onIconSelected(id: Int) {
        router.sendResult(ICON_SELECTED_RESULT, id)
        router.back()
    }

    companion object {
        val ICON_SELECTED_RESULT = UUID.randomUUID().toString()
    }
}