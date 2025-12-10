package com.example.moviemaniac.core.particleSystem

import androidx.compose.ui.graphics.Color

data class Particle(
    var x: Float,                   // X position
    var y: Float,                   // Y position
    var radius: Float,              // Size of particles
    var speedX: Float,              // Horizontal Speed
    var speedY: Float,              // Vertical Speed
    var alpha: Float,               // Transparency (0f to 1f)
    var color: Color,               // Particle Color
    var life: Float,                // Particle Life (0f to 1f)
    var maxLife: Float,             // Maximum Life of Particle
    var isGlowing: Boolean = false  // Whether the particle is glowing

)