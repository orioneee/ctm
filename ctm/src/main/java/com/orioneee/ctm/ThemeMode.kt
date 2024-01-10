package com.orioneee.ctm

import android.os.Build
import androidx.annotation.ChecksSdkIntAtLeast
import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable
import com.orioneee.ctm.internal.AppThemes

sealed class ThemeMode(
    internal val key: String,
    val isSupported: Boolean = true,
    val colorScheme: colorTheme
) {
    data object System : ThemeMode(
        key = "system",
        isSupported = Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q,
        colorScheme = object : colorTheme {
            @ChecksSdkIntAtLeast(api = Build.VERSION_CODES.S)
            override val isSupportedDynamic = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
            override val Dynamic: ColorScheme
                @Composable
                get() {
                    return if(Ctm.isAppDarkTheme) {
                        AppThemes.DynamicDarkColorScheme
                    } else {
                        AppThemes.DynamicLightColorScheme
                    }
                }
            override val Blue: ColorScheme
                @Composable
                get() = if(Ctm.isAppDarkTheme) AppThemes.BlueTheme.dark else AppThemes.BlueTheme.light
            override val Green: ColorScheme
                @Composable
                get() = if(Ctm.isAppDarkTheme) AppThemes.GreenTheme.dark else AppThemes.GreenTheme.light
            override val Indigo: ColorScheme
                @Composable
                get() = if(Ctm.isAppDarkTheme) AppThemes.IndigoTheme.dark else AppThemes.IndigoTheme.light
            override val Orange: ColorScheme
                @Composable
                get() = if(Ctm.isAppDarkTheme) AppThemes.OrangeTheme.dark else AppThemes.OrangeTheme.light
            override val Breeze: ColorScheme
                @Composable
                get() = if(Ctm.isAppDarkTheme) AppThemes.BreezeTheme.dark else AppThemes.BreezeTheme.light
            override val Red: ColorScheme
                @Composable
                get() = if(Ctm.isAppDarkTheme) AppThemes.RedTheme.dark else AppThemes.RedTheme.light
        }
    )
    data object Dark: ThemeMode(
        key = "dark",
        colorScheme = object : colorTheme {
            @ChecksSdkIntAtLeast(api = Build.VERSION_CODES.S)
            override val isSupportedDynamic = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
            override val Dynamic: ColorScheme
                @Composable
                get() = AppThemes.BlueTheme.dark
            override val Blue: ColorScheme
                @Composable
                get() = AppThemes.BlueTheme.dark
            override val Green: ColorScheme
                @Composable
                get() = AppThemes.GreenTheme.dark
            override val Indigo: ColorScheme
                @Composable
                get() = AppThemes.IndigoTheme.dark
            override val Orange: ColorScheme
                @Composable
                get() = AppThemes.OrangeTheme.dark
            override val Breeze: ColorScheme
                @Composable
                get() = AppThemes.BreezeTheme.dark
            override val Red: ColorScheme
                @Composable
                get() = AppThemes.RedTheme.dark
        }
    )
    data object Light: ThemeMode(
        key = "light",
        colorScheme = object : colorTheme {
            @ChecksSdkIntAtLeast(api = Build.VERSION_CODES.S)
            override val isSupportedDynamic = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
            override val Dynamic: ColorScheme
                @Composable
                get() = AppThemes.BlueTheme.light
            override val Blue: ColorScheme
                @Composable
                get() = AppThemes.BlueTheme.light
            override val Green: ColorScheme
                @Composable
                get() = AppThemes.GreenTheme.light
            override val Indigo: ColorScheme
                @Composable
                get() = AppThemes.IndigoTheme.light
            override val Orange: ColorScheme
                @Composable
                get() = AppThemes.OrangeTheme.light
            override val Breeze: ColorScheme
                @Composable
                get() = AppThemes.BreezeTheme.light
            override val Red: ColorScheme
                @Composable
                get() = AppThemes.RedTheme.light
        }
    )
}