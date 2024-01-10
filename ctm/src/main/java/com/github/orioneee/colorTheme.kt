package com.github.orioneee

import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable

interface colorTheme {
    val isSupportedDynamic: Boolean
    @get:Composable
    val Dynamic: ColorScheme
    @get:Composable
    val Blue: ColorScheme
    @get:Composable
    val Green: ColorScheme
    @get:Composable
    val Indigo: ColorScheme
    @get:Composable
    val Orange: ColorScheme
    @get:Composable
    val Breeze: ColorScheme
    @get:Composable
    val Red: ColorScheme
}