package dev.hybridlabs.aquatic.block.entity

import dev.hybridlabs.aquatic.entity.fish.ClownfishEntity
import net.minecraft.core.BlockPos
import net.minecraft.nbt.CompoundTag
import net.minecraft.nbt.Tag
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket
import net.minecraft.world.entity.EntityType
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.phys.Vec3
import software.bernie.geckolib.core.animatable.GeoAnimatable
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache
import software.bernie.geckolib.core.animation.AnimatableManager
import software.bernie.geckolib.core.animation.Animation
import software.bernie.geckolib.core.animation.AnimationController
import software.bernie.geckolib.core.animation.AnimationState
import software.bernie.geckolib.core.animation.RawAnimation
import software.bernie.geckolib.core.`object`.PlayState
import software.bernie.geckolib.util.GeckoLibUtil
import software.bernie.geckolib.util.RenderUtils
import java.util.function.Function

class AnemoneBlockEntity(pos: BlockPos, state: BlockState) :
    BlockEntity(HybridAquaticBlockEntityTypes.ANEMONE.get(), pos, state), GeoAnimatable {
    private val factory = GeckoLibUtil.createInstanceCache(this)

    private var hideTimer = 0
    private var cooldownTimer = 0
    private var hiddenClownfish: CompoundTag? = null

    fun tick(world: Level) {
        if (world.isClientSide) {
            return
        }

        val clownfish = hiddenClownfish
        if (clownfish != null) {
            if (hideTimer > 0) {
                hideTimer--

                // end timer and release clownfish
                if (hideTimer <= 0) {
                    releaseClownfish(Vec3.atCenterOf(worldPosition).add(0.0, 1.0, 0.0))
                    resetHiddenClownfish()
                }

                // update nbt
                setChanged()
            }
        } else {
            if (cooldownTimer > 0) {
                cooldownTimer--

                // update nbt
                setChanged()
            }
        }
    }

    fun hideClownfish(clownfish: ClownfishEntity): Boolean {
        if (cooldownTimer > 0) {
            return false
        }

        if (hiddenClownfish != null) {
            return false
        }

        // serialize clownfish
        clownfish.stopRiding()
        clownfish.ejectPassengers()
        hiddenClownfish = CompoundTag().apply(clownfish::addAdditionalSaveData)

        // start timer
        hideTimer = HIDE_DURATION

        // update nbt
        setChanged()

        return true
    }

    private fun releaseClownfish(releasePosition: Vec3): Boolean {
        val world = level ?: return false
        val hiddenNbt = hiddenClownfish ?: return false

        val nbt = hiddenNbt.copy()
        removeIrrelevantNbtKeys(nbt)

        val clownfish = EntityType.loadEntityRecursive(nbt, world, Function.identity())
        if (clownfish !is ClownfishEntity) {
            return false
        }

        clownfish.moveTo(releasePosition.x, releasePosition.y, releasePosition.z, 0f, 0f)
        level!!.addFreshEntity(clownfish)

        return true
    }

    fun emergencyReleaseHiddenClownfish() {
        releaseClownfish(Vec3.atCenterOf(worldPosition))
    }

    private fun resetHiddenClownfish() {
        hiddenClownfish = null
        hideTimer = 0
        cooldownTimer = COOLDOWN_DURATION
    }

    private fun <E> predicate(event: AnimationState<E>): PlayState where E : BlockEntity?, E : GeoAnimatable {
        return if (level != null) {
            event.controller.setAnimation(SWAY_ANIMATION)
            PlayState.CONTINUE
        } else {
            PlayState.STOP
        }
    }

    override fun registerControllers(controllerRegistrar: AnimatableManager.ControllerRegistrar) {
        controllerRegistrar.add(AnimationController(this, "controller", 0, ::predicate))
    }

    override fun getAnimatableInstanceCache(): AnimatableInstanceCache {
        return factory
    }

    override fun getTick(o: Any): Double {
        return RenderUtils.getCurrentTick()
    }

    override fun getUpdateTag(): CompoundTag {
        return saveWithoutMetadata()
    }

    override fun saveAdditional(nbt: CompoundTag) {
        hiddenClownfish?.let { clownfishNbt -> nbt.put("clownfish", clownfishNbt) }

        nbt.putInt("hide_timer", hideTimer)
        nbt.putInt("cooldown_timer", cooldownTimer)
    }

    override fun load(nbt: CompoundTag) {
        if (nbt.contains("clownfish", Tag.TAG_COMPOUND.toInt())) {
            val clownfishNbt = nbt.getCompound("clownfish")
            hiddenClownfish = clownfishNbt
        }

        hideTimer = nbt.getInt("hide_timer")
        cooldownTimer = nbt.getInt("cooldown_timer")
    }

    override fun getUpdatePacket(): ClientboundBlockEntityDataPacket {
        return ClientboundBlockEntityDataPacket.create(this)
    }

    companion object {
        private const val HIDE_DURATION = 200
        private const val COOLDOWN_DURATION = 40
        val SWAY_ANIMATION: RawAnimation = RawAnimation.begin().then("sway", Animation.LoopType.LOOP)

        private val IRRELEVANT_NBT_KEYS: List<String> = listOf(
            "Air",
            "ArmorDropChances",
            "ArmorItems",
            "Brain",
            "CanPickUpLoot",
            "DeathTime",
            "FallDistance",
            "FallFlying",
            "Fire",
            "HandDropChances",
            "HandItems",
            "HurtByTimestamp",
            "HurtTime",
            "LeftHanded",
            "Motion",
            "NoGravity",
            "OnGround",
            "PortalCooldown",
            "Pos",
            "Rotation",
            "Passengers",
            "Leash",
            "UUID"
        )

        fun tick(world: Level, blockEntity: AnemoneBlockEntity) {
            blockEntity.tick(world)
        }

        fun removeIrrelevantNbtKeys(compound: CompoundTag) {
            IRRELEVANT_NBT_KEYS.forEach(compound::remove)
        }
    }
}
