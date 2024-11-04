package dev.zzemlyanaya.focuspotion.core.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import dev.zzemlyanaya.focuspotion.core.contract.BaseIntent
import dev.zzemlyanaya.focuspotion.core.contract.ScreenUiState
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@Composable
fun <S: Any> BaseScreen(
    modifier: Modifier,
    uiFlow: StateFlow<ScreenUiState<S>>,
    sendIntent: suspend (BaseIntent) -> Unit,
    content: @Composable (Modifier, S, (BaseIntent) -> Unit) -> Unit
) {
    val scope = rememberCoroutineScope()

    fun sendIntentInternal(intent: BaseIntent) {
        scope.launch { sendIntent(intent) }
    }

    val uiState by uiFlow.collectAsState()
    content(modifier, uiState.state, ::sendIntentInternal)
}