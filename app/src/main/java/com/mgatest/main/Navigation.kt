package com.mgatest.main

sealed class Navigation(val route: String) {

    data object Home : Navigation("home")


}