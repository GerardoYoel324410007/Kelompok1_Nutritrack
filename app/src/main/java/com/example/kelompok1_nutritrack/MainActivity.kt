package com.example.kelompok1_nutritrack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme

private val NutriTrackColorScheme = lightColorScheme(
    primary = StitchColors.Primary,
    onPrimary = StitchColors.OnPrimary,
    secondary = StitchColors.Secondary,
    tertiary = StitchColors.Tertiary,
    background = StitchColors.Surface,
    surface = StitchColors.Surface,
    error = StitchColors.Error
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme(colorScheme = NutriTrackColorScheme) {
                NutriTrackScreen()
            }
        }
    }
}