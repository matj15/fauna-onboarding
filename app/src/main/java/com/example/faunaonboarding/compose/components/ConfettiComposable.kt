package com.example.faunaonboarding.compose.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.faunaonboarding.R
import nl.dionsegijn.konfetti.compose.KonfettiView
import nl.dionsegijn.konfetti.core.Angle
import nl.dionsegijn.konfetti.core.Party
import nl.dionsegijn.konfetti.core.emitter.Emitter
import nl.dionsegijn.konfetti.core.models.Size
import java.util.concurrent.TimeUnit

@Composable
fun ConfettiComposable(
    modifier: Modifier = Modifier,
    title: Int,
    icon: Int,
    subtitle: String? = null,
    showConfetti: Boolean = true
) {
    val party = Party(
        angle = Angle.TOP,
        colors = listOf(0xf528d4, 0xf56e43, 0x4b3fde, 0xfd0000, 0x4c7b62, 0xffb700, 0x9ebd7f),
        spread = 180,
        size = listOf(Size.MEDIUM),
        emitter = Emitter(duration = 3, TimeUnit.SECONDS)
            .max(300)
    )

    Surface(
        modifier = modifier
            .background(color = colorResource(R.color.green_200))
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(20.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier
                .background(color = colorResource(R.color.green_200))
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier
                    .padding(top = 15.dp)
                    .weight(0.1f)
            ) {
                Icon(
                    painterResource(id = R.drawable.ic_fauna_logo_resource),
                    contentDescription = "FaunaLogo",
                    tint = Color.Unspecified,
                    modifier = Modifier
                        .size(75.dp)
                )
                Text(
                    text = stringResource(title),
                    style = MaterialTheme.typography.headlineLarge,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(top = 5.dp)
                )
            }
            Column(
                Modifier
                    .padding(4.dp)
                    .clip(RoundedCornerShape(15.dp))
                    .background(Color.White)
                    .weight(0.2f)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(10.dp))
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        if (subtitle != null) {
                            Text(
                                text = subtitle,
                                style = MaterialTheme.typography.titleMedium,
                                modifier = Modifier
                                    .padding(bottom = 15.dp)
                            )
                        }
                        Icon(
                            painterResource(id = icon),
                            contentDescription = "Icon",
                            tint = Color.Unspecified,
                            modifier = Modifier
                                .background(MaterialTheme.colorScheme.secondaryContainer, shape = CircleShape)
                                .padding(60.dp)
                                .size(180.dp)
                        )
                    }
                }
            }
        }
    }
    if (showConfetti) {
        KonfettiView(
            modifier = Modifier.fillMaxSize(),
            parties = listOf(party),
        )
    }
}

@Preview
@Composable
fun ConfettiPreview() {
    ConfettiComposable(
        Modifier,
        R.string.animal_created_title,
        R.drawable.ic_animal_created
    )
}