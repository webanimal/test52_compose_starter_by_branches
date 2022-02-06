package ru.webanimal.test52_compose01.feature_custom_layout

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.layout.FirstBaseline
import androidx.compose.ui.layout.layout
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ru.webanimal.test52_compose01.R
import ru.webanimal.test52_compose01.core.compose.CustomColumn

@Composable
internal fun CustomLayoutScreen() {
    CustomColumn(Modifier.padding(8.dp)) {
        Text(text = "First")
        TextWithTopPadding(padding = 8.dp, "Second")
        TextWithTopPadding(padding = 8.dp, "Third")
        TextWithTopPadding(padding = 8.dp, "etc...")
        TextWithTopPadding(padding = 8.dp)
        TextWithTopPadding(padding = 8.dp)
    }
}

@Composable
private fun TextWithTopPaddingToBaseline(padding: Dp, text: String? = null) {
    Text(
        text = text ?: stringResource(id = R.string.layouts_custom_sample),
        Modifier.firstBaselineToTop(padding)
    )
}

@Composable
private fun TextWithTopPadding(padding: Dp, text: String? = null) {
    Text(
        text = text ?: stringResource(id = R.string.layouts_custom_sample),
        Modifier.padding(top = padding)
    )
}

@Preview(showBackground = true)
@Composable
private fun CustomColumnPreview() {
    CustomLayoutScreen()
}

@Preview(showBackground = true)
@Composable
private fun TextWithTopPaddingToBaselinePreview() {
    TextWithTopPaddingToBaseline(padding = 32.dp)
}

@Preview(showBackground = true)
@Composable
private fun TextWithTopPaddingPreview() {
    TextWithTopPadding(padding = 32.dp)
}

private fun Modifier.firstBaselineToTop(firstBaselineToTop: Dp) = this.then(
    layout { measurable, constraints ->
        val placeable = measurable.measure(constraints)

        // Check the composable has a first baseline
        check(placeable[FirstBaseline] != AlignmentLine.Unspecified)
        val firstBaseLine = placeable[FirstBaseline]

        // Height of the composable with padding - first baseline
        val placeableY = firstBaselineToTop.roundToPx() - firstBaseLine
        val height = placeableY + placeable.height
        layout(placeable.width, height) {
            placeable.placeRelative(0, placeableY)
        }
    }
)