package com.example.faunaonboarding.onboarding.userCreate

import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import com.example.faunaonboarding.R

@Composable
fun PrivacyPolicyText() {
    val annotatedLinkString: AnnotatedString = buildAnnotatedString {
        val privacyPolicy = stringResource(id = R.string.on_board_new_user_privacy_policy)
        val text = listOf(
            stringResource(id = R.string.on_board_new_user_accept),
            privacyPolicy
        ).joinToString(separator = " ")
        val startIndex = text.indexOf(privacyPolicy)
        val endIndex = startIndex + privacyPolicy.length
        append(text)
        addStyle(
            style = SpanStyle(
                color = MaterialTheme.colorScheme.primary,
                textDecoration = TextDecoration.Underline,
                fontWeight = FontWeight.Bold
            ),
            start = startIndex, end = endIndex
        )

        addStringAnnotation(
            tag = "URL",
            annotation = stringResource(id = R.string.on_board_new_user_privacy_policy_website),
            start = startIndex,
            end = endIndex
        )
    }

    val uriHandler = LocalUriHandler.current

    ClickableText(
        text = annotatedLinkString,
        onClick = {
            annotatedLinkString
                .getStringAnnotations("URL", it, it)
                .firstOrNull()?.let { stringAnnotation ->
                    uriHandler.openUri(stringAnnotation.item)
                }
        },
        style = MaterialTheme.typography.bodyMedium
    )
}