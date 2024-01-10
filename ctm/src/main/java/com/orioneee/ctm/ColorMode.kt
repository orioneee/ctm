package com.orioneee.ctm

import android.os.Build
import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable
import com.orioneee.ctm.internal.AppThemes

sealed class ColorMode(
    internal val key: String,
    val isSupported: Boolean = true,
    val theme: appTheme
) {
    data object System : ColorMode(
        key = "system",
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
        key = "blue",
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
        key = "green",
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
        key = "indigo",
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
        key = "orange",
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
        key = "breeze",
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
        key = "red",
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