package com.material3.rally.ui.components


import androidx.compose.foundation.Indication
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.material3.rally.ui.components.common.RallyDivider


@Composable
fun RallyAlertDialog(
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit,
    bodyText: String,
    buttonLabel: String
) {
    AlertDialog(
        modifier = modifier,
        onDismissRequest = onDismiss,
        text = {
            Text(text = bodyText, style = MaterialTheme.typography.bodySmall)
        },
        dismissButton = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            )
            {
                RallyDivider()
                TextButton(
                    modifier = Modifier
                        .fillMaxWidth(),
                    shape = MaterialTheme.shapes.small,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.surface,
                        contentColor = MaterialTheme.colorScheme.onSurface
                    ),
                    onClick = onDismiss
                ) {
                    Text(text = buttonLabel)
                }
            }
        },
        confirmButton = {}
    )

}
