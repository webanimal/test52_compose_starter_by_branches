package ru.webanimal.test52_compose01.feature_onboarding

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.webanimal.test52_compose01.R
import ru.webanimal.test52_compose01.core.compose.SimpleText
import ru.webanimal.test52_compose01.core.compose.ThemeWrapper

@Composable
fun OnboardingScreen(
    onContinueClick: () -> Unit
) {

    Surface {
        Column(
            Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SimpleText(
                text = stringResource(id = R.string.onboarding_welcome_text),
                alignment = Alignment.Center
            )
            Button(
                onClick = { onContinueClick() },
                Modifier.padding(vertical = 24.dp)
            ) {
                Text(text = stringResource(id = R.string.onboarding_button_next_text))
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 420, uiMode = Configuration.UI_MODE_NIGHT_YES, name = "DefaultPreviewDark")
@Preview(showBackground = true, widthDp = 420)
@Composable
private fun DefaultPreview() {

    ThemeWrapper { OnboardingScreen(onContinueClick = {}) }
}