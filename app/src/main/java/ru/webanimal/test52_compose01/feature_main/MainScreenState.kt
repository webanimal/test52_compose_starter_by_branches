package ru.webanimal.test52_compose01.feature_main

internal sealed class MainScreenState {

    data class Content(val title: String, val data: List<String>): MainScreenState()
    object Default: MainScreenState()
    object Empty: MainScreenState()
    object Error: MainScreenState()
    object Loading: MainScreenState()
}