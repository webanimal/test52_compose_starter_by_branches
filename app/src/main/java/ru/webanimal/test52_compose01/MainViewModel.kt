package ru.webanimal.test52_compose01

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

internal class MainViewModel(initState: MainScreenState = MainScreenState.Default) : ViewModel() {
    private val _viewState = MutableStateFlow(initState)
    internal val viewState: StateFlow<MainScreenState> = _viewState
}