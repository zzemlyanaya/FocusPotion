package dev.zzemlyanaya.focuspotion.features.presets.viewModel

import dagger.hilt.android.lifecycle.HiltViewModel
import dev.zzemlyanaya.focuspotion.app.navigation.NavigationRouter
import dev.zzemlyanaya.focuspotion.core.contract.BaseIntent
import dev.zzemlyanaya.focuspotion.core.viewModel.BaseViewModel
import dev.zzemlyanaya.focuspotion.features.presets.model.NumberPickerArgs
import dev.zzemlyanaya.focuspotion.features.presets.model.contract.NumberPickerContract
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class NumberPickerViewModel @Inject constructor(
    private val router: NavigationRouter
) : BaseViewModel<NumberPickerContract.UiState, NumberPickerContract.Intent>(router) {

    override fun getInitialState() = NumberPickerContract.UiState()

    init {
        router.getCurrentArgs<NumberPickerArgs>()?.let { args ->
            updateScreenState { it.copy(
                title = args.title,
                range = (1..args.max).map { it.toString() },
                scrollToCurrent = args.current-1
            ) }
        }
    }

    override fun handleIntent(intent: BaseIntent) {
        when (intent) {
            is NumberPickerContract.Intent.CancelClick -> router.back()
            is NumberPickerContract.Intent.SaveClick -> onNumberSelected(intent.current)
            else -> super.handleIntent(intent)
        }
    }

    private fun onNumberSelected(id: Int) {
        router.sendResult(NUMBER_SELECTED_RESULT, id+1)
        router.back()
    }

    companion object {
        val NUMBER_SELECTED_RESULT = UUID.randomUUID().toString()
    }
}