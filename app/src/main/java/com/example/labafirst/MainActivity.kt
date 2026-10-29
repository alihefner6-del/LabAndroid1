package com.example.labafirst

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.labafirst.ui.theme.LabaFirstTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LabaFirstTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    SingleHelloWorldLab(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun SingleHelloWorldLab(modifier: Modifier = Modifier) {
    val words = listOf("HELLO", "WORLD")

    Row(
        modifier = modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        words.forEachIndexed { rowIndex, word ->
            // Чётное слово (WORLD) будет серым, нечётное (HELLO) — основного цвета
            val isEven = rowIndex % 2 == 1
            val baseColor = if (isEven) Color.LightGray else MaterialTheme.colorScheme.primary

            // Случайные подсвеченные буквы только для чётного слова (WORLD)
            val highlightedIndices = remember(rowIndex) {
                if (isEven) {
                    val count = Random.nextInt(1, 3) // 1-2 случайные буквы
                    List(count) { Random.nextInt(word.length) }.toSet()
                } else {
                    emptySet()
                }
            }

            // Отрисовываем буквы слова
            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                word.forEachIndexed { charIndex, char ->
                    val boxColor = if (highlightedIndices.contains(charIndex)) {
                        Color.Yellow
                    } else {
                        baseColor
                    }

                    val textColor = if (boxColor == Color.Yellow || isEven) Color.Black else Color.White

                    Box(
                        modifier = Modifier
                            .size(32.dp)
                            .clip(RoundedCornerShape(4.dp))
                            .background(boxColor),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = char.toString(),
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = textColor
                        )
                    }
                }
            }

            // Небольшой отступ/пробел между HELLO и WORLD
            if (rowIndex < words.size - 1) {
                Box(modifier = Modifier.size(12.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SingleHelloWorldLabPreview() {
    LabaFirstTheme {
        SingleHelloWorldLab()
    }
}