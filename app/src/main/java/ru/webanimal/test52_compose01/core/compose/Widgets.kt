package ru.webanimal.test52_compose01.core.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import ru.webanimal.test52_compose01.R.string

@Composable
fun TitleText(text: String) {

    Text(
        text = text,
        Modifier
            .padding(all = 16.dp)
            .fillMaxWidth()
            .wrapContentSize(align = Alignment.Center),
        style = MaterialTheme.typography.h3,
    )
}

@Composable
fun SimpleText(text: String, alignment: Alignment? = null) {

    Text(
        text = text,
        Modifier
            .fillMaxWidth()
            .wrapContentSize(align = alignment ?: Alignment.CenterStart),
        style = MaterialTheme.typography.body1.copy(
            fontWeight = FontWeight.ExtraBold
        ),
    )
}

@Composable
fun DoubleText(
    modifier: Modifier = Modifier,
    firstLineText: String,
    secondLineText: String,
) {

    Column(modifier) {
        Text(
            text = firstLineText,
            fontWeight = FontWeight.Bold
        )
        // LocalContentAlpha is defining opacity level of its children
        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
            Text(
                text = secondLineText,
                style = MaterialTheme.typography.body2
            )
        }
    }
}

@Composable
fun AvatarWithPlaceholder(
    modifier: Modifier = Modifier,
    avatarUrl: String,
) {

    Surface(
        modifier,
        shape = CircleShape,
        color = Color.LightGray.copy(alpha = 0.4f)
    ) {
        Image(
            painter = rememberImagePainter(data = avatarUrl),
            contentDescription = stringResource(id = string.marketplace_item_avatar_description),
            modifier = modifier.padding(all = 6.dp)
        )
    }
}