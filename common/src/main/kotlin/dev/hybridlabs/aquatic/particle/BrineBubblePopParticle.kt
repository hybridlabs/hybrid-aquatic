package dev.hybridlabs.aquatic.particle

import net.minecraft.client.multiplayer.ClientLevel
import net.minecraft.client.particle.*
import net.minecraft.util.RandomSource
import net.minecraft.core.particles.SimpleParticleType

class BrineBubblePopParticle(
    level: ClientLevel,
    x: Double,
    y: Double,
    z: Double,
    xSpeed: Double,
    ySpeed: Double,
    zSpeed: Double,
    private val sprites: SpriteSet,
) : SingleQuadParticle(level, x, y, z, sprites.get(0, 4)) {
    init {
        this.lifetime = 4
        this.gravity = 0.008f
        this.xd = xSpeed
        this.yd = ySpeed
        this.zd = zSpeed
        this.setSpriteFromAge(sprites)
    }

    override fun tick() {
        this.xo = this.x
        this.yo = this.y
        this.zo = this.z
        if (this.age++ >= this.lifetime) {
            this.remove()
        } else {
            this.yd -= this.gravity.toDouble()
            this.move(this.xd, this.yd, this.zd)
            this.setSpriteFromAge(this.sprites)
        }
    }

    companion object {
        class Provider(private val sprites: SpriteSet) : ParticleProvider<SimpleParticleType> {
            override fun createParticle(
                type: SimpleParticleType,
                level: ClientLevel,
                x: Double,
                y: Double,
                z: Double,
                xSpeed: Double,
                ySpeed: Double,
                zSpeed: Double,
                random: RandomSource,
            ): Particle {
                return BrineBubblePopParticle(level, x, y, z, xSpeed, ySpeed, zSpeed, this.sprites)
            }
        }
    }

    override fun getLayer(): SingleQuadParticle.Layer = SingleQuadParticle.Layer.OPAQUE
}
