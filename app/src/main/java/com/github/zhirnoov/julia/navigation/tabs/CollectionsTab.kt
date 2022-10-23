package com.github.zhirnoov.julia.navigation.tabs

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.github.zhirnoov.julia.R
import com.github.zhirnoov.julia.navigation.screens.CollectionsScreenNav
import com.github.zhirnoov.julia.presentation.screens.collections.CollectionsScreen

object CollectionsTab : Tab {

    override val options: TabOptions
    @SuppressLint("SuspiciousIndentation") @Composable
        get()  {
         val title = stringResource(R.string.title_collections)
         val icon = painterResource(R.drawable.icon_cards)

            return remember {
                TabOptions(
                    index = 0u,
                    title = title,
                    icon = icon
                )
            }
        }

    @Composable
    override fun Content() {
       Navigator(CollectionsScreenNav())
    }


}