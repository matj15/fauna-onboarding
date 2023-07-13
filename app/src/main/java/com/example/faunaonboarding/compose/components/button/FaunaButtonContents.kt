package com.example.faunaonboarding.compose.components.button

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.faunaonboarding.R
import com.example.faunaonboarding.ui.theme.Space

@Composable
fun TextButtonContent(
    contentColor: Color,
    disabledContentColor: Color,
    text: String,
    enabled: Boolean
) {
    Text(
        text,
        style = MaterialTheme.typography.labelLarge,
        color = if (enabled) contentColor else disabledContentColor
    )
}

@Composable
fun LeftIconButtonContent(
    contentColor: Color,
    disabledContentColor: Color,
    text: String,
    icon: Int?,
    enabled: Boolean
) {
    if (icon != null) {
        Image(
            painter = painterResource(id = icon),
            contentDescription = stringResource(id = getIconDescription(icon)),
            colorFilter = ColorFilter.tint(if (enabled) contentColor else disabledContentColor)
        )
    }
    Text(
        text,
        style = MaterialTheme.typography.labelLarge,
        color = if (enabled) contentColor else disabledContentColor,
        modifier = Modifier.padding(start = Space.SPACE8),
    )
}

@Composable
fun RightIconButtonContent(
    contentColor: Color,
    disabledContentColor: Color,
    text: String,
    icon: Int?,
    enabled: Boolean
) {
    Text(
        text,
        style = MaterialTheme.typography.labelLarge,
        color = if (enabled) contentColor else disabledContentColor,
        modifier = Modifier.padding(end = Space.SPACE8),
    )
    if (icon != null) {
        Image(
            painter = painterResource(id = icon),
            contentDescription = stringResource(id = getIconDescription(icon)),
            colorFilter = ColorFilter.tint(if (enabled) contentColor else disabledContentColor)
        )
    }
}

// TODO is this how it should be done with icon descriptions?
fun getIconDescription(icon: Int): Int {
    return when (icon) {
        R.drawable.plus -> {
            R.string.add_button_icon_description
        }
        else -> R.string.add_button_icon_description
    }
}