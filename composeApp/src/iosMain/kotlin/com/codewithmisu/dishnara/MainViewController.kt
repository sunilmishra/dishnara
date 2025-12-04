package com.codewithmisu.dishnara

import androidx.compose.runtime.remember
import androidx.compose.ui.window.ComposeUIViewController

fun MainViewController() = ComposeUIViewController {
    val databaseBuilder = remember { getDatabaseBuilder() }
    App(databaseBuilder)
}