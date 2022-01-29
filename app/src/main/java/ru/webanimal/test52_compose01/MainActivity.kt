package ru.webanimal.test52_compose01

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ThemeWrapper { SetupViewStateViaVM() }
        }
    }

    @Composable
    private fun SetupViewStateViaVM(
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

        Surface(color = MaterialTheme.colors.primaryVariant) {
            Text(
                text = text,
                style = MaterialTheme.typography.h3,
                modifier = Modifier
                    .padding(all = 24.dp)
                    .fillMaxWidth()
                    .wrapContentSize(align = Alignment.Center)
            )
        }
    }

    @Composable
    private fun SimpleText(text: String) {

        Surface(color = MaterialTheme.colors.primary) {
            Text(
                text = text,
                style = MaterialTheme.typography.body1,
                modifier = Modifier
                    .padding(all = 16.dp)
                    .fillMaxWidth()
                    .wrapContentSize(align = Alignment.CenterStart)
            )
        }
    }

    @Composable
    private fun VerticalSimpleList(
        titleItem: @Composable () -> Unit,
        data: List<String>,
    ) {

        Column {
            titleItem()
            data.forEach { SimpleText(it) }
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

    @Preview(
        showBackground = true,
        widthDp = 420
    )
    @Composable
    private fun DefaultPreview() {

        ThemeWrapper { SetupViewStateViaVM() }
    }
}