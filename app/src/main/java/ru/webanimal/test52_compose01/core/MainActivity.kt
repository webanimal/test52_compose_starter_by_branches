package ru.webanimal.test52_compose01.core

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import ru.webanimal.test52_compose01.core.compose.ThemeWrapper

class MainActivity : ComponentActivity() {

    val screenProvider by lazy { ScreenProvider() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ThemeWrapper { CodelabLayoutsApp() }
        }
    }

    @Composable
    private fun CodelabLayoutsApp() {
        screenProvider.Marketplace()
    }

    @Composable
    private fun CodelabBasicsApp() {

        var shouldShowOnboarding by rememberSaveable { mutableStateOf(true) }
        if (shouldShowOnboarding) {
            screenProvider.Onboarding(onContinueClick = { shouldShowOnboarding = false })

        } else {
            screenProvider.Main()
        }
    }
}