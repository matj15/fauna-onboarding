package com.example.faunaonboarding.compose.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.example.faunaonboarding.R
import com.example.faunaonboarding.ui.theme.FaunaTheme
import com.example.faunaonboarding.ui.theme.Space

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StepCounterTopAppBarComposable(
    screenTitle: String,
    numberOfSteps: Int,
    stepNumber: Int,
    onBackClick: () -> Unit,
    onCloseClick: () -> Unit
) {
    TopAppBar(
        modifier = Modifier.fillMaxWidth(),
        navigationIcon = {
            IconButton(
                content = {
                    Icon(
                        painter = painterResource(id = R.drawable.left),
                        contentDescription = ""
                    )
                },
                onClick = onBackClick,
                colors = IconButtonDefaults.iconButtonColors(
                    contentColor = MaterialTheme.colorScheme.primary
                )
            )
        },
        actions = {
            IconButton(
                content = {
                    Icon(
                        painter = painterResource(id = R.drawable.close),
                        contentDescription = stringResource(
                            id = R.string.on_board_new_user_close_icon_description
                        )
                    )
                },
                onClick = { onCloseClick() },
                colors = IconButtonDefaults.iconButtonColors(
                    contentColor = MaterialTheme.colorScheme.primary
                )
            )
        },
        title = {
            Column {
                Text(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .fillMaxWidth()
                        .padding(bottom = Space.SPACE8),
                    text = screenTitle,
                    style = MaterialTheme.typography.headlineSmall,
                    textAlign = TextAlign.Center
                )
                StepsProgressBar(
                    modifier = Modifier
                        .fillMaxWidth(0.19F)
                        .align(Alignment.CenterHorizontally),
                    numberOfSteps = numberOfSteps,
                    currentStep = stepNumber
                )
            }

        }, colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.onSecondary
        )
    )
}

@Preview(showBackground = true, device = Devices.PHONE)
@Composable
fun StepCounterTopAppBarPreview() {
    FaunaTheme {
        StepCounterTopAppBarComposable(
            screenTitle = "Info om dig", numberOfSteps = 3, stepNumber = 0, {}, {}
        )
    }
}