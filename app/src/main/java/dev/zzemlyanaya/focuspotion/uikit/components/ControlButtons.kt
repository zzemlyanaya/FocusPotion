package dev.zzemlyanaya.focuspotion.uikit.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.wear.compose.material.ChipDefaults
import com.google.android.horologist.compose.material.ResponsiveButton
import com.google.android.horologist.compose.material.responsiveButtonWidth
import dev.zzemlyanaya.focuspotion.uikit.tokens.LocalSpacing

@Composable
fun ControlButtons(
    onCancel: () -> Unit,
    onSave: () -> Unit,
    modifier: Modifier = Modifier
) {
    val (spacedBy, buttonWidth) = responsiveButtonWidth(2)

    Row(
        modifier = modifier.fillMaxWidth().padding(horizontal = LocalSpacing.current.small),
        horizontalArrangement = Arrangement.spacedBy(spacedBy, Alignment.CenterHorizontally),
        verticalAlignment = Alignment.CenterVertically
    ) {
        ResponsiveButton(
            icon = Icons.Default.Close,
            contentDescription = Icons.Default.Close.name,
            onClick = onCancel,
            buttonWidth = buttonWidth,
            colors = ChipDefaults.secondaryChipColors(),
        )

        ResponsiveButton(
            icon = Icons.Default.Check,
            contentDescription = Icons.Default.Check.name,
            onClick = onSave,
            buttonWidth = buttonWidth
        )
    }
}