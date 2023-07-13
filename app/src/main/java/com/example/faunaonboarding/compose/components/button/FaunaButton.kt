package com.example.faunaonboarding.compose.components.button

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.faunaonboarding.R
import com.example.faunaonboarding.ui.theme.FaunaTheme
import com.example.faunaonboarding.ui.theme.Space

@Composable
fun FaunaButton(
    modifier: Modifier = Modifier,
    backgroundColor: Color = MaterialTheme.colorScheme.primary,
    contentColor: Color = MaterialTheme.colorScheme.onPrimary,
    text: String,
    iconLeft: Int? = null,
    iconRight: Int? = null,
    enabled: Boolean = true,
    onClick: () -> Unit = {},
) {
    val disabledContentColor = MaterialTheme.colorScheme.onPrimary.copy(0.3F)
    val isPressed by remember { mutableStateOf(false) }

    Button(
        modifier = modifier,
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = if (enabled) {
                if (isPressed) MaterialTheme.colorScheme.onPrimaryContainer else backgroundColor
            } else backgroundColor.copy(0.2F),
            disabledContentColor = disabledContentColor
        ),
        shape = CircleShape,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(Space.SPACE8)
        ) {
            if (iconLeft != null) {
                LeftIconButtonContent(
                    contentColor = contentColor,
                    disabledContentColor = disabledContentColor,
                    text = text,
                    icon = iconLeft,
                    enabled = enabled
                )
            } else if (iconRight != null) {
                RightIconButtonContent(
                    contentColor = contentColor,
                    disabledContentColor = disabledContentColor,
                    text = text,
                    icon = iconRight,
                    enabled = enabled
                )
            } else
                TextButtonContent(
                    contentColor = contentColor,
                    disabledContentColor = disabledContentColor,
                    text = text,
                    enabled = enabled
                )
        }
    }
}



@Preview
@Composable
fun ButtonPreview() {
    FaunaTheme {
        FaunaButton(
            onClick = {},
            text = stringResource(id = R.string.button_enabled)
        )
    }
}

@Preview
@Composable
fun ButtonWithLeftIconPreview() {
    FaunaTheme {
        FaunaButton(
            iconLeft = R.drawable.plus,
            onClick = {},
            text = stringResource(id = R.string.button_enabled)
        )
    }
}

@Preview
@Composable
fun ButtonDisabledPreview() {
    FaunaTheme {
        FaunaButton(
            onClick = {},
            text = stringResource(id = R.string.button_disabled),
            enabled = false,
            iconRight = R.drawable.plus
        )
    }
}



