package dev.hybridlabs.aquatic.particle

import net.minecraft.client.multiplayer.ClientLevel
import net.minecraft.client.particle.*
import net.minecraft.core.particles.SimpleParticleType
import kotlin.math.cos
import kotlin.math.min
import kotlin.math.pow
import kotlin.math.sin

class SargassumParticle(world: ClientLevel, x: Double, y: Double, z: Double): TextureSheetParticle(world, x, y, z) {
    private var rotSpeed: Float = Math.toRadians(if (this.random.nextBoolean()) -30.0 else 30.0).toFloat()
    private val particleRandom: Float = this.random.nextFloat()
    private val spinAcceleration: Float = Math.toRadians(if (this.random.nextBoolean()) -5.0 else 5.0).toFloat()

    init {
        this.lifetime = 300
        this.gravity = 2.5E-4f
        val f = if (this.random.nextBoolean()) 0.05f else 0.075f
        this.quadSize = f
        this.setSize(f, f)
        this.friction = 1.0f
    }

    override fun getRenderType(): ParticleRenderType {
        return ParticleRenderType.PARTICLE_SHEET_OPAQUE
    }

    override fun tick() {
        this.xo = this.x
        this.yo = this.y
        this.zo = this.z
        if (this.lifetime-- <= 0) {
            this.remove()
        }

        if (!this.removed) {
            val f = (300 - this.lifetime).toFloat()
            val f1 = min(f / 300.0f, 1.0f)
            val d0 = cos(Math.toRadians((this.particleRandom * 60.0f).toDouble())) * 0.5 * f1.toDouble().pow(1.25)
            val d1 = sin(Math.toRadians((this.particleRandom * 60.0f).toDouble())) * 0.5 * f1.toDouble().pow(1.25)
            this.xd += d0 * 0.0025
            this.zd += d1 * 0.0025
            this.yd -= this.gravity.toDouble()
            this.rotSpeed += this.spinAcceleration / 20.0f
            this.oRoll = this.roll
            this.roll += this.rotSpeed / 20.0f
            this.move(this.xd, this.yd, this.zd)
            if (this.onGround || this.lifetime < 299 && (this.xd == 0.0 || this.zd == 0.0)) {
                this.remove()
            }

            if (!this.removed) {
                this.xd *= this.friction.toDouble()
                this.yd *= this.friction.toDouble()
                this.zd *= this.friction.toDouble()
            }
        }
    }

    companion object {
        class Provider(private val sprite: SpriteSet) : ParticleProvider<SimpleParticleType> {
            override fun createParticle(
                type: SimpleParticleType, level: ClientLevel,
                x: Double, y: Double, z: Double,
                xSpeed: Double, ySpeed: Double, zSpeed: Double
            ): Particle {
                val sargassumParticle = SargassumParticle(level, x, y, z)
                sargassumParticle.pickSprite(this.sprite)
                return sargassumParticle
            }
        }
    }
}