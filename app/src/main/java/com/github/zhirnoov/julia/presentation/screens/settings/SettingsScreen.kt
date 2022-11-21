package com.github.zhirnoov.julia.presentation.screens.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.github.zhirnoov.julia.data.datastore.StoreTheme
import com.github.zhirnoov.julia.navigation.screens.AboutScreenNav
import com.github.zhirnoov.julia.presentation.viewmodels.MainViewModel
import kotlinx.coroutines.launch

@Composable
fun SettingsScreen(modifier: Modifier = Modifier) {

    val navigation = LocalNavigator.currentOrThrow

    Scaffold(topBar = {
        TopAppBar(backgroundColor = Color(0xFF0277BD),
            title = { Text(text = "Настройки", color = Color.White) })
    }, content = { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            Row(
                modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, start = 8.dp, end = 8.dp)
                    .clickable(onClick = { navigation.push(AboutScreenNav()) }),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = modifier.size(32.dp),
                    imageVector = Icons.Filled.Info,
                    contentDescription = "info icon",
                    tint = MaterialTheme.colors.onBackground
                )
                Text(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 4.dp),
                    text = "О приложении",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.W400,
                    color = MaterialTheme.colors.onBackground,
                )
                Icon(
                    imageVector = Icons.Filled.KeyboardArrowRight,
                    contentDescription = "icon arrow right",
                    tint = MaterialTheme.colors.onBackground,
                )
            }


            SwitchThemes()
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 10.dp),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    "Версия приложения: 1.1",
                    color = MaterialTheme.colors.onBackground,
                    fontWeight = FontWeight.W300
                )
            }
        }
    })
}

@Composable
fun SwitchThemes(modifier: Modifier = Modifier) {

    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val dataStore = StoreTheme(context)
    val savedTheme = dataStore.getTheme.collectAsState(false)

    var switchState by remember { mutableStateOf(false) }

    Row(
        modifier
            .fillMaxWidth()
            .padding(top = 10.dp, start = 8.dp, end = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            text = "Темная тема", fontSize = 18.sp, fontWeight = FontWeight.W400
        )
        Switch(checked = savedTheme.value, onCheckedChange = {
            switchState = it
            scope.launch {
                dataStore.saveTheme(switchState)
            }
        })
    }
}
