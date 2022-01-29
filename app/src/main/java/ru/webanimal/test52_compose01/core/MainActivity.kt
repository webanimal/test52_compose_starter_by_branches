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
import ru.webanimal.test52_compose01.feature_main.MainScreen
import ru.webanimal.test52_compose01.feature_onboarding.OnboardingScreen

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ThemeWrapper { MyApp() }
        }
    }

    @Composable
    private fun MyApp() {

        var shouldShowOnboarding by rememberSaveable { mutableStateOf(true) }
        if (shouldShowOnboarding) {
            OnboardingScreen(onContinueClick = { shouldShowOnboarding = false })

        } else {
            MainScreen()
        }
    }
}