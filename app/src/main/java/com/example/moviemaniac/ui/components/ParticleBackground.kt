package com.example.moviemaniac.ui.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moviemaniac.core.particleSystem.Particle
import com.example.moviemaniac.core.particleSystem.ParticleSystem

@Composable
fun ParticleBackground() {
    var particleSystem by remember { mutableStateOf<ParticleSystem?>(null) }
    var lastUpdateTime by remember { mutableStateOf(System.currentTimeMillis()) }

    // Creating a gradient background
    val gradientBrush = Brush.verticalGradient(
        colors = listOf(
            Color(0xFF000000), // Black
            Color(0xFF0A0A0A), // Slightly lighter black
            Color(0xFF1A1A1A)  // Dark grey hint for depth
        )
    )

    // Animating for breathing effect
    val breathingAnimation by rememberInfiniteTransition().animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(3000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(gradientBrush)
    ) {
        // Particle canvas with blur effect
        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .blur(radius = 4.dp)
        ) {
            if (particleSystem == null) {
                particleSystem = ParticleSystem(size.width, size.height)
            }

            val currentTime = System.currentTimeMillis()
            val deltaTime = (currentTime - lastUpdateTime) / 1000f

            particleSystem?.let { system ->
                system.update(deltaTime)
                lastUpdateTime = currentTime

                // Drawing particles
                system.getParticles().forEach { particle ->
                    drawParticle(particle, breathingAnimation)
                }
            }
        }

        // Text overlay
        // Box () goes here or any composable components
    }
}

private fun DrawScope.drawParticle(particle: Particle, breathingValue: Float) {
    // Calculating current alpha with breathing effect
    val currentAlpha = particle.alpha * particle.life *
            (0.8f + 0.2f * breathingValue)

    // Drawing main particle
    drawCircle(
        color = particle.color.copy(alpha = currentAlpha),
        radius = particle.radius,
        center = Offset(particle.x, particle.y),
        blendMode = BlendMode.Plus // Makes color additive (brighter)
    )

    // Drawing glow effect for glowing particles
    if (particle.isGlowing) {
        val glowRadius = particle.radius * 3
        val glowAlpha = currentAlpha * 100f

        drawCircle(
            color = particle.color.copy(alpha = glowAlpha),
            radius = glowRadius,
            center = Offset(particle.x, particle.y),
            blendMode = BlendMode.Lighten
        )
    }

    // Drawing trail effect for moving particles
    val trailLength = 3
    for (i in 1..trailLength) {
        val trailAlpha = currentAlpha * (1f - i.toFloat() / trailLength) * 0.3f
        val trailX = particle.x + (particle.speedX * i)
        val trailY = particle.y + (particle.speedY * i)

        drawCircle(
            color = particle.color.copy(alpha = trailAlpha),
            radius = particle.radius * 0.7f,
            center = Offset(trailX, trailY),
        )
    }
}