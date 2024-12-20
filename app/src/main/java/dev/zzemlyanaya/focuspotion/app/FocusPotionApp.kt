package dev.zzemlyanaya.focuspotion.app

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.flowWithLifecycle
import androidx.navigation.NavHostController
import androidx.wear.compose.foundation.rememberSwipeToDismissBoxState
import androidx.wear.compose.navigation.*
import com.google.android.horologist.compose.layout.AppScaffold
import dev.zzemlyanaya.focuspotion.app.navigation.*
import dev.zzemlyanaya.focuspotion.features.mainScreen.view.MainScreen
import dev.zzemlyanaya.focuspotion.features.pomodoro.api.service.TimerListener
import dev.zzemlyanaya.focuspotion.features.pomodoro.impl.view.TimerScreen
import dev.zzemlyanaya.focuspotion.features.presets.impl.view.*
import dev.zzemlyanaya.focuspotion.uikit.FocusPotionTheme
import kotlinx.coroutines.flow.collectLatest


@Composable
fun FocusPotionApp(
    navigationRouter: NavigationRouter,
    timerListener: () -> TimerListener?,
) {
    FocusPotionTheme {
        AppScaffold {
            val navController = rememberSwipeDismissableNavController()
            val lifecycle = LocalLifecycleOwner.current.lifecycle

            LaunchedEffect(true) {
                navigationRouter.commands
                    .flowWithLifecycle(lifecycle, Lifecycle.State.CREATED)
                    .collectLatest { nextCommand ->
                        if (nextCommand.destination == MainDirections.back.destination) {
                            navController.popBackStack()
                        } else if (nextCommand.destination.isNotEmpty()) {
                            navController.navigate(nextCommand.destination) { launchSingleTop = true }
                        }
                    }
            }

            AppNavGraph(navController, timerListener, onBack = navigationRouter::back)
        }
    }
}

@Composable
fun AppNavGraph(
    navController: NavHostController,
    timerListener: () -> TimerListener?,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    startDestination: String = Destination.Main.route,
) {
    val swipeBoxState = rememberSwipeToDismissBoxState()
    val navState = rememberSwipeDismissableNavHostState(swipeBoxState)

    SwipeDismissableNavHost(
        navController = navController,
        startDestination = startDestination,
        state = navState,
        modifier = modifier,
    ) {

        composable(Destination.Main.route) {
            BackHandler(true) {}
            MainScreen(modifier = modifier)
        }

        composable(Destination.PresetsList.route) {
            BackHandler(true, onBack)
            PresetsListScreen(modifier = modifier)
        }

        composable(Destination.PresetEdit.route) {
            BackHandler(true, onBack)
            NewPresetScreen(modifier = modifier)
        }

        composable(Destination.IconPicker.route) {
            BackHandler(true, onBack)
            IconPickerScreen(modifier = modifier)
        }

        composable(Destination.NumberPicker.route) {
            BackHandler(true, onBack)
            NumberPickerScreen(modifier = modifier)
        }

        composable(Destination.Timer.route) {
            BackHandler(true, onBack)
            TimerScreen(modifier = modifier, timerListener = timerListener())
        }
    }
}