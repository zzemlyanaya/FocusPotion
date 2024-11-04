package dev.zzemlyanaya.focuspotion.app

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.flowWithLifecycle
import androidx.navigation.NavHostController
import androidx.wear.compose.navigation.SwipeDismissableNavHost
import androidx.wear.compose.navigation.composable
import androidx.wear.compose.navigation.rememberSwipeDismissableNavController
import com.google.android.horologist.compose.layout.AppScaffold
import dev.zzemlyanaya.focuspotion.app.navigation.Destination
import dev.zzemlyanaya.focuspotion.app.navigation.MainDirections
import dev.zzemlyanaya.focuspotion.app.navigation.NavigationRouter
import dev.zzemlyanaya.focuspotion.features.mainScreen.view.MainScreen
import dev.zzemlyanaya.focuspotion.features.presets.view.PresetsListScreen
import dev.zzemlyanaya.focuspotion.uikit.FocusPotionTheme
import kotlinx.coroutines.flow.collectLatest


@Composable
fun FocusPotionApp(navigationRouter: NavigationRouter) {
    FocusPotionTheme {
        AppScaffold {
            val navController = rememberSwipeDismissableNavController()
            val lifecycle = LocalLifecycleOwner.current.lifecycle

            LaunchedEffect(true) {
                navigationRouter.commands
                    .flowWithLifecycle(lifecycle)
                    .collectLatest { nextCommand ->
                        if (nextCommand.destination == MainDirections.back.destination) {
                            navController.popBackStack()
                        } else if (nextCommand.destination.isNotEmpty()) {
                            navController.navigate(nextCommand.destination) { launchSingleTop = true }
                        }
                    }
            }

            AppNavGraph(navController, onBack = navigationRouter::back)
        }
    }
}

@Composable
fun AppNavGraph(
    navController: NavHostController,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    startDestination: String = Destination.Main.route,
) {
    SwipeDismissableNavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
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
            // PresetEditScreen(modifier = modifier)
        }

        composable(Destination.Timer.route) {
            BackHandler(true, onBack)
            // TimerScreen(modifier = modifier)
        }
    }
}