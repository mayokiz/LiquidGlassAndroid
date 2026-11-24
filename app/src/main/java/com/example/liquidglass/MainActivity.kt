package com.example.liquidglass

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kyant.backdrop.backdrops.layerBackdrop
import com.kyant.backdrop.backdrops.rememberLayerBackdrop
import com.kyant.backdrop.drawBackdrop
import com.kyant.backdrop.effects.blur
import com.kyant.backdrop.effects.lens
import com.kyant.backdrop.effects.vibrancy
import com.kyant.backdrop.highlight.Highlight
import com.kyant.shapes.RoundedRectangle

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                LiquidGlassScreen()
            }
        }
    }
}

@Composable
fun LiquidGlassScreen() {
    val backdrop = rememberLayerBackdrop()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF090B18))
    ) {
        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .layerBackdrop(backdrop)
        ) {
            drawRect(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFF182D59),
                        Color(0xFF522E72),
                        Color(0xFF101A35),
                        Color(0xFF090B18)
                    )
                )
            )

            drawCircle(
                color = Color(0xFF597DFF),
                radius = 150.dp.toPx(),
                center = Offset(
                    size.width * 0.15f,
                    size.height * 0.25f
                )
            )

            drawCircle(
                color = Color(0xFFB05DDA),
                radius = 130.dp.toPx(),
                center = Offset(
                    size.width * 0.85f,
                    size.height * 0.55f
                )
            )

            drawCircle(
                color = Color(0xFF286F9C),
                radius = 110.dp.toPx(),
                center = Offset(
                    size.width * 0.25f,
                    size.height * 0.85f
                )
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Liquid Glass",
                color = Color.White,
                fontSize = 34.sp
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "A glassmorphism playground",
                color = Color.White.copy(alpha = 0.7f),
                fontSize = 15.sp
            )

            Spacer(modifier = Modifier.height(48.dp))

            GlassPanel(backdrop)

            Spacer(modifier = Modifier.height(24.dp))

            GlassPanel(backdrop)

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "REFRACTION  •  BLUR  •  DEPTH",
                color = Color.White.copy(alpha = 0.65f),
                fontSize = 11.sp,
                letterSpacing = 2.sp
            )
        }
    }
}

@Composable
fun GlassPanel(backdrop: com.kyant.backdrop.backdrops.LayerBackdrop) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(125.dp)
            .drawBackdrop(
                backdrop = backdrop,
                shape = {
                    RoundedRectangle(28.dp)
                },
                effects = {
                    vibrancy()
                    blur(12.dp.toPx())
                    lens(
                        refractionHeight = 24.dp.toPx(),
                        refractionAmount = 18.dp.toPx(),
                        depthEffect = true,
                        chromaticAberration = true
                    )
                },
                highlight = {
                    Highlight.Plain
                }
            )
            .background(
                Color.White.copy(alpha = 0.07f),
                RoundedCornerShape(28.dp)
            )
            .padding(22.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Glass surface",
            color = Color.White,
            fontSize = 20.sp
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Light bends through this surface.",
            color = Color.White.copy(alpha = 0.75f),
            fontSize = 13.sp
        )
    }
}
