package dev.hybridlabs.aquatic.entity.misc

import dev.hybridlabs.aquatic.entity.HAEntityTypes
import net.minecraft.nbt.CompoundTag
import net.minecraft.util.Mth
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile
import net.minecraft.world.level.GameRules
import net.minecraft.world.level.Level
import net.minecraft.world.phys.EntityHitResult
import net.minecraft.world.phys.HitResult
import software.bernie.geckolib.animatable.GeoEntity
import software.bernie.geckolib.constant.DefaultAnimations
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache
import software.bernie.geckolib.core.animation.AnimatableManager
import software.bernie.geckolib.core.animation.AnimationController
import software.bernie.geckolib.util.GeckoLibUtil

class CavitationBubbleEntity : AbstractHurtingProjectile,
    GeoEntity {
    private val animCache = GeckoLibUtil.createInstanceCache(this)
    private var explosionPower = 1

    constructor(entityType: EntityType<out CavitationBubbleEntity?>, level: Level) : super(entityType, level)

    constructor(
        level: Level,
        shooter: LivingEntity,
        offsetX: Double,
        offsetY: Double,
        offsetZ: Double,
        explosionPower: Int,
    ) : super(
        HAEntityTypes.CAVITATION_BUBBLE.get(), shooter, offsetX, offsetY, offsetZ, level
    ) {
        this.explosionPower = explosionPower
    }

    override fun isOnFire(): Boolean {
        return false
    }

    override fun tick() {
        super.tick()

        val vec3d = this.deltaMovement
        val e: Double = vec3d.x
        val f: Double = vec3d.y
        val g: Double = vec3d.z
        val l: Double = vec3d.horizontalDistance()

        this.yRot = (Mth.atan2(e, g) * (180f / Math.PI.toFloat())).toFloat()
        this.xRot = (Mth.atan2(f, l) * (180f / Math.PI.toFloat())).toFloat()
        this.xRot = lerpRotation(this.xRotO, this.xRot)
        this.yRot = lerpRotation(this.yRotO, this.yRot)

        if (!this.level().isClientSide) {
            if (!isInWaterOrBubble) {
                this.discard()
            }
        }
    }

    override fun onHit(result: HitResult) {
        super.onHit(result)
        if (!this.level().isClientSide) {
            val flag = this.level().gameRules.getBoolean(GameRules.RULE_MOBGRIEFING)
            this.level().explode(
                this,
                this.x,
                this.y,
                this.z,
                this.explosionPower.toFloat(),
                flag,
                Level.ExplosionInteraction.MOB
            )
            this.discard()
        }
    }

    override fun onHitEntity(result: EntityHitResult) {
        super.onHitEntity(result)
        if (!this.level().isClientSide) {
            val entity = result.entity
            val entity1 = this.owner
            entity.hurt(this.damageSources().explosion(this, entity1), 6.0f)
            if (entity1 is LivingEntity) {
                this.doEnchantDamageEffects(entity1, entity)
            }
        }
    }

    override fun addAdditionalSaveData(compound: CompoundTag) {
        super.addAdditionalSaveData(compound)
        compound.putByte("ExplosionPower", this.explosionPower.toByte())
    }

    override fun readAdditionalSaveData(compound: CompoundTag) {
        super.readAdditionalSaveData(compound)
        if (compound.contains("ExplosionPower", 99)) {
            this.explosionPower = compound.getByte("ExplosionPower").toInt()
        }
    }

    override fun registerControllers(controllers: AnimatableManager.ControllerRegistrar) {
        controllers.add(
            AnimationController(this, "Cavitation Bubble Controller", 0) { state ->
                state.setAndContinue(DefaultAnimations.RUN)
            }
        )
    }

    override fun getAnimatableInstanceCache(): AnimatableInstanceCache? {
        return animCache
    }
}