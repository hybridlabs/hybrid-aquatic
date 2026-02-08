package dev.hybridlabs.aquatic.particle

import net.minecraft.client.multiplayer.ClientLevel
import net.minecraft.client.particle.*
import net.minecraft.core.BlockPos
import net.minecraft.core.particles.SimpleParticleType
import net.minecraft.tags.FluidTags
import net.minecraft.util.Mth

class WaterUpWhirlParticle(world: ClientLevel, x: Double, y: Double, z: Double): TextureSheetParticle(world, x, y, z) {
    private var angle: Float = 0f

    init {
        this.lifetime = (Math.random() * 60.0).toInt() + 30
        this.hasPhysics = false
        this.xd = 0.0
        this.yd = 0.05
        this.zd = 0.0
        this.setSize(0.02f, 0.02f)
        this.quadSize *= this.random.nextFloat() * 0.6f + 0.2f
        this.gravity = 0.002f
    }

    override fun getRenderType(): ParticleRenderType {
        return ParticleRenderType.PARTICLE_SHEET_OPAQUE
    }

    override fun tick() {
        this.xo = this.x
        this.yo = this.y
        this.zo = this.z
        if (this.age++ >= this.lifetime) {
            this.remove()
        } else {
            val f = 0.6f
            this.xd += (0.6f * Mth.cos(this.angle)).toDouble()
            this.zd += (0.6f * Mth.sin(this.angle)).toDouble()
            this.xd *= 0.07
            this.zd *= 0.07
            this.move(this.xd, this.yd, this.zd)
            if (!this.level.getFluidState(BlockPos.containing(this.x, this.y, this.z))
                    .`is`(FluidTags.WATER) || this.onGround
            ) {
                this.remove()
            }

            this.angle += 0.08f
        }
    }

    companion object {
        class Provider(private val sprite: SpriteSet) : ParticleProvider<SimpleParticleType> {
            override fun createParticle(
                type: SimpleParticleType, level: ClientLevel,
                x: Double, y: Double, z: Double,
                xSpeed: Double, ySpeed: Double, zSpeed: Double
            ): Particle {
                val waterUpWhirlParticle = WaterUpWhirlParticle(level, x, y, z)
                waterUpWhirlParticle.pickSprite(this.sprite)
                return waterUpWhirlParticle
            }
        }
    }
}