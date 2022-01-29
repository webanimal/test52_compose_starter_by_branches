package ru.webanimal.test52_compose01

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons.Filled
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.webanimal.test52_compose01.MainScreenState.Content
import ru.webanimal.test52_compose01.MainScreenState.Default
import ru.webanimal.test52_compose01.MainScreenState.Empty
import ru.webanimal.test52_compose01.MainScreenState.Error
import ru.webanimal.test52_compose01.MainScreenState.Loading
import ru.webanimal.test52_compose01.R.string

@Composable
internal fun MainScreen(
    viewModel: MainViewModel = MainViewModel(
        initState = Content(
            title = stringResource(id = string.main_title_default),
            data = List(1000) { "$it" }
        ),
    ),
) {

    val viewState by viewModel.viewState.collectAsState()
    val title = when (viewState) {
        is Content -> (viewState as Content).title
        Default -> stringResource(id = string.main_title_default)
        Empty -> stringResource(id = string.main_title_empty)
        Error -> stringResource(id = string.main_title_error)
        Loading -> stringResource(id = string.main_title_loading)
    }
    val data = (viewState as? Content)?.data ?: emptyList()
    VerticalListWithHeader(
        header = { TitleText(text = title) },
        dataSet = data
    )
}

@Composable
private fun VerticalListWithHeader(
    header: @Composable () -> Unit,
    dataSet: List<String>,
) {

    Column {
        Surface(color = MaterialTheme.colors.surface) {
            header()
        }
        LazyColumn {
            items(items = dataSet) { text ->
                AnimatedListItemWithButton(text = text)
            }
        }
    }
}

@Composable
private fun AnimatedListItemWithButton(text: String) {

    Card(
        backgroundColor = MaterialTheme.colors.primary,
        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        CardContent(text = text)
    }
}

@Composable
private fun CardContent(text: String) {

    var isExpanded by rememberSaveable { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .padding(all = 12.dp)
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            ),
        verticalAlignment = Alignment.Top
    ) {

        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .wrapContentHeight(align = Alignment.CenterVertically)
        ) {
            SimpleText(text = stringResource(id = string.main_item_title))
            Spacer(modifier = Modifier.height(4.dp))
            SimpleText(text)
            if (isExpanded) {
                Spacer(modifier = Modifier.height(4.dp))
                SimpleText(
                    text = stringResource(id = string.main_item_expandable_text).repeat(4)
                )
            }
        }

        Spacer(modifier = Modifier.width(4.dp))

        IconButtonUpdatable(
            collapsedDescription = stringResource(id = string.main_button_first),
            expandedDescription = stringResource(id = string.main_button_second)
        ) {
            isExpanded = !isExpanded
            return@IconButtonUpdatable isExpanded
        }
    }
}

@Composable
private fun IconButtonUpdatable(
    collapsedDescription: String,
    expandedDescription: String,
    onClick: () -> Boolean,
) {

    var isExpanded by remember { mutableStateOf(false) }

    IconButton(
        onClick = { isExpanded = onClick() },
        content = {
            Icon(
                imageVector = if (isExpanded) Filled.ExpandLess else Filled.ExpandMore,
                contentDescription = if (isExpanded) expandedDescription else collapsedDescription
            )
        }
    )
}

@Composable
private fun ButtonWithTextUpdate(
    collapsedText: String,
    expandedText: String,
    onClick: () -> Boolean,
) {

    var isExpanded by remember { mutableStateOf(false) }

    OutlinedButton(onClick = { isExpanded = onClick() }) {
        Text(text = if (isExpanded) expandedText else collapsedText)
    }
}

@Preview(showBackground = true, widthDp = 420, uiMode = UI_MODE_NIGHT_YES, name = "DefaultPreviewDark")
@Preview(showBackground = true, widthDp = 420)
@Composable
private fun DefaultPreview() {

    ThemeWrapper { MainScreen() }
}