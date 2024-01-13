package com.github.orioneee

import android.app.Activity
import android.content.Context
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
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


    val AllColorModes: List<ColorMode>
        get() {
            return listOf(
                Colors.Dynamic,
                Colors.Blue,
                Colors.Green,
                Colors.Indigo,
                Colors.Orange,
                Colors.Breeze,
                Colors.Red
            ).filter { it.isSupported }
        }
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

    private val _chousenColorMode = mutableStateOf<ColorMode>(ColorMode.Dynamic)
    private val _chousenThemeMode = mutableStateOf<ThemeMode>(ThemeMode.System)


    val currentThemeMode = _chousenThemeMode
    val currentColorMode = _chousenColorMode

    fun setThemeMode(mode: ThemeMode) {
        val selected = if (mode.isSupported) mode else ThemeMode.Light
        _chousenThemeMode.value = selected
        SharedPrefernnces.saveThemeMode(selected)
    }

    fun setColorMode(mode: ColorMode) {
        val selected = if (mode.isSupported) mode else ColorMode.Blue
        _chousenColorMode.value = selected
        SharedPrefernnces.saveColorMode(selected)
    }

    val isAppDarkTheme: Boolean
        @Composable
        get() {
            return _chousenThemeMode.value == ThemeMode.Dark || (_chousenThemeMode.value == ThemeMode.System && isSystemInDarkTheme())
        }
    val ChousedDarkColorScheme: ColorScheme
        @Composable
        get() {
            return _chousenColorMode.value.theme.dark
        }
    val ChousedLightColorScheme: ColorScheme
        @Composable
        get() {
            return _chousenColorMode.value.theme.light
        }
    private val ChousedColorScheme: ColorScheme
        @Composable
        get() {
            val context = LocalContext.current
            SharedPrefernnces.prefs = context.getSharedPreferences(
                SharedPrefernnces.SharedPrefernncesName,
                Context.MODE_PRIVATE
            )
            LaunchedEffect(Unit) {
                _chousenThemeMode.value = SharedPrefernnces.loadThemeMode()
                _chousenColorMode.value = SharedPrefernnces.loadColorMode()
            }
            val isDark = isAppDarkTheme
            return if (isDark) _chousenColorMode.value.theme.dark else _chousenColorMode.value.theme.light
        }
    private val animatedColorScheme: ColorScheme
        @Composable
        get() {
            if (isAppDarkTheme) {
                return darkColorScheme(
                    primary = animateColorAsState(targetValue = ChousedColorScheme.primary).value,
                    onPrimary = animateColorAsState(targetValue = ChousedColorScheme.onPrimary).value,
                    primaryContainer = animateColorAsState(targetValue = ChousedColorScheme.primaryContainer).value,
                    onPrimaryContainer = animateColorAsState(targetValue = ChousedColorScheme.onPrimaryContainer).value,
                    inversePrimary = animateColorAsState(targetValue = ChousedColorScheme.inversePrimary).value,
                    secondary = animateColorAsState(targetValue = ChousedColorScheme.secondary).value,
                    onSecondary = animateColorAsState(targetValue = ChousedColorScheme.onSecondary).value,
                    secondaryContainer = animateColorAsState(targetValue = ChousedColorScheme.secondaryContainer).value,
                    onSecondaryContainer = animateColorAsState(targetValue = ChousedColorScheme.onSecondaryContainer).value,
                    tertiary = animateColorAsState(targetValue = ChousedColorScheme.tertiary).value,
                    onTertiary = animateColorAsState(targetValue = ChousedColorScheme.onTertiary).value,
                    tertiaryContainer = animateColorAsState(targetValue = ChousedColorScheme.tertiaryContainer).value,
                    onTertiaryContainer = animateColorAsState(targetValue = ChousedColorScheme.onTertiaryContainer).value,
                    background = animateColorAsState(targetValue = ChousedColorScheme.background).value,
                    onBackground = animateColorAsState(targetValue = ChousedColorScheme.onBackground).value,
                    surface = animateColorAsState(targetValue = ChousedColorScheme.surface).value,
                    onSurface = animateColorAsState(targetValue = ChousedColorScheme.onSurface).value,
                    surfaceVariant = animateColorAsState(targetValue = ChousedColorScheme.surfaceVariant).value,
                    onSurfaceVariant = animateColorAsState(targetValue = ChousedColorScheme.onSurfaceVariant).value,
                    inverseSurface = animateColorAsState(targetValue = ChousedColorScheme.inverseSurface).value,
                    inverseOnSurface = animateColorAsState(targetValue = ChousedColorScheme.inverseOnSurface).value,
                    outline = animateColorAsState(targetValue = ChousedColorScheme.outline).value,
                )
            }
            return lightColorScheme(
                primary = animateColorAsState(targetValue = ChousedColorScheme.primary).value,
                onPrimary = animateColorAsState(targetValue = ChousedColorScheme.onPrimary).value,
                primaryContainer = animateColorAsState(targetValue = ChousedColorScheme.primaryContainer).value,
                onPrimaryContainer = animateColorAsState(targetValue = ChousedColorScheme.onPrimaryContainer).value,
                inversePrimary = animateColorAsState(targetValue = ChousedColorScheme.inversePrimary).value,
                secondary = animateColorAsState(targetValue = ChousedColorScheme.secondary).value,
                onSecondary = animateColorAsState(targetValue = ChousedColorScheme.onSecondary).value,
                secondaryContainer = animateColorAsState(targetValue = ChousedColorScheme.secondaryContainer).value,
                onSecondaryContainer = animateColorAsState(targetValue = ChousedColorScheme.onSecondaryContainer).value,
                tertiary = animateColorAsState(targetValue = ChousedColorScheme.tertiary).value,
                onTertiary = animateColorAsState(targetValue = ChousedColorScheme.onTertiary).value,
                tertiaryContainer = animateColorAsState(targetValue = ChousedColorScheme.tertiaryContainer).value,
                onTertiaryContainer = animateColorAsState(targetValue = ChousedColorScheme.onTertiaryContainer).value,
                background = animateColorAsState(targetValue = ChousedColorScheme.background).value,
                onBackground = animateColorAsState(targetValue = ChousedColorScheme.onBackground).value,
                surface = animateColorAsState(targetValue = ChousedColorScheme.surface).value,
                onSurface = animateColorAsState(targetValue = ChousedColorScheme.onSurface).value,
                surfaceVariant = animateColorAsState(targetValue = ChousedColorScheme.surfaceVariant).value,
                onSurfaceVariant = animateColorAsState(targetValue = ChousedColorScheme.onSurfaceVariant).value,
                inverseSurface = animateColorAsState(targetValue = ChousedColorScheme.inverseSurface).value,
                inverseOnSurface = animateColorAsState(targetValue = ChousedColorScheme.inverseOnSurface).value,
                outline = animateColorAsState(targetValue = ChousedColorScheme.outline).value,
            )
        }
    val colorScheme: ColorScheme @Composable get() = animatedColorScheme

    @Composable
    fun Theme(content: @Composable () -> Unit) {
        val context = LocalContext.current
        SharedPrefernnces.prefs = context.getSharedPreferences(
            SharedPrefernnces.SharedPrefernncesName,
            Context.MODE_PRIVATE
        )
        LaunchedEffect(Unit) {
            _chousenThemeMode.value = SharedPrefernnces.loadThemeMode()
            _chousenColorMode.value = SharedPrefernnces.loadColorMode()
        }
        val colorScheme: ColorScheme
        val isDark = isAppDarkTheme
        colorScheme =
            if (isDark) _chousenColorMode.value.theme.dark else _chousenColorMode.value.theme.light
        val view = LocalView.current
        if (!view.isInEditMode) {
            SideEffect {
                val window = (view.context as Activity).window
                window.statusBarColor = Color.Transparent.toArgb()
                window.navigationBarColor = Color.Transparent.toArgb()
                WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !isDark
                WindowCompat.getInsetsController(window, view).isAppearanceLightNavigationBars =
                    !isDark
            }
        }

        MaterialTheme(
            colorScheme = colorScheme,
            content = content,
        )
    }
}