package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.github.orioneee.ColorMode
import com.github.orioneee.Ctm
import com.github.orioneee.ThemeMode
import io.woong.compose.grid.SimpleGridCells
import io.woong.compose.grid.VerticalGrid
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Scaffold {
                    Surface(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(it),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        ThemeChooser()
                    }
                }
            }
        }
    }
}

@Composable
fun OptionGenerator(
    it: ColorMode,
    isCurrent: Boolean
) {
    val palette = it.theme.light
    val colorOpt by animateColorAsState(
        if (isCurrent) MaterialTheme.colorScheme.primary.copy(alpha = 0.2f)
        else Color.Transparent, label = ""
    )
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
        contentAlignment = Alignment.Center
    ){
        Box(
            modifier = Modifier
                .width(105.dp)
                .height(100.dp)
                .clip(RoundedCornerShape(15.dp))
                .background(colorOpt)
                .clickable { Ctm.setColorMode(it) }
            ,
            contentAlignment = Alignment.Center
        ) {
            if (it != ColorMode.Dynamic) {
                Card(
                    colors = CardDefaults.cardColors(containerColor = palette.primary),
                    modifier = Modifier.size(70.dp)
                ) {}
            } else {
                ElevatedCard(
                    modifier = Modifier.size(70.dp)
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                    }
                }
            }
        }
    }
}

@Composable
fun ThemeModeOptionGenerator(
    mode: ThemeMode,
    isCurrent: Boolean,
) {
    val colorOpt by animateColorAsState(
        targetValue = if (isCurrent) MaterialTheme.colorScheme.primary.copy(alpha = 0.2f) else Color.Transparent,
        label = ""
    )
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(15.dp))
            .background(colorOpt)
            .clickable {
                Ctm.setThemeMode(mode)
            }
            .padding(15.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        ElevatedCard(
            colors = CardDefaults.elevatedCardColors(
                when (mode) {
                    ThemeMode.System -> {
                        if (isSystemInDarkTheme()) {
                            Ctm.ChousedDarkColorScheme.background
                        } else {
                            Ctm.ChousedLightColorScheme.background
                        }
                    }

                    ThemeMode.Light -> Ctm.ChousedLightColorScheme.background
                    ThemeMode.Dark -> Ctm.ChousedDarkColorScheme.background
                }
            ),
            modifier = Modifier
                .width(75.dp)
                .height(70.dp),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        10.dp
                    ),
                contentAlignment = Alignment.Center
            ) {
            }
        }
        Text(
            "fadsfsdf"
        )
    }
    Spacer(modifier = Modifier.size(10.dp))
}

@Composable
fun ThemeChooser() {
    var sw by remember { mutableStateOf(true) }
    val context = LocalContext.current
    val coroutineScope = CoroutineScope(Dispatchers.IO)
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            val current by Ctm.currentThemeMode
            if (ThemeMode.System.isSupported) ThemeModeOptionGenerator(
                ThemeMode.System,
                ThemeMode.System == current
            )
            ThemeModeOptionGenerator(ThemeMode.Light, ThemeMode.Light == current)
            ThemeModeOptionGenerator(ThemeMode.Dark, ThemeMode.Dark == current)
            if (!ThemeMode.System.isSupported){
                Spacer(modifier = Modifier.size(105.dp))
            }
        }
        Spacer(modifier = Modifier.size(10.dp))
        val current by Ctm.currentColorMode
        VerticalGrid(
            columns = SimpleGridCells.Fixed(3),
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Ctm.AllColorModes.forEach {
                if (it.isSupported) {
                    OptionGenerator(it, it == current)
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Ctm.Theme {
        Ctm.setColorMode(Ctm.Colors.Blue)
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.primary)
        ) {}
        Greeting("Android")
    }
}