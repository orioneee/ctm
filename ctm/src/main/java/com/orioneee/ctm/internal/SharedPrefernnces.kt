package com.orioneee.ctm.internal

import android.content.SharedPreferences
import com.orioneee.ctm.ColorMode
import com.orioneee.ctm.ThemeMode

internal object SharedPrefernnces {
    lateinit var prefs: SharedPreferences
    const val SharedPrefernncesName = "ctm"
    private const val themeMode = "theme_mode"
    private const val colorMode = "color_mode"

    fun saveThemeMode(mode: ThemeMode){
        prefs.edit().putString(themeMode, mode.key).apply()
    }

    fun saveColorMode(mode: ColorMode) {
        prefs.edit().putString(colorMode, mode.key).apply()
    }

    fun loadThemeMode(): ThemeMode {
        val mode = prefs.getString(themeMode, ThemeMode.System.key)
        val result =  when (mode) {
            ThemeMode.System.key -> ThemeMode.System
            ThemeMode.Dark.key -> ThemeMode.Dark
            ThemeMode.Light.key -> ThemeMode.Light
            else -> ThemeMode.System
        }
        if(!result.isSupported) return ThemeMode.Light
        return result
    }

    fun loadColorMode(): ColorMode {
        val mode = prefs.getString(colorMode, "system")
        val result =  when (mode) {
            ColorMode.Dynamic.key -> ColorMode.Dynamic
            ColorMode.Blue.key -> ColorMode.Blue
            ColorMode.Green.key -> ColorMode.Green
            ColorMode.Indigo.key -> ColorMode.Indigo
            ColorMode.Orange.key -> ColorMode.Orange
            ColorMode.Breeze.key -> ColorMode.Breeze
            ColorMode.Red.key -> ColorMode.Red
            else -> ColorMode.Dynamic
        }
        if(!result.isSupported) return ColorMode.Blue
        return result
    }
}