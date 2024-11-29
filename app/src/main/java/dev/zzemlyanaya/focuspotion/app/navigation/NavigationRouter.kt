package dev.zzemlyanaya.focuspotion.app.navigation

import dev.zzemlyanaya.focuspotion.app.navigation.MainDirections.back
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class NavigationRouter {

    private val scope = MainScope()

    private val _commands = MutableSharedFlow<NavigationCommand>(replay = 1)
    val commands: Flow<NavigationCommand> = _commands

    private val resultListeners: HashMap<String, ResultListener> = hashMapOf()

    @Suppress("UNCHECKED_CAST")
    fun <T> getCurrentArgs() = _commands.replayCache.first().args.firstOrNull() as? T

    fun navigateTo(directions: NavigationCommand) {
        scope.launch { _commands.emit(directions) }
    }

    fun back() {
        scope.launch { _commands.emit(back) }
    }

    @Suppress("UNCHECKED_CAST")
    fun <T> addResultListener(id: String, listener: (T) -> Unit) {
        resultListeners[id] = object : ResultListener {
            override fun onResult(result: Any) {
                (result as? T)?.let { listener(it) }
            }
        }
    }

    fun removeResultListener(id: String) {
        resultListeners.remove(id)
    }

    fun sendResult(id: String, result: Any) {
        resultListeners[id]?.onResult(result)
    }

    fun sendResultAll(result: Any) {
        resultListeners.values.forEach { it.onResult(result) }
    }
}


interface ResultListener {
    fun onResult(result: Any)
}