package com.github.orioneee

import android.app.Activity
import android.content.Context
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import androidx.lifecycle.MutableLiveData
import com.github.orioneee.internal.SharedPrefernnces

object Ctm {
    val Colors = ColorModes
    val Themes = ThemeModes
    val AllColorModes = listOf(
        Colors.Dynamic,
        Colors.Blue,
        Colors.Green,
        Colors.Indigo,
        Colors.Orange,
        Colors.Breeze,
        Colors.Red
    )
    val AllThemeModes = listOf(
        ThemeMode.System,
        ThemeMode.Light,
        ThemeMode.Dark
    )
    val ColorScheme.disabled: Color
        @Composable
        get() {
            return if (isAppDarkTheme) {
                lerp(Color.DarkGray, this.primary, 0f)
            } else {
                lerp(Color.LightGray, this.primary, 0f)
            }
        }
    private val _chousenColorMode = MutableLiveData<ColorMode>()
    private val _chousenThemeMode = MutableLiveData<ThemeMode>()

    val currentThemeMode = _chousenThemeMode
    val currentColorMode = _chousenColorMode

    fun setThemeMode(mode: ThemeMode) {
        val selected = if(mode.isSupported) mode else ThemeMode.Light
        _chousenThemeMode.value = selected
        SharedPrefernnces.saveThemeMode(selected)
    }

    fun setColorMode(mode: ColorMode) {
        val selected = if(mode.isSupported) mode else ColorMode.Blue
        _chousenColorMode.value = selected
        SharedPrefernnces.saveColorMode(selected)
    }
    val isAppDarkTheme: Boolean
        @Composable
        get() {
            return _chousenThemeMode.observeAsState(ThemeMode.System).value == ThemeMode.Dark || (_chousenThemeMode.observeAsState(
                ThemeMode.System
            ).value == ThemeMode.System && isSystemInDarkTheme())
        }
    val darkColorScheme: ColorScheme
        @Composable
        get() {
            return _chousenColorMode.observeAsState(ColorMode.Dynamic).value.theme.dark
        }
    val lightColorScheme: ColorScheme
        @Composable
        get() {
            return _chousenColorMode.observeAsState(ColorMode.Dynamic).value.theme.light
        }
    val dynamicColorScheme: ColorScheme
        @Composable
        get() {
            val chooused = _chousenColorMode.observeAsState(ColorMode.Dynamic).value
            return if(isAppDarkTheme) chooused.theme.dark else chooused.theme.light
        }

    @Composable
    fun Theme(content: @Composable () -> Unit) {
        val context = LocalContext.current
        SharedPrefernnces.prefs = context.getSharedPreferences(SharedPrefernnces.SharedPrefernncesName, Context.MODE_PRIVATE)
        LaunchedEffect(Unit){
                _chousenThemeMode.value = SharedPrefernnces.loadThemeMode()
                _chousenColorMode.value = SharedPrefernnces.loadColorMode()
        }
        val colorMode by _chousenColorMode.observeAsState(ColorMode.Dynamic)
        val colorScheme: ColorScheme
        val isDark = isAppDarkTheme
        colorScheme = if(isDark)  colorMode.theme.dark else colorMode.theme.light
        val view = LocalView.current
        if (!view.isInEditMode) {
            SideEffect {
                val window = (view.context as Activity).window
                window.statusBarColor = Color.Transparent.toArgb()
                window.navigationBarColor = Color.Transparent.toArgb()
                WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !isDark
            }
        }

        MaterialTheme(
            colorScheme = colorScheme,
            content = content,
        )
    }
}