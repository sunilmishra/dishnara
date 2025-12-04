package com.codewithmisu.dishnara

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Dishnara",
    ) {
        App(getDatabaseBuilder())
    }
}