package ru.webanimal.test52_compose01

internal sealed class MainScreenState {
    object Default: MainScreenState()
    object Loading: MainScreenState()
    data class Content(val text: String): MainScreenState()
    object Empty: MainScreenState()
    object Error: MainScreenState()
}