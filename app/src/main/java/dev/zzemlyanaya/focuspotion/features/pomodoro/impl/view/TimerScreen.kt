package dev.zzemlyanaya.focuspotion.features.pomodoro.impl.view

import android.Manifest
import android.content.res.Configuration
import android.os.Build
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.wear.compose.material.*
import androidx.wear.tooling.preview.devices.WearDevices
import com.google.accompanist.permissions.*
import com.google.android.horologist.compose.layout.ScreenScaffold
import com.google.android.horologist.compose.material.Button
import com.google.android.horologist.compose.material.ButtonSize
import dev.zzemlyanaya.focuspotion.R
import dev.zzemlyanaya.focuspotion.core.contract.BaseIntent
import dev.zzemlyanaya.focuspotion.core.ui.BaseScreen
import dev.zzemlyanaya.focuspotion.features.pomodoro.api.service.TimerListener
import dev.zzemlyanaya.focuspotion.features.pomodoro.impl.model.TimerContract
import dev.zzemlyanaya.focuspotion.features.pomodoro.impl.viewModel.TimerViewModel
import dev.zzemlyanaya.focuspotion.uikit.FocusPotionTheme
import dev.zzemlyanaya.focuspotion.uikit.icons.*
import dev.zzemlyanaya.focuspotion.uikit.tokens.LocalSpacing

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun TimerScreen(
    modifier: Modifier = Modifier,
    timerListener: TimerListener?
) {
    val permissionState = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        rememberPermissionState(permission = Manifest.permission.POST_NOTIFICATIONS)
    } else {
        object: PermissionState {
            override val permission = "no_runtime_permission_required"
            override val status = PermissionStatus.Granted
            override fun launchPermissionRequest() { }
        }
    }

    if (permissionState.status == PermissionStatus.Granted) {
        val viewModel = hiltViewModel<TimerViewModel>()
        viewModel.timerListener = timerListener

        BaseScreen(
            modifier = modifier,
            uiFlow = viewModel.screenState,
            sendIntent = viewModel::sendIntent,
            content = { mModifier, uiState, sendEvent ->
                TimerScreen(
                    mModifier,
                    uiState,
                    sendEvent
                )
            }
        )
    } else if (permissionState.status is PermissionStatus.Denied) {
        // TODO after returning screen is unresponsive
        LaunchedEffect(Unit) {
            permissionState.launchPermissionRequest()
        }
    }
}

@Composable
private fun TimerScreen(
    modifier: Modifier,
    uiState: TimerContract.UiState,
    sendIntent: (BaseIntent) -> Unit,
) {
    val isBig by remember { mutableStateOf(false) }

    val animatedProgress by animateFloatAsState(
        targetValue = uiState.progress,
        animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec,
        label = "TimerProgressAnimation"
    )

    ScreenScaffold(
        modifier = modifier.onSizeChanged { it.width >= 225 },
        timeText = { TimeText() }
    ) {

        CircularProgressIndicator(
            modifier = Modifier
                .fillMaxSize()
                .padding(all = 1.dp)
                .clearAndSetSemantics {},
            progress = animatedProgress,
            indicatorColor = MaterialTheme.colors.primaryVariant,
            startAngle = 295.5f,
            endAngle = 245.5f,
            strokeWidth = ProgressIndicatorDefaults.FullScreenStrokeWidth
        )

        if (uiState.isFinished) {
            FinishedTimer()
        } else {
            ActiveTimer(uiState)
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = LocalSpacing.current.xxLarge),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.weight(0.75f))

            if (uiState.isFinished) {
                com.google.android.horologist.compose.material.CompactChip(
                    labelId = R.string.finished_button,
                    onClick = { sendIntent(TimerContract.Intent.FinishedClick) }
                )
            } else {
                TimerControls(isBig, uiState, sendIntent)
            }
        }
    }
}

@Composable
private fun ActiveTimer(uiState: TimerContract.UiState) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(bottom = LocalSpacing.current.xxLarge),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = uiState.stageName,
            color = MaterialTheme.colors.secondary,
            style = MaterialTheme.typography.caption2
        )

        Text(
            text = uiState.timer,
            style = MaterialTheme.typography.display1
        )

        if (uiState.isStarted) {
            val text = if (uiState.isLongBreak) {
                stringResource(R.string.long_rest_subtitle)
            } else {
                stringResource(R.string.sessions_progress, uiState.currentSession, uiState.sessionsCount)
            }

            Text(
                text = text,
                color = MaterialTheme.colors.secondaryVariant,
                style = MaterialTheme.typography.caption3.copy(fontWeight = FontWeight.Normal)
            )
        }
    }
}

@Composable
private fun FinishedTimer() {
    Column(
        Modifier
            .fillMaxSize()
            .padding(LocalSpacing.current.xxLarge),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.finished_title),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.body1
        )
    }
}

@Composable
private fun TimerControls(
    isBig: Boolean,
    uiState: TimerContract.UiState,
    sendIntent: (BaseIntent) -> Unit
) {

    val buttonSize = if (isBig) ButtonSize.Small else ButtonSize.Custom(16.dp, 40.dp)

    Row(
        horizontalArrangement = Arrangement.spacedBy(LocalSpacing.current.small)
    ) {
        if (uiState.isStarted.not()) {
            Button(
                imageVector = AppIcons.Play,
                contentDescription = AppIcons.Play.name,
                buttonSize = buttonSize,
                onClick = { sendIntent.invoke(TimerContract.Intent.StartClick) }
            )
        }
        else if (uiState.isPaused) {
            Button(
                imageVector = AppIcons.Stop,
                contentDescription = AppIcons.Stop.name,
                buttonSize = buttonSize,
                colors = ButtonDefaults.secondaryButtonColors(),
                onClick = { sendIntent.invoke(TimerContract.Intent.StopClick) }
            )

            Button(
                imageVector = AppIcons.Play,
                contentDescription = AppIcons.Play.name,
                buttonSize = buttonSize,
                onClick = { sendIntent.invoke(TimerContract.Intent.StartClick) }
            )
        } else {
            Button(
                imageVector = AppIcons.Stop,
                contentDescription = AppIcons.Stop.name,
                buttonSize = buttonSize,
                colors = ButtonDefaults.secondaryButtonColors(),
                onClick = { sendIntent.invoke(TimerContract.Intent.StopClick) }
            )
            Button(
                imageVector = AppIcons.Pause,
                contentDescription = AppIcons.Pause.name,
                buttonSize = buttonSize,
                onClick = { sendIntent.invoke(TimerContract.Intent.PauseClick) }
            )
        }
    }
}

@Preview(
    device = WearDevices.SMALL_ROUND, heightDp = 185, widthDp = 185,
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_WATCH
)
@Composable
private fun TimerScreenPreview() {
    FocusPotionTheme {
        TimerScreen(
            Modifier,
            TimerContract.UiState(
                progress = 0.5f,
                timer = "03:25",
                sessionsCount = 4,
                currentSession = 2,
                stageName = "Focus",
                isStarted = true,
                isPaused = false
            )
        ) { }
    }
}

@Preview(
    device = WearDevices.SMALL_ROUND, heightDp = 185, widthDp = 185,
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_WATCH
)
@Composable
fun FinishPreview() {
    FocusPotionTheme {
        TimerScreen(
            Modifier,
            TimerContract.UiState(
                progress = 1f,
                timer = "00:00",
                sessionsCount = 4,
                currentSession = 4,
                stageName = "Rest",
                isStarted = true,
                isPaused = false,
                isFinished = true
            )
        ) { }
    }
}