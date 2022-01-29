package ru.webanimal.test52_compose01

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MainViewModel : ViewModel() {
    private val _viewState = MutableStateFlow(MainScreenState.Default)
    internal val viewState: StateFlow<MainScreenState> = _viewState
}