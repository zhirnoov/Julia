package com.github.zhirnoov.julia.navigation.tabs

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.github.zhirnoov.julia.R
import com.github.zhirnoov.julia.navigation.screens.CollectionsScreenNav
import com.github.zhirnoov.julia.navigation.screens.TrainingScreenNav

object TrainingTab : Tab {

    override val options: TabOptions
        @Composable
        get()  {
            val title = stringResource(R.string.title_training)
            val icon = painterResource(R.drawable.icon_training)

            return remember {
                TabOptions(
                    index = 1u,
                    title = title,
                    icon = icon
                )
            }
        }

    @Composable
    override fun Content() {
        Navigator(TrainingScreenNav())
    }


}