package ru.kyamshanov.sample

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toComposeImageBitmap
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.jetbrains.kotlinx.kandy.dsl.continuous
import org.jetbrains.kotlinx.kandy.dsl.invoke
import org.jetbrains.kotlinx.kandy.dsl.plot
import org.jetbrains.kotlinx.kandy.letsplot.export.toBufferedImage
import org.jetbrains.kotlinx.kandy.letsplot.feature.layout
import org.jetbrains.kotlinx.kandy.letsplot.layers.bars
import org.jetbrains.kotlinx.kandy.letsplot.layers.line
import org.jetbrains.kotlinx.kandy.letsplot.settings.LineType
import org.jetbrains.kotlinx.kandy.letsplot.x
import org.jetbrains.kotlinx.kandy.letsplot.y
import org.jetbrains.kotlinx.kandy.util.color.Color

@Composable
@Preview
fun App() {
    MaterialTheme {

        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            var xName by remember { mutableStateOf(true) }

            Button(onClick = {
                xName = !xName
            }) {
                Text("Click me!")
            }

            val simpleDataset = mapOf(
                "time" to listOf(0, 1, 2, 4, 5, 7, 8, 9),
                "day" to listOf(0, 1, 2, 4, 5, 7, 8, 9),
                "temperature" to listOf(12.0, 14.2, 15.1, 15.9, 17.9, 15.6, 14.2, if (xName) 10.1 else 24.3),
                "humidity" to listOf(0.5, 0.32, 0.11, 0.89, 0.68, 0.57, 0.56, 0.5)
            )
            val simplePlot =
                plot(simpleDataset) {
                    if (xName) {
                        x("time"<Int>())
                    } else {
                        x("day"<Int>())
                    }
                    y("temperature"<Double>()) {
                        scale = continuous(0.0..25.5)
                    }

                    bars {
                        fillColor("humidity"<Double>()) {
                            scale = continuous(
                                range = Color.YELLOW..Color.RED
                            )
                        }
                        borderLine.width = 0.0
                    }

                    line {
                        width = 3.0
                        color = Color.hex("#6e5596")
                        type = LineType.DOTDASH
                    }

                    layout {
                        title = "Simple plot with lets-plot"
                    }

                }
            Image(
                bitmap = simplePlot.toBufferedImage().toComposeImageBitmap(),
                contentDescription = "plot image"
            )
        }
    }
}