package dev.hybridlabs.aquatic.entity.misc

import net.minecraft.world.damagesource.DamageSource

import net.minecraft.server.level.ServerLevel

import net.minecraft.world.level.storage.ValueInput
import net.minecraft.world.level.storage.ValueOutput

import dev.hybridlabs.aquatic.entity.HAEntityTypes
import dev.hybridlabs.aquatic.world.UnderwaterExplosionDamageCalculator
import net.minecraft.core.particles.ParticleTypes
import net.minecraft.network.syncher.EntityDataAccessor
import net.minecraft.network.syncher.EntityDataSerializers
import net.minecraft.network.syncher.SynchedEntityData
import net.minecraft.world.entity.*
import net.minecraft.world.level.Level
import kotlin.math.cos
import kotlin.math.sin

open class PrimedDepthChargeEntity(
    entityType: EntityType<out PrimedDepthChargeEntity>,
    level: Level,
) : Entity(entityType, level), TraceableEntity {

    constructor(level: Level, x: Double, y: Double, z: Double, owner: LivingEntity?)
            : this(HAEntityTypes.DEPTH_CHARGE.get(), level) {
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

    override fun getMovementEmission(): MovementEmission {
        return MovementEmission.NONE
    }

    override fun isPickable(): Boolean {
        return !this.isRemoved
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
                this.explode()
            }
        } else {
            this.updateFluidInteraction()
            if (this.level().isClientSide) {
                this.level()
                    .addParticle(ParticleTypes.BUBBLE_COLUMN_UP, this.x, this.y + 0.5, this.z, 0.0, 0.0, 0.0)
            }
        }
    }

    private fun explode() {
        val radius = 4.0f

        this.level().explode(
            this,
            null,
            UnderwaterExplosionDamageCalculator(),
            this.x,
            this.getY(0.0625),
            this.z,
            radius,
            false,
            Level.ExplosionInteraction.TNT
        )
    }

    //#region Data
    override fun defineSynchedData(builder: SynchedEntityData.Builder) {
        builder.define(DATA_FUSE_ID, 100)
    }

    override fun addAdditionalSaveData(output: ValueOutput) {
        output.putShort("Fuse", this.fuse.toShort())
    }

    override fun readAdditionalSaveData(input: ValueInput) {
        this.fuse = input.getShortOr("Fuse", this.fuse.toShort()).toInt()
    }
    //#endregion

    override fun hurtServer(level: ServerLevel, source: DamageSource, amount: Float): Boolean {
        return false
    }

    override fun getOwner(): LivingEntity? {
        return this.owner
    }

    var fuse: Int
        get() = this.entityData.get(DATA_FUSE_ID) as Int
        set(life) { this.entityData.set(DATA_FUSE_ID, life)
        }

    init {
        this.blocksBuilding = true
    }

    companion object {
        private val DATA_FUSE_ID: EntityDataAccessor<Int> =
            SynchedEntityData.defineId(PrimedDepthChargeEntity::class.java, EntityDataSerializers.INT)
    }
}