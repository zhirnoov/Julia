package com.github.zhirnoov.julia.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.github.zhirnoov.julia.presentation.screens.MainScreen
import com.github.zhirnoov.julia.presentation.viewmodels.SplashViewModel
import com.github.zhirnoov.julia.ui.theme.JuliaAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import java.util.*

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: SplashViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().apply {
            setKeepVisibleCondition {
                viewModel.isLoading.value
            }
        }
        Log.d("JuliaTest", "Splash Screen loaded ${Date()}")
        setContent {
            JuliaAppTheme {
                MainScreen()
            }
        }
    }
}