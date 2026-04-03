package dev.hybridlabs.aquatic.entity.base

import net.minecraft.advancements.CriteriaTriggers
import net.minecraft.core.particles.ParticleOptions
import net.minecraft.core.particles.ParticleTypes
import net.minecraft.nbt.CompoundTag
import net.minecraft.network.syncher.EntityDataAccessor
import net.minecraft.network.syncher.EntityDataSerializers
import net.minecraft.network.syncher.SynchedEntityData
import net.minecraft.server.level.ServerPlayer
import net.minecraft.server.players.OldUsersConverter
import net.minecraft.world.damagesource.DamageSource
import net.minecraft.world.entity.*
import net.minecraft.world.entity.player.Player
import net.minecraft.world.level.GameRules
import net.minecraft.world.level.Level
import net.minecraft.world.scores.Team
import software.bernie.geckolib.animatable.GeoEntity
import java.util.*

abstract class HATameableWaterAnimal(type: EntityType<out HATameableWaterAnimal>, world: Level) :
    HAWaterAnimal(type, world), GeoEntity, OwnableEntity {
    val DATA_FLAGS_ID: EntityDataAccessor<Byte>? = null
    val DATA_OWNERUUID_ID: EntityDataAccessor<Optional<UUID>>? = null
    private var orderedToSit = false

    init {
        this.reassessTameGoals()
    }

    override fun defineSynchedData() {
        super.defineSynchedData()
        this.entityData.define(DATA_FLAGS_ID, 0.toByte())
        this.entityData.define(DATA_OWNERUUID_ID, Optional.empty<UUID>())
    }

    override fun addAdditionalSaveData(compound: CompoundTag) {
        super.addAdditionalSaveData(compound)
        if (this.ownerUUID != null) {
            compound.putUUID("Owner", this.ownerUUID)
        }

        compound.putBoolean("Sitting", this.orderedToSit)
    }

    override fun readAdditionalSaveData(compound: CompoundTag) {
        super.readAdditionalSaveData(compound)
        val uuid: UUID?
        if (compound.hasUUID("Owner")) {
            uuid = compound.getUUID("Owner")
        } else {
            val s = compound.getString("Owner")
            uuid = OldUsersConverter.convertMobOwnerIfNecessary(this.server, s)
        }

        if (uuid != null) {
            try {
                this.setOwnerUUID(uuid)
                this.setTame(true)
            } catch (var4: Throwable) {
                this.setTame(false)
            }
        }

        this.orderedToSit = compound.getBoolean("Sitting")
        this.setInSittingPose(this.orderedToSit)
    }

    override fun canBeLeashed(player: Player): Boolean {
        return !this.isLeashed
    }

    protected fun spawnTamingParticles(tamed: Boolean) {
        var particleoptions: ParticleOptions = ParticleTypes.HEART
        if (!tamed) {
            particleoptions = ParticleTypes.SMOKE
        }

        for (i in 0..6) {
            val d0 = this.random.nextGaussian() * 0.02
            val d1 = this.random.nextGaussian() * 0.02
            val d2 = this.random.nextGaussian() * 0.02
            this.level().addParticle(
                particleoptions,
                this.getRandomX(1.0),
                this.randomY + 0.5,
                this.getRandomZ(1.0),
                d0,
                d1,
                d2
            )
        }
    }

    override fun handleEntityEvent(id: Byte) {
        if (id.toInt() == 7) {
            this.spawnTamingParticles(true)
        } else if (id.toInt() == 6) {
            this.spawnTamingParticles(false)
        } else {
            super.handleEntityEvent(id)
        }
    }

    fun isTame(): Boolean {
        return ((this.entityData.get(DATA_FLAGS_ID) as Byte).toInt() and 4) != 0
    }

    open fun setTame(tamed: Boolean) {
        val b0 = this.entityData.get<Byte>(DATA_FLAGS_ID) as Byte
        if (tamed) {
            this.entityData.set(DATA_FLAGS_ID, (b0.toInt() or 4).toByte())
        } else {
            this.entityData.set(DATA_FLAGS_ID, (b0.toInt() and -5).toByte())
        }

        this.reassessTameGoals()
    }

    protected open fun reassessTameGoals() {
    }

    fun isInSittingPose(): Boolean {
        return ((this.entityData.get(DATA_FLAGS_ID) as Byte).toInt() and 1) != 0
    }

    fun setInSittingPose(sitting: Boolean) {
        val b0 = this.entityData.get<Byte>(DATA_FLAGS_ID) as Byte
        if (sitting) {
            this.entityData.set(DATA_FLAGS_ID, (b0.toInt() or 1).toByte())
        } else {
            this.entityData.set(DATA_FLAGS_ID, (b0.toInt() and -2).toByte())
        }
    }

    override fun getOwnerUUID(): UUID? {
        return (this.entityData.get(DATA_OWNERUUID_ID) as Optional<*>).orElse(null as UUID?) as UUID?
    }

    fun setOwnerUUID(uuid: UUID?) {
        this.entityData.set(DATA_OWNERUUID_ID, Optional.ofNullable<UUID>(uuid))
    }

    fun tame(player: Player) {
        this.setTame(true)
        this.setOwnerUUID(player.getUUID())
        if (player is ServerPlayer) {
            CriteriaTriggers.TAME_ANIMAL.trigger(player, this)
        }
    }

    override fun canAttack(target: LivingEntity): Boolean {
        return if (this.isOwnedBy(target)) false else super.canAttack(target)
    }

    fun isOwnedBy(entity: LivingEntity?): Boolean {
        return entity === this.owner
    }

    open fun wantsToAttack(target: LivingEntity?, owner: LivingEntity?): Boolean {
        return true
    }

    override fun getTeam(): Team? {
        if (this.isTame()) {
            val livingentity = this.owner
            if (livingentity != null) {
                return livingentity.team
            }
        }

        return super.getTeam()
    }

    override fun isAlliedTo(entity: Entity): Boolean {
        if (this.isTame()) {
            val livingentity = this.owner
            if (entity === livingentity) {
                return true
            }

            if (livingentity != null) {
                return livingentity.isAlliedTo(entity)
            }
        }

        return super.isAlliedTo(entity)
    }

    override fun die(cause: DamageSource) {
        if (!this.level().isClientSide && this.level().gameRules
                .getBoolean(GameRules.RULE_SHOWDEATHMESSAGES) && this.owner is ServerPlayer
        ) {
            this.owner!!.sendSystemMessage(this.combatTracker.deathMessage)
        }

        super.die(cause)
    }

    fun isOrderedToSit(): Boolean {
        return this.orderedToSit
    }

    fun setOrderedToSit(orderedToSit: Boolean) {
        this.orderedToSit = orderedToSit
    }

    companion object {
        val DATA_FLAGS_ID: EntityDataAccessor<Byte> =
            SynchedEntityData.defineId(HATameableWaterAnimal::class.java, EntityDataSerializers.BYTE)
        val DATA_OWNERUUID_ID: EntityDataAccessor<Optional<UUID>> =
            SynchedEntityData.defineId(HATameableWaterAnimal::class.java, EntityDataSerializers.OPTIONAL_UUID)
    }
}