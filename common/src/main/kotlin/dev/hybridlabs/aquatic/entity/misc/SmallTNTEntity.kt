package dev.hybridlabs.aquatic.entity.misc

import dev.hybridlabs.aquatic.entity.HybridAquaticEntityTypes
import net.minecraft.core.particles.ParticleTypes
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.MoverType
import net.minecraft.world.entity.item.PrimedTnt
import net.minecraft.world.level.ExplosionDamageCalculator
import net.minecraft.world.level.Level
import kotlin.math.cos
import kotlin.math.sin

open class SmallTNTEntity(
    entityType: EntityType<out SmallTNTEntity>,
    level: Level
) : PrimedTnt(entityType, level) {

    constructor(level: Level, x: Double, y: Double, z: Double, owner: LivingEntity?): this(HybridAquaticEntityTypes.SMALL_TNT.get(), level) {
        this.setPos(x, y, z)
        val d0 = level.random.nextDouble() * (Math.PI.toFloat() * 2f)
        this.setDeltaMovement(-sin(d0) * 0.02, 0.2, -cos(d0) * 0.02)
        this.fuse = 100
        this.xo = x
        this.yo = y
        this.zo = z
        this.owner = owner
    }

    private var owner: LivingEntity? = null

    override fun getOwner(): LivingEntity? {
        return owner
    }

    override fun tick() {
        if (!this.isNoGravity) {
            this.deltaMovement = this.deltaMovement.add(0.0, -0.04, 0.0)
        }

        this.move(MoverType.SELF, this.deltaMovement)
        this.deltaMovement = this.deltaMovement.scale(0.98)
        if (this.onGround()) {
            this.deltaMovement = this.deltaMovement.multiply(0.7, -0.5, 0.7)
        }

        val i = this.fuse - 1
        this.fuse = i
        if (i <= 0) {
            this.discard()
            if (!this.level().isClientSide) {
                this.explodeTnt()
            }
        } else {
            this.updateInWaterStateAndDoFluidPushing()
            if (this.level().isClientSide) {
                this.level()
                    .addParticle(
                        ParticleTypes.SMOKE,
                        this.x, this.y + 0.5, this.z,
                        0.0, 0.0, 0.0)
            }
        }
    }

    private fun explodeTnt() {
        val radius = 2.0f

        this.level().explode(
            this, null, ExplosionDamageCalculator(),
            this.x, this.getY(0.0625), this.z,
            radius, false, Level.ExplosionInteraction.TNT
        )
    }
}
