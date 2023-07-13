package com.example.faunaonboarding.ui.theme

import androidx.compose.ui.graphics.Color

object primaryColors {
    val BASE = Color(0xFF3D6F5C)
    val C96 = Color(0xFFF7F9F8)
    val C90 = Color(0xFFECF1EF)
    val C80 = Color(0xFFD8E2DE)
    val C40 = Color(0xFF8BA99D)
    val C20 = Color(0xFF18392D)
}

object secondaryColors {
    val BASE = Color(0xFFD89F72)
    val C95 = Color(0xFFF9F9F9)
    val C90 = Color(0xFFF6F1EC)
    val C80 = Color(0xFFF4EBE4)
    val C40 = Color(0xFFE7D3C3)
    val C20 = Color(0xFF76573E)
}

object negativeColors {
    val BASE = Color(0xFFC01048)
    val LIGHT = Color(0xFFFFF1F3)
    val DARK = Color(0xFFA2002A)
}

object positiveColors {
    val BASE = Color(0xFF2F984D)
    val LIGHT = Color(0xFFE1F6EC)
    val DARK = Color(0xFF006D45)
}

object warningColors {
    val BASE = Color(0xFFEF8D32)
    val LIGHT = Color(0xFFFFEFD3)
    val DARK = Color(0xFFB1530E)
}

object infoColors {
    val BASE = Color(0xFF4867D3)
    val LIGHT = Color(0xFFE7EDFE)
    val DARK = Color(0xFF2B4395)
}



// ------- MATERIAL3 colors -------

// TODO adapt colors to the ones from material3 branch
// primary
val md_theme_light_primary = primaryColors.BASE
val md_theme_light_onPrimary = Color(0xFFFFFFFF)
val md_theme_light_primaryContainer = primaryColors.C90
val md_theme_light_onPrimaryContainer = primaryColors.C20
// secondary
val md_theme_light_secondaryContainer = secondaryColors.C90
val md_theme_light_secondary = secondaryColors.BASE
val md_theme_light_onSecondary = secondaryColors.C80
val md_theme_light_onSecondaryContainer = secondaryColors.C20
val md_theme_light_tertiary = infoColors.BASE
val md_theme_light_onTertiary = Color(0xFFFFFFFF)
val md_theme_light_tertiaryContainer = infoColors.LIGHT
val md_theme_light_onTertiaryContainer = infoColors.DARK
val md_theme_light_error = negativeColors.BASE
val md_theme_light_errorContainer = negativeColors.LIGHT
val md_theme_light_onError = Color(0xFFFFFFFF)
val md_theme_light_onErrorContainer = negativeColors.DARK
val md_theme_light_background = Color(0xFFFAFCFF)
val md_theme_light_onBackground = Color(0xFF001F2A)
val md_theme_light_surface = Color(0xFFFAFCFF)
val md_theme_light_onSurface = Color(0xFF001F2A)
val md_theme_light_surfaceVariant = secondaryColors.C95
val md_theme_light_onSurfaceVariant = Color(0xFF404944)
val md_theme_light_outline = Color(0xFF707974)
val md_theme_light_inverseOnSurface = Color(0xFFE1F4FF)
val md_theme_light_inverseSurface = Color(0xFF003547)
val md_theme_light_inversePrimary = primaryColors.C96
val md_theme_light_surfaceTint = Color(0xFF006C51)
val md_theme_light_outlineVariant = Color(0xFFBFC9C2)
val md_theme_light_scrim = Color(0xFF000000)

val md_theme_dark_primary = Color(0xFF66DBB2)
val md_theme_dark_onPrimary = Color(0xFF003829)
val md_theme_dark_primaryContainer = Color(0xFF00513C)
val md_theme_dark_onPrimaryContainer = Color(0xFF83F8CD)
val md_theme_dark_secondary = Color(0xFFFFB77E)
val md_theme_dark_onSecondary = Color(0xFF4D2600)
val md_theme_dark_secondaryContainer = Color(0xFF6E3900)
val md_theme_dark_onSecondaryContainer = Color(0xFFFFDCC3)
val md_theme_dark_tertiary = Color(0xFFA7CCE0)
val md_theme_dark_onTertiary = Color(0xFF0A3445)
val md_theme_dark_tertiaryContainer = Color(0xFF264B5C)
val md_theme_dark_onTertiaryContainer = Color(0xFFC2E8FD)
val md_theme_dark_error = Color(0xFFFFB4AB)
val md_theme_dark_errorContainer = Color(0xFF93000A)
val md_theme_dark_onError = Color(0xFF690005)
val md_theme_dark_onErrorContainer = Color(0xFFFFDAD6)
val md_theme_dark_background = Color(0xFF001F2A)
val md_theme_dark_onBackground = Color(0xFFBFE9FF)
val md_theme_dark_surface = Color(0xFF001F2A)
val md_theme_dark_onSurface = Color(0xFFBFE9FF)
val md_theme_dark_surfaceVariant = Color(0xFF404944)
val md_theme_dark_onSurfaceVariant = Color(0xFFBFC9C2)
val md_theme_dark_outline = Color(0xFF89938D)
val md_theme_dark_inverseOnSurface = Color(0xFF001F2A)
val md_theme_dark_inverseSurface = Color(0xFFBFE9FF)
val md_theme_dark_inversePrimary = Color(0xFF006C51)
val md_theme_dark_surfaceTint = Color(0xFF66DBB2)
val md_theme_dark_outlineVariant = Color(0xFF404944)
val md_theme_dark_scrim = Color(0xFF000000)