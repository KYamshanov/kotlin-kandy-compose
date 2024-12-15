package ru.kyamshanov.sample

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "kotlin-kandy-compose",
    ) {
        App()
    }
}