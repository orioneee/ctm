package com.orioneee.ctm.internal

import android.os.Build
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.orioneee.ctm.appTheme

internal object AppThemes {
    internal val DynamicDarkColorScheme: ColorScheme
        @Composable
        get() {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) return dynamicDarkColorScheme(
                LocalContext.current
            )
            return BlueTheme.dark
        }

    internal val DynamicLightColorScheme: ColorScheme
        @Composable
        get() {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) return dynamicLightColorScheme(
                LocalContext.current
            )
            return BlueTheme.light
        }
    val DynamicTheme = object : appTheme {
        override val light: ColorScheme
            @Composable
            get() = DynamicLightColorScheme
        override val dark: ColorScheme
            @Composable
            get() = DynamicDarkColorScheme
    }

    val BlueTheme = object : appTheme {
        override val light: ColorScheme
            @Composable
            get() = ThemePallets.LightColorBlue
        override val dark: ColorScheme
            @Composable
            get() = ThemePallets.DarkColorBlue
    }
    val GreenTheme = object : appTheme {
        override val light: ColorScheme
            @Composable
            get() = ThemePallets.LightColorGreen
        override val dark: ColorScheme
            @Composable
            get() = ThemePallets.DarkColorGreen
    }
    val IndigoTheme = object : appTheme {
        override val light: ColorScheme
            @Composable
            get() = ThemePallets.LightColorIndigo
        override val dark: ColorScheme
            @Composable
            get() = ThemePallets.DarkColorIndigo
    }
    val OrangeTheme = object : appTheme {
        override val light: ColorScheme
            @Composable
            get() = ThemePallets.LightColorOrange
        override val dark: ColorScheme
            @Composable
            get() = ThemePallets.DarkColorOrange
    }
    val BreezeTheme = object : appTheme {
        override val light: ColorScheme
            @Composable
            get() = ThemePallets.LightColorBreeze
        override val dark: ColorScheme
            @Composable
            get() = ThemePallets.DarkColorBreeze
    }
    val RedTheme = object : appTheme {
        override val light: ColorScheme
            @Composable
            get() = ThemePallets.LightColorRed
        override val dark: ColorScheme
            @Composable
            get() = ThemePallets.DarkColorRed
    }
}