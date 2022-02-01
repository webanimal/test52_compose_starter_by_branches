package ru.webanimal.test52_compose01.core

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import ru.webanimal.test52_compose01.core.ScreenNavigator.CodelabType
import ru.webanimal.test52_compose01.core.compose.ThemeWrapper

class MainActivity : ComponentActivity() {

    private val navigator : ScreenNavigator by lazy { ScreenNavigator.provideNavigator() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ThemeWrapper { CodelabApp() }
        }
    }

    @Composable
    private fun CodelabApp() {
        var shouldShowOnboarding by rememberSaveable { mutableStateOf(true) }
        if (shouldShowOnboarding) {
            navigator.NavigateTo(
                CodelabType.Onboarding(onContinueClick = { shouldShowOnboarding = false })
            )

        } else {
            navigator.NavigateTo(CodelabType.Layouts.CustomLayout)
        }
    }
}