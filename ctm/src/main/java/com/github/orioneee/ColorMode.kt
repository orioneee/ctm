package com.github.orioneee

import android.os.Build
import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable
import com.github.orioneee.internal.AppThemes

sealed class ColorMode(
    internal val key: String,
    val isSupported: Boolean = true,
    val theme: appTheme
) {
    data object Dynamic : ColorMode(
        key = "Dynamic",
        isSupported = (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S),
        theme = object : appTheme {
            override val light: ColorScheme
                @Composable
                get() = AppThemes.DynamicTheme.light
            override val dark: ColorScheme
                @Composable
                get() = AppThemes.DynamicTheme.dark
        }
    )

    data object Blue : ColorMode(
        key = "Blue",
        theme = object : appTheme {
            override val light: ColorScheme
                @Composable
                get() = AppThemes.BlueTheme.light
            override val dark: ColorScheme
                @Composable
                get() = AppThemes.BlueTheme.dark
        }
    )

    data object Green : ColorMode(
        key = "Green",
        theme = object : appTheme {
        override val light: ColorScheme
            @Composable
            get() = AppThemes.GreenTheme.light
        override val dark: ColorScheme
            @Composable
            get() = AppThemes.GreenTheme.dark

        }
    )

    data object Indigo : ColorMode(
        key = "Indigo",
        theme = object : appTheme {
            override val light: ColorScheme
                @Composable
                get() = AppThemes.IndigoTheme.light
            override val dark: ColorScheme
                @Composable
                get() = AppThemes.IndigoTheme.dark
        }
    )
    data object Orange : ColorMode(
        key = "Orange",
        theme = object : appTheme {
            override val light: ColorScheme
                @Composable
                get() = AppThemes.OrangeTheme.light
            override val dark: ColorScheme
                @Composable
                get() = AppThemes.OrangeTheme.dark
        }
    )

    data object Breeze : ColorMode(
        key = "Breeze",
        theme = object : appTheme {
            override val light: ColorScheme
                @Composable
                get() = AppThemes.BreezeTheme.light
            override val dark: ColorScheme
                @Composable
                get() = AppThemes.BreezeTheme.dark
        }
    )

    data object Red : ColorMode(
        key = "Red",
        theme = object : appTheme {
            override val light: ColorScheme
                @Composable
                get() = AppThemes.RedTheme.light
            override val dark: ColorScheme
                @Composable
                get() = AppThemes.RedTheme.dark
        }
    )
}