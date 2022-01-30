package ru.webanimal.test52_compose01.feature_photo_marketplace

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import ru.webanimal.test52_compose01.core.compose.ThemeWrapper

@Composable
fun MarketplaceScreen() {
}

@Preview(showBackground = true, widthDp = 420, uiMode = Configuration.UI_MODE_NIGHT_YES, name = "DefaultPreviewDark")
@Preview(showBackground = true, widthDp = 420)
@Composable
private fun DefaultPreview() {

    ThemeWrapper { MarketplaceScreen() }
}