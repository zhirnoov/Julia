package com.github.zhirnoov.julia.presentation.screens.collections

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.zhirnoov.julia.R

@Composable
fun EmptyCollectionsMessage(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier
                .padding(12.dp)
                .size(width = 212.dp, height = 113.dp),
            painter = painterResource(R.drawable.img_empty_collections),
            contentDescription = "image empty collections",
            alignment = Alignment.Center
        )
        Text(
            modifier = modifier.padding(top = 40.dp),
            text = stringResource(R.string.emptyCollections_text),
            fontWeight = FontWeight.W600,
            fontSize = 20.sp
        )
        Text(
            modifier = modifier.padding(top = 6.dp),
            text = stringResource(R.string.emptyCollectionsContent_text),
            fontWeight = FontWeight.W400,
            fontSize = 14.sp
        )
    }

}