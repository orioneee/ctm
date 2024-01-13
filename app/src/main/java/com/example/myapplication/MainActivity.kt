package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.github.orioneee.ColorMode
import com.github.orioneee.Ctm

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
                        LazyVerticalGrid(
                            columns = GridCells.Fixed(3),
                            modifier = Modifier.fillMaxSize()
                        ) {
                            Ctm.AllColorModes.forEach { colorMode ->
                                item {
                                    OptionCard(
                                        isSelected = colorMode == Ctm.currentColorMode.value,
                                        background = colorMode.theme.light.primary,
                                        onClick = {
                                            Ctm.setColorMode(colorMode)
                                        }
                                    )
                                }
                            }
                            item{
                                Button(onClick = { /*TODO*/ }) {
                                    Text("Button")
                                }
                            }
                            item{
                                Button(onClick = { /*TODO*/ }) {
                                    Text("Button")
                                }
                            }
                            item{
                                Button(onClick = { /*TODO*/ }) {
                                    Text("Button")
                                }
                            }
                            item{
                                Button(onClick = { /*TODO*/ }) {
                                    Text("Button")
                                }
                            }
                            item{
                                Button(onClick = { /*TODO*/ }) {
                                    Text("Button")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun OptionCard(
    isSelected: Boolean,
    background: Color,
    onClick: () -> Unit,
) {
    val backgroundColor = if (isSelected) {
        MaterialTheme.colorScheme.primary.copy(0.2f)
    } else {
        Color.Transparent
    }
    Box(
        modifier = Modifier
            .width(105.dp)
            .height(100.dp)
            .clip(RoundedCornerShape(15.dp))
            .background(backgroundColor)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Card(
            colors = CardDefaults.cardColors(containerColor = background),
            modifier = Modifier.size(70.dp)
        ) {}
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