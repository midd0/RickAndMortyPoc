package com.rickandmortypoc.ui.nav

sealed class ScreenNav(val route: String) {
    object Splash : ScreenNav("splash_screen")
    object Home : ScreenNav("main_screen")
    object Detail : ScreenNav("characterdetail_screen")
    object List : ScreenNav("characterlist_screen")
}
