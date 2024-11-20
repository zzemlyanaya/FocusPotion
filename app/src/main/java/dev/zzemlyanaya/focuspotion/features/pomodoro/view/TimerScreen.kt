package dev.zzemlyanaya.focuspotion.features.pomodoro.view

import android.content.res.Configuration
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.wear.compose.material.*
import androidx.wear.tooling.preview.devices.WearDevices
import com.google.android.horologist.compose.layout.ScreenScaffold
import com.google.android.horologist.compose.material.Button
import com.google.android.horologist.compose.material.ButtonSize
import dev.zzemlyanaya.focuspotion.R
import dev.zzemlyanaya.focuspotion.core.contract.BaseIntent
import dev.zzemlyanaya.focuspotion.core.ui.BaseScreen
import dev.zzemlyanaya.focuspotion.features.pomodoro.model.TimerContract
import dev.zzemlyanaya.focuspotion.features.pomodoro.viewModel.TimerViewModel
import dev.zzemlyanaya.focuspotion.uikit.FocusPotionTheme
import dev.zzemlyanaya.focuspotion.uikit.icons.*
import dev.zzemlyanaya.focuspotion.uikit.tokens.LocalSpacing

@Composable
fun TimerScreen(modifier: Modifier = Modifier) {
    val viewModel = hiltViewModel<TimerViewModel>()

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
}

// TODO rethink design, looks too empty for bigger screens
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
            modifier = Modifier.fillMaxSize().padding(all = 1.dp).clearAndSetSemantics {},
            progress = animatedProgress,
            indicatorColor = MaterialTheme.colors.primaryVariant,
            startAngle = 295.5f,
            endAngle = 245.5f,
            strokeWidth = ProgressIndicatorDefaults.FullScreenStrokeWidth
        )

        Column(
            Modifier.fillMaxSize().padding(bottom = LocalSpacing.current.xxLarge),
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

            Text(
                text = stringResource(R.string.sessions_progress, uiState.currentSession, uiState.sessionsCount),
                color = MaterialTheme.colors.secondaryVariant,
                style = MaterialTheme.typography.caption3.copy(fontWeight = FontWeight.Normal)
            )
        }

        Column(
            modifier = Modifier.fillMaxSize().padding(vertical = LocalSpacing.current.xxLarge),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.weight(0.75f))

            TimerActionButtons(isBig, uiState, sendIntent)
        }
    }
}

@Composable
private fun TimerActionButtons(
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