package dev.zzemlyanaya.focuspotion.core.viewModel

import androidx.annotation.CallSuper
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.zzemlyanaya.focuspotion.app.navigation.NavigationRouter
import dev.zzemlyanaya.focuspotion.core.contract.BaseIntent
import dev.zzemlyanaya.focuspotion.core.contract.ScreenUiState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus

abstract class BaseViewModel<UiState, UiIntent : BaseIntent>(
    private val router: NavigationRouter
): ViewModel() {

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        handleException(exception)
    }
    protected val ioScope = viewModelScope + Dispatchers.IO + coroutineExceptionHandler

    private val intentChannel = Channel<BaseIntent>(Channel.UNLIMITED)

    private val _screenState = MutableStateFlow(ScreenUiState.Data(getInitialState()))
    open val screenState: StateFlow<ScreenUiState<UiState>> = _screenState

    abstract fun getInitialState(): UiState

    init {
        observeIntent()
    }

    suspend fun sendIntent(intent: BaseIntent) {
        intentChannel.send(intent)
    }

    protected fun observeIntent() {
        viewModelScope.launch {
            intentChannel.consumeAsFlow().collect { handleIntent(it) }
        }
    }

    @CallSuper
    protected open fun handleIntent(intent: BaseIntent) {
        if (intent is BaseIntent.Back) back()
    }

    protected fun updateScreenState(reducer: (UiState) -> UiState) {
        reducer(_screenState.value.state).let { _screenState.value = ScreenUiState.Data(it) }
    }

    protected fun getScreenState(): UiState = _screenState.value.state

    protected open fun handleException(e: Throwable) {
        e.printStackTrace()
    }

    protected open fun back() {
        router.back()
    }
}