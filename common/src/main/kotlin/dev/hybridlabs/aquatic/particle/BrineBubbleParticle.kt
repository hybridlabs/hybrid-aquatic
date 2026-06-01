package dev.hybridlabs.aquatic.particle

import net.minecraft.client.multiplayer.ClientLevel
import net.minecraft.client.particle.*
import net.minecraft.core.BlockPos
import net.minecraft.core.particles.SimpleParticleType
import net.minecraft.tags.FluidTags

class BrineBubbleParticle internal constructor(
    level: ClientLevel,
    x: Double,
    y: Double,
    z: Double,
    xSpeed: Double,
    ySpeed: Double,
    zSpeed: Double,
) : TextureSheetParticle(level, x, y, z) {
    init {
        this.setSize(0.02f, 0.02f)
        this.quadSize *= this.random.nextFloat() * 0.6f + 0.2f
        this.xd = xSpeed * 0.2 + (Math.random() * 2.0 - 1.0) * 0.02
        this.yd = ySpeed * 0.2 + (Math.random() * 2.0 - 1.0) * 0.02
        this.zd = zSpeed * 0.2 + (Math.random() * 2.0 - 1.0) * 0.02
        this.lifetime = (100 * (0.8 + Math.random() * 0.4)).toInt()
    }

    override fun tick() {
        this.xo = this.x
        this.yo = this.y
        this.zo = this.z
        if (this.lifetime-- <= 0) {
            this.remove()
        } else {
            this.yd += 0.002
            this.move(this.xd, this.yd, this.zd)
            this.xd *= 0.85
            this.yd *= 0.85
            this.zd *= 0.85
            if (!this.level.getFluidState(BlockPos.containing(this.x, this.y, this.z)).`is`(FluidTags.WATER)) {
                this.remove()
            }
        }
    }

    override fun getRenderType(): ParticleRenderType {
        return ParticleRenderType.PARTICLE_SHEET_OPAQUE
    }

    companion object {
        class Provider(private val sprite: SpriteSet) : ParticleProvider<SimpleParticleType> {
            override fun createParticle(
                type: SimpleParticleType,
                level: ClientLevel,
                x: Double,
                y: Double,
                z: Double,
                xSpeed: Double,
                ySpeed: Double,
                zSpeed: Double,
            ): Particle {
                val bubbleParticle = BrineBubbleParticle(level, x, y, z, xSpeed, ySpeed, zSpeed)
                bubbleParticle.pickSprite(this.sprite)
                return bubbleParticle
            }
        }
    }
}