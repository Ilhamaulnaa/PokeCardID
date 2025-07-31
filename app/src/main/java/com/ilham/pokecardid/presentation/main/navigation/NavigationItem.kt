package com.ilham.pokecardid.presentation.main.navigation

import androidx.annotation.DrawableRes

class NavigationItem(

    val title: String,
    val screen: Screen,
    @DrawableRes
    val icon: Int,
    @DrawableRes
    val iconActive: Int

)