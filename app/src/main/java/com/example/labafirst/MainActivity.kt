package com.example.labafirst

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.labafirst.ui.theme.LabaFirstTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LabaFirstTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(words: List<String> = listOf("JETPACK", "COMPOSE", "ANDROID", "KOTLIN", "STUDENT"), modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        words.forEachIndexed { rowIndex, word ->
            val isEvenRow = (rowIndex + 1) % 2 == 0

            val yellowIndices = remember(word, rowIndex) {
                if (isEvenRow && word.isNotEmpty()) {
                    val count = Random.nextInt(1, minOf(3, word.length + 1))
                    (0 until word.length).shuffled().take(count).toSet()
                } else {
                    emptySet()
                }
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                word.forEachIndexed { charIndex, char ->
                    val boxBackgroundColor = when {
                        !isEvenRow -> MaterialTheme.colorScheme.primary
                        charIndex in yellowIndices -> Color.Yellow
                        else -> Color.Gray
                    }

                    val textColor = if (boxBackgroundColor == Color.Yellow) Color.Black else MaterialTheme.colorScheme.onPrimary

                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .background(boxBackgroundColor),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = char.toString(),
                            color = if (isEvenRow && boxBackgroundColor != Color.Yellow) Color.White else textColor,
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LabaFirstTheme {
        Greeting()
    }
}