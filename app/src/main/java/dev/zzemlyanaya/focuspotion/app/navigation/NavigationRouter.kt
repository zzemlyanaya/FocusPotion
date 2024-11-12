package dev.zzemlyanaya.focuspotion.app.navigation

import dev.zzemlyanaya.focuspotion.app.navigation.MainDirections.back
import dev.zzemlyanaya.focuspotion.app.navigation.MainDirections.default
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class NavigationRouter {

    private val _commands = MutableStateFlow(default)
    val commands: Flow<NavigationCommand> = _commands

    private val resultListeners: HashMap<String, ResultListener> = hashMapOf()

    @Suppress("UNCHECKED_CAST")
    fun <T> getCurrentArgs() = _commands.value.args.firstOrNull() as? T

    fun navigateTo(directions: NavigationCommand) {
        _commands.update { directions }
    }

    fun back() {
        _commands.update { back }
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