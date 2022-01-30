package ru.webanimal.test52_compose01.core.compose

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

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