package com.example.moviemaniac.core.particleSystem

import androidx.compose.ui.graphics.Color
import kotlin.math.cos
import kotlin.math.sin
import kotlin.random.Random

class ParticleSystem(
    private val width: Float,
    private val height: Float
) {
    private val particles = mutableListOf<Particle>()
    private val maxParticles = 300

    // Color palette for different vibes
    private val nightColors = listOf(
        Color(0xFF87CEEB),  // Light blue (stars)
        Color(0xFFFFFFFF),  // White (snow)
        Color(0xFFF0E68C),  // Khaki (warm stars)
        Color(0xFFE6E6FA),  // Lavender
        Color(0xFFB0E0E6)   // Powder blue
    )

    private val fireworkColors = listOf(
        Color(0xFFFF69B4),  // Hot pink
        Color(0xFF00FFFF),  // Cyan
        Color(0xFFFFFF00),  // Yellow
        Color(0xFF32CD32),  // Lime green
        Color(0xFFFF4500)   // Orange red
    )

    init {
        createInitialParticles()
    }

    private fun createInitialParticles() {
        repeat(maxParticles) {
            particles.add(createRandomParticles())
        }
    }

    private fun createRandomParticles(): Particle {
        val type = Random.nextInt(0, 3)
        return when (type) {
            0 -> createStarParticle()
            1 -> createSnowParticle()
            else -> createFireworkParticle()
        }
    }

    private fun createStarParticle(): Particle {
        return Particle(
            x = Random.nextFloat() * width,
            y = Random.nextFloat() * height,
            radius = Random.nextFloat() * 1.5f + 0.5f,
            speedX = Random.nextFloat() * 0.3f - 0.15f,
            speedY = Random.nextFloat() * 0.1f - 0.05f,
            alpha = Random.nextFloat() * 0.5f + 0.3f,
            color = nightColors[Random.nextInt(nightColors.size)],
            life = 1f,
            maxLife = Random.nextFloat() * 100 + 50f,
            isGlowing = Random.nextBoolean()
        )
    }

    private fun createSnowParticle(): Particle {
        return Particle(
            x = Random.nextFloat() * width,
            y = Random.nextFloat() * height,
            radius = Random.nextFloat() * 2f + 1f,
            speedX = Random.nextFloat() * 0.5f - 0.25f,
            speedY = Random.nextFloat() * 0.8f + 0.2f,
            alpha = Random.nextFloat() * 0.7f + 0.3f,
            color = Color.White.copy(alpha = 0.8f),
            life = 1f,
            maxLife = Random.nextFloat() * 80 + 40f,
            isGlowing = false
        )
    }

    private fun createFireworkParticle(): Particle {
        val angle = Random.nextFloat() * 2f * Math.PI.toFloat()
        val speed = Random.nextFloat() * 0.8f + 0.2f

        val startX = Random.nextFloat() * width
        val startY = Random.nextFloat() * height

        return Particle(
            x = startX,
            y = startY,
            radius = Random.nextFloat() * 2f + 1f,
            speedX = cos(angle) * speed,
            speedY = sin(angle) * speed,
            alpha = 1f,
            color = fireworkColors[Random.nextInt(fireworkColors.size)],
            life = 1f,
            maxLife = Random.nextFloat() * 60 + 30f,
            isGlowing = true
        )
    }

    private fun createFireworkBurst(): List<Particle> {
        val burst = mutableListOf<Particle>()
        val startX = Random.nextFloat() * width
        val startY = Random.nextFloat() * height
        val colors = fireworkColors

        repeat(30) { // number of sparks in burst
            val angle = Random.nextFloat() * 2f * Math.PI.toFloat()
            val speed = Random.nextFloat() * 1.2f + 0.2f

            burst.add(
                Particle(
                    x = startX,
                    y = startY,
                    radius = Random.nextFloat() * 2f + 1f,
                    speedX = cos(angle) * speed,
                    speedY = sin(angle) * speed,
                    alpha = 1f,
                    color = colors.random(),
                    life = 1f,
                    maxLife = Random.nextFloat() * 60 + 30f,
                    isGlowing = true
                )
            )
        }
        return burst
    }

    fun update(deltaTime: Float) {

        val newList = mutableListOf<Particle>()

        particles.forEach { particle ->

            // update particle position
            particle.x += particle.speedX
            particle.y += particle.speedY

            // update life
            particle.life -= 1f / particle.maxLife

            // Adding some randomness to movement
            particle.speedX += (Random.nextFloat() - 0.5f) * 0.01f
            particle.speedY += (Random.nextFloat() - 0.5f) * 0.01f

            // Applying damping
            particle.speedX *= 0.99f
            particle.speedY *= 0.99f

            // Boundary checking with wrap around
            if (particle.x < 0) particle.x = width
            if (particle.x > width) particle.x = 0f
            if (particle.y < 0) particle.y = height
            if (particle.y > height) particle.y = 0f

            // Respawning particles that have dies
            if (particle.life < 0) {
                // 0.1% chance of a firework burst
                if (Random.nextFloat() < 0.001) {
                    val burst = createFireworkBurst()

                    // Adding only as many as allowed to maintain maxParticles
                    for (b in burst) {
                        if (newList.size < maxParticles)
                            newList.add(b)
                    }
                } else {
                    // Normal Respawn
                    if (newList.size < maxParticles)
                        newList.add(createRandomParticles())
                }
            } else {
                // Keeping living particles
                if (newList.size < maxParticles)
                    newList.add(particle)
            }
        }

        // Replacing original list with new fixed-size list
        particles.clear()
        particles.addAll(newList)
    }

    fun getParticles(): List<Particle> = particles
}