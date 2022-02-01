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

@Composable
internal fun CustomLayoutScreen() {
    // TODO https://developer.android.com/codelabs/jetpack-compose-layouts?authuser=2&continue=https%3A%2F%2Fdeveloper.android.com%2Fcourses%2Fpathways%2Fcompose%3Fauthuser%3D2%23codelab-https%3A%2F%2Fdeveloper.android.com%2Fcodelabs%2Fjetpack-compose-layouts#6
}

@Composable
fun TextWithTopPaddingToBaseline(padding: Dp) {
    Text(
        text = stringResource(id = R.string.layouts_custom_sample),
        Modifier.firstBaselineToTop(padding)
    )
}

@Composable
fun TextWithTopPadding(padding: Dp) {
    Text(
        text = stringResource(id = R.string.layouts_custom_sample),
        Modifier.padding(top = padding)
    )
}

@Preview(showBackground = true)
@Composable
fun TextWithTopPaddingToBaselinePreview() {
    TextWithTopPaddingToBaseline(padding = 32.dp)
}

@Preview(showBackground = true)
@Composable
fun TextWithTopPaddingPreview() {
    TextWithTopPadding(padding = 32.dp)
}

fun Modifier.firstBaselineToTop(firstBaselineToTop: Dp) = this.then(
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