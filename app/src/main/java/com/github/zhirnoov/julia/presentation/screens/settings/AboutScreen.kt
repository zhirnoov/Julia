package com.github.zhirnoov.julia.presentation.screens.settings

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.github.zhirnoov.julia.R

@Composable
fun AboutScreen() {
    val navigator = LocalNavigator.currentOrThrow
    Scaffold(topBar = {
        TopAppBar(
            backgroundColor = Color(0xFF0277BD),
            title = { Text(text = "О приложении", color = Color.White) },
            navigationIcon = {
                IconButton(onClick = { navigator.pop() }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "button back",
                        tint = Color.White
                    )
                }
            })
    }, content = { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(start = 8.dp, end = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .size(180.dp)
                    .clip(RoundedCornerShape(40.dp)),
                painter = painterResource(id = R.drawable.julia_logo_about),
                contentDescription = "julia logo"
            )
            Text(
                modifier = Modifier.padding(top = 10.dp),
                text = "Julia - приложение для эффективного запоминания информации, используя метод интервальных повторений.",
                textAlign = TextAlign.Start,
                fontSize = 18.sp,
                fontWeight = FontWeight.W400
            )

            Text(
                modifier = Modifier.padding(top = 6.dp),
                text = "Метод интервальных повторений позволяет улучшать процесс обучения,\nпри котором карточки повторяются через увеличивающиеся интервалы.",
                textAlign = TextAlign.Start,
                fontSize = 16.sp,
                fontWeight = FontWeight.W300
            )
        }
    })
}