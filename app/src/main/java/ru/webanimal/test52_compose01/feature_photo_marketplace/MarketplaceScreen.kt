package ru.webanimal.test52_compose01.feature_photo_marketplace

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.webanimal.test52_compose01.R
import ru.webanimal.test52_compose01.core.compose.AvatarWithPlaceholder
import ru.webanimal.test52_compose01.core.compose.DoubleText
import ru.webanimal.test52_compose01.core.compose.ThemeWrapper

@Composable
fun MarketplaceScreen() {

    TopBar()
}

@Composable
private fun TopBar(modifier: Modifier = Modifier) {

    Scaffold(
        topBar = { TopBarContent(modifier) }
    ) { innerPadding ->
        BodyContent(modifier.padding(innerPadding))
    }
}

@Composable
private fun TopBarContent(modifier: Modifier = Modifier) {

    var isAversVisible by rememberSaveable { mutableStateOf(true) }

    TopAppBar(
        title = {
            Text(text = stringResource(id = R.string.marketplace_top_bar_title))
        },
        actions = {
            IconButton(onClick = {
                isAversVisible = !isAversVisible
            }) {
                Icon(
                    imageVector = if (isAversVisible) Icons.Filled.FavoriteBorder else Icons.Filled.Favorite,
                    contentDescription = stringResource(id = R.string.marketplace_top_bar_action_favorite_description)
                )
            }
        }
    )
}

@Composable
private fun BodyContent(modifier: Modifier = Modifier) {

    val scrollState = rememberLazyListState()

    LazyColumn(state = scrollState) {
        items(LIST_ITEM_COUNT) { pos ->
            DoubleTextWithAvatarItem(modifier, position = pos)
        }
    }
}

@Composable
private fun DoubleTextWithAvatarItem(
    modifier: Modifier = Modifier,
    position: Int,
) {

    Row(
        modifier
            .fillMaxWidth()
            .padding(all = 8.dp)
            .clip(RoundedCornerShape(4.dp))
            .background(MaterialTheme.colors.surface)
            .clickable { /**/ }
            .padding(all = 16.dp)
    ) {
        AvatarWithPlaceholder(
            Modifier.size(44.dp),
            avatarUrl = AVATAR_URL
        )
        DoubleText(
            Modifier
                .padding(start = 8.dp)
                .align(alignment = Alignment.CenterVertically),
            firstLineText = stringResource(id = R.string.marketplace_item_title),
            secondLineText = stringResource(id = R.string.marketplace_item_text, position)
        )
    }
}

@Preview(showBackground = true, widthDp = 420, uiMode = Configuration.UI_MODE_NIGHT_YES, name = "DefaultPreviewDark")
@Preview(showBackground = true, widthDp = 420)
@Composable
private fun DefaultPreview() {

    ThemeWrapper { MarketplaceScreen() }
}

private const val LIST_ITEM_COUNT = 100
private const val AVATAR_URL = "https://developer.android.com/images/brand/Android_Robot.png"