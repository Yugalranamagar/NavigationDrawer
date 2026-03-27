package com.example.navigationdrawer

sealed class Screens(val screen: String){
    data object Home: Screens(screen = "Home")
    data object Cart: Screens(screen = "Cart")
    data object Favorite: Screens(screen = "Favorite")
    data object Calendar: Screens(screen = "Calendar")
    data object Bin: Screens(screen = "Bin")

}