package com.github.zhirnoov.julia.navigation.tabs

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.github.zhirnoov.julia.R
import com.github.zhirnoov.julia.navigation.screens.SettingsScreenNav

object SettingsTab : Tab {

    override val options: TabOptions
        @Composable
        get()  {
            val title = stringResource(R.string.title_setting)
            val icon = painterResource(R.drawable.icon_settings)

            return remember {
                TabOptions(
                    index = 2u,
                    title = title,
                    icon = icon
                )
            }
        }

    @Composable
    override fun Content() {
        Navigator(SettingsScreenNav())
    }


}