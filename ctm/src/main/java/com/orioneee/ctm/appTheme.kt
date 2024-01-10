package com.orioneee.ctm

import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable

interface appTheme {
    @get:Composable
    val light: ColorScheme
    @get:Composable
    val dark: ColorScheme
}