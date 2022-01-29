package ru.webanimal.test52_compose01

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

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

    @Composable
    private fun ThemeWrapper(content: @Composable () -> Unit) {

        MaterialTheme {
            Surface(
                color = MaterialTheme.colors.background,
                modifier = Modifier.padding(all = 8.dp)
            ) {
                content()
            }
        }
    }

    @Preview(showBackground = true, widthDp = 420)
    @Composable
    private fun MainScreenPreview() {

        ThemeWrapper { MainScreen() }
    }

    @Preview(showBackground = true, widthDp = 420)
    @Composable
    private fun OnboardingPreview() {

        ThemeWrapper { OnboardingScreen(onContinueClick = {}) }
    }
}