package com.github.zhirnoov.julia.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.*
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.github.zhirnoov.julia.presentation.viewmodels.MainUIState
import com.github.zhirnoov.julia.presentation.viewmodels.MainViewModel
import com.github.zhirnoov.julia.ui.theme.JuliaAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel : MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashscreen = installSplashScreen()
        super.onCreate(savedInstanceState)

        var uiState : MainUIState by mutableStateOf(MainUIState.Loading)

        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState
                    .onEach {
                        uiState = it
                    }
                    .collect()
            }
        }
        
        splashscreen.setKeepOnScreenCondition {
            when(uiState) {
                MainUIState.Loading -> true
                is MainUIState.Success -> false
            }
        }


        setContent {
            
            val isDarkTheme = shouldUseDarkTheme(uiState)
            
            JuliaAppTheme(darkTheme = isDarkTheme) {
                MainScreen()
            }
        }
    }

    @Composable
    private fun shouldUseDarkTheme(uiState: MainUIState): Boolean =
        when(uiState) {
            MainUIState.Loading-> isSystemInDarkTheme()
            is MainUIState.Success -> uiState.isDarkTheme
        }

}