package dev.zzemlyanaya.focuspotion.core.viewModel

import androidx.annotation.CallSuper
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.zzemlyanaya.focuspotion.app.navigation.NavigationRouter
import dev.zzemlyanaya.focuspotion.core.contract.BaseIntent
import dev.zzemlyanaya.focuspotion.core.contract.ScreenUiState
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*

abstract class BaseViewModel<UiState, UiIntent : BaseIntent>(
    private val router: NavigationRouter
): ViewModel() {

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        exception.printStackTrace()
        handleException(exception)
    }
    private val supervisorJob = SupervisorJob()
    protected val ioScope = viewModelScope + Dispatchers.IO + supervisorJob + coroutineExceptionHandler

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

    protected open fun handleException(e: Throwable) {}

    protected open fun back() {
        router.back()
    }
}