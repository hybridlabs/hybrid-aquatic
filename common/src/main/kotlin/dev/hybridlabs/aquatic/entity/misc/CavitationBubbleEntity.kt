package dev.hybridlabs.aquatic.entity.misc

import dev.hybridlabs.aquatic.entity.HAEntityTypes
import net.minecraft.core.particles.ParticleOptions
import net.minecraft.core.particles.ParticleTypes
import net.minecraft.nbt.CompoundTag
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile
import net.minecraft.world.entity.projectile.ProjectileUtil
import net.minecraft.world.level.GameRules
import net.minecraft.world.level.Level
import net.minecraft.world.phys.EntityHitResult
import net.minecraft.world.phys.HitResult
import software.bernie.geckolib.animatable.GeoEntity
import software.bernie.geckolib.constant.DefaultAnimations
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache
import software.bernie.geckolib.core.animation.AnimatableManager
import software.bernie.geckolib.core.animation.AnimationController
import software.bernie.geckolib.core.animation.RawAnimation
import software.bernie.geckolib.core.`object`.PlayState
import software.bernie.geckolib.util.GeckoLibUtil

@Suppress("DEPRECATION")
class CavitationBubbleEntity : AbstractHurtingProjectile,
    GeoEntity {
    private val animCache = GeckoLibUtil.createInstanceCache(this)
    private var explosionPower = 1
    private var fuseDuration = -1
    private val trapBubble = this.deltaMovement.lengthSqr() < 0.0025

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

    override fun getTrailParticle(): ParticleOptions {
        return ParticleTypes.BUBBLE
    }

    override fun isOnFire(): Boolean {
        return false
    }

    override fun shouldBurn(): Boolean {
        return false
    }

    override fun tick() {
        val entity = this.owner
        if (this.level().isClientSide || (entity == null || !entity.isRemoved) && this.level()
                .hasChunkAt(this.blockPosition())
        ) {
            super.tick()
            if (this.shouldBurn()) {
                this.setSecondsOnFire(1)
            }

            val hitresult = ProjectileUtil.getHitResultOnMoveVector(
                this
            ) { target: Entity -> this.canHitEntity(target) }
            if (hitresult.type != HitResult.Type.MISS) {
                this.onHit(hitresult)
            }

            this.checkInsideBlocks()
            val vec3 = this.deltaMovement
            val d0 = this.x + vec3.x
            val d1 = this.y + vec3.y
            val d2 = this.z + vec3.z
            ProjectileUtil.rotateTowardsMovement(this, 0.2f)
            val f = this.inertia

            this.deltaMovement = vec3.add(this.xPower, this.yPower, this.zPower).scale(f.toDouble())
            this.level().addParticle(this.trailParticle, d0, d1 + 0.5, d2, 0.0, 0.0, 0.0)
            this.setPos(d0, d1, d2)
        } else {
            this.discard()
        }

        if (!this.level().isClientSide) {
            if (!isInWaterOrBubble) {
                this.discard()
                return
            }

            if (trapBubble) {
                val nearbyPlayer = this.level().getNearestPlayer(this, 5.0)

                if (nearbyPlayer != null) {

                    this.triggerAnim("explode_controller", "explode")

                    if (fuseDuration < 0) {
                        fuseDuration = 20
                    } else {
                        fuseDuration--

                        if (fuseDuration <= 0) {
                            explode()
                        }
                    }
                } else {
                    fuseDuration = -1
                }
            } else {
                fuseDuration = -1
            }
        }
    }

    private fun explode() {
        if (!this.level().isClientSide) {
            val flag = this.level().gameRules.getBoolean(GameRules.RULE_MOBGRIEFING)

            this.level().explode(
                this,
                this.x,
                this.y,
                this.z,
                4.0f,
                flag,
                Level.ExplosionInteraction.NONE
            )

            this.discard()
        }
    }

    override fun onHit(result: HitResult) {
        super.onHit(result)
        explode()
    }

    override fun onHitEntity(result: EntityHitResult) {
        super.onHitEntity(result)
        if (!this.level().isClientSide) {
            val entity = result.entity
            val entity1 = this.owner
            entity.hurt(this.damageSources().explosion(this, entity1), 8.0f)
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
        controllers.add(DefaultAnimations.genericSwimIdleController(this))

        controllers.add(
            AnimationController(this, "explode_controller") { PlayState.STOP }
                .triggerableAnim("explode", EXPLODE_ANIMATION)
        )
    }

    override fun getAnimatableInstanceCache(): AnimatableInstanceCache? {
        return animCache
    }

    companion object {
        val EXPLODE_ANIMATION: RawAnimation = RawAnimation.begin().thenPlay("misc.explode")
    }
}