package ru.webanimal.test52_compose01

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.webanimal.test52_compose01.MainScreenState.Content
import ru.webanimal.test52_compose01.MainScreenState.Default
import ru.webanimal.test52_compose01.MainScreenState.Empty
import ru.webanimal.test52_compose01.MainScreenState.Error
import ru.webanimal.test52_compose01.MainScreenState.Loading
import ru.webanimal.test52_compose01.R.string

@Composable
internal fun MainScreen(
    viewModel: MainViewModel = MainViewModel(initState = Default),
) {

    val viewState by viewModel.viewState.collectAsState()
    val title = when (viewState) {
        Default -> stringResource(id = string.main_title_default)
        Loading -> stringResource(id = string.main_title_loading)
        is Content -> (viewState as Content).text
        Empty -> stringResource(id = string.main_title_empty)
        Error -> stringResource(id = string.main_title_error)
    }
    VerticalSimpleList(
        titleItem = { TitleText(text = title) },
        data = listOf("Maria", "Asja", "Sveta", "Lena", "Katja")
    )
}

@Composable
private fun TitleText(text: String) {

    Text(
        text = text,
        style = MaterialTheme.typography.h3,
        modifier = Modifier
            .padding(all = 24.dp)
            .fillMaxWidth()
            .wrapContentSize(align = Alignment.Center)
    )
}

@Composable
private fun SimpleText(text: String) {

    Text(
        text = text,
        style = MaterialTheme.typography.body1,
        modifier = Modifier
            .padding(all = 16.dp)
            .fillMaxWidth()
            .wrapContentSize(align = Alignment.CenterStart)
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

@Composable
private fun ListItemWithButton(text: String) {

    var isExpanded by remember { mutableStateOf(false) }
    val paddingExpansion = if (isExpanded) 32.dp else 0.dp

    Row(modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(bottom = paddingExpansion)
        ) {
            SimpleText(text = text)
        }
        ButtonWithTextUpdate(
            collapsedText = stringResource(id = string.main_button_first),
            expandedText = stringResource(id = string.main_button_second)
        ) {
            isExpanded = !isExpanded
            return@ButtonWithTextUpdate isExpanded
        }
    }
}

@Composable
private fun VerticalSimpleList(
    titleItem: @Composable () -> Unit,
    data: List<String>,
) {

    Column {
        Surface(color = MaterialTheme.colors.primaryVariant) {
            titleItem()
        }
        data.forEach {
            Surface(color = MaterialTheme.colors.primary) {
                ListItemWithButton(text = it)
            }
        }
    }
}