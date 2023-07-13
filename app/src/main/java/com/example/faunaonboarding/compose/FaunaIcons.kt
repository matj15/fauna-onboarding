package com.example.faunaonboarding.compose

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.faunaonboarding.R

object FaunaIcons {
    val faunaIcon = R.drawable.fauna_logo_small
//    val vaccinationsSelected = R.drawable.menu_vaccinations_active
//    val vaccinationsUnselected = R.drawable.menu_vaccinations_inactive
//    val householdSelected = R.drawable.menu_household_active
//    val householdUnselected = R.drawable.menu_household_inactive
    val homeSelected = R.drawable.menu_home_active
    val homeUnselected = R.drawable.menu_home_inactive
//    val favorite = R.drawable.ic_favorite
//    val search = R.drawable.ic_search
}

/**
 * A sealed class to make dealing with [ImageVector] and [DrawableRes] icons easier.
 */
sealed class Icon {
    data class ImageVectorIcon(val imageVector: ImageVector) : Icon()
    data class DrawableResourceIcon(@DrawableRes val id: Int) : Icon()
}
