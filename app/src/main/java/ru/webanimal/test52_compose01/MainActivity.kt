package ru.webanimal.test52_compose01

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
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
        viewModel: MainViewModel = MainViewModel(),
    ) {

        val viewState by viewModel.viewState.collectAsState()
        when (viewState) {
            Default -> InitTitle(text = stringResource(id = string.main_title_default))
            Loading -> InitTitle(text = stringResource(id = string.main_title_loading))
            is Content -> InitTitle(text = (viewState as Content).text)
            Empty -> InitTitle(text = stringResource(id = string.main_title_empty))
            Error -> InitTitle(text = stringResource(id = string.main_title_error))
        }
    }

    @Composable
    private fun InitTitle(text: String) {
        Text(
            text = text,
            style = MaterialTheme.typography.h3,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = dimensionResource(id = R.dimen.size_16dp))
                .wrapContentWidth(align = Alignment.CenterHorizontally)
        )
    }

    @Composable
    private fun ThemeWrapper(content: @Composable () -> Unit) {
        MaterialTheme {
            Surface(color = MaterialTheme.colors.background) {
                content()
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    private fun DefaultPreview() {
        ThemeWrapper { SetupViewStateViaVM() }
    }
}