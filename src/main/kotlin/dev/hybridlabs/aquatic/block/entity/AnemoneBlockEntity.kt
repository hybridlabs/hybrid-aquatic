package dev.hybridlabs.aquatic.block.entity

import dev.hybridlabs.aquatic.entity.fish.ClownfishEntity
import net.minecraft.block.BlockState
import net.minecraft.block.entity.BlockEntity
import net.minecraft.entity.EntityType
import net.minecraft.nbt.NbtCompound
import net.minecraft.nbt.NbtElement
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Vec3d
import net.minecraft.world.World
import software.bernie.geckolib3.core.IAnimatable
import software.bernie.geckolib3.core.PlayState
import software.bernie.geckolib3.core.builder.AnimationBuilder
import software.bernie.geckolib3.core.builder.ILoopType
import software.bernie.geckolib3.core.controller.AnimationController
import software.bernie.geckolib3.core.event.predicate.AnimationEvent
import software.bernie.geckolib3.core.manager.AnimationData
import software.bernie.geckolib3.core.manager.AnimationFactory
import software.bernie.geckolib3.util.GeckoLibUtil
import java.util.function.Function

class AnemoneBlockEntity(pos: BlockPos, state: BlockState) : BlockEntity(HybridAquaticBlockEntityTypes.ANEMONE, pos, state), IAnimatable {
    private val factory = GeckoLibUtil.createFactory(this)

    private var hideTimer = 0
    private var cooldownTimer = 0
    private var hiddenClownfish: NbtCompound? = null

    fun tick(world: World) {
        if (world.isClient) {
            return
        }

        val clownfish = hiddenClownfish
        if (clownfish != null) {
            if (hideTimer > 0) {
                hideTimer--

                // end timer and release clownfish
                if (hideTimer <= 0) {
                    releaseClownfish(Vec3d.ofCenter(pos).add(0.0, 1.0, 0.0))
                    resetHiddenClownfish()
                }

                // update nbt
                markDirty()
            }
        } else {
            if (cooldownTimer > 0) {
                cooldownTimer--

                // update nbt
                markDirty()
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
        clownfish.removeAllPassengers()
        hiddenClownfish = NbtCompound().apply(clownfish::saveNbt)

        // start timer
        hideTimer = HIDE_DURATION

        // update nbt
        markDirty()

        return true
    }

    private fun releaseClownfish(releasePosition: Vec3d): Boolean {
        val world = world ?: return false
        val hiddenNbt = hiddenClownfish ?: return false

        val nbt = hiddenNbt.copy()
        removeIrrelevantNbtKeys(nbt)

        val clownfish = EntityType.loadEntityWithPassengers(nbt, world, Function.identity())
        if (clownfish !is ClownfishEntity) {
            return false
        }

        clownfish.refreshPositionAndAngles(releasePosition.x, releasePosition.y, releasePosition.z, 0f, 0f)
        world.spawnEntity(clownfish)

        return true
    }

    fun emergencyReleaseHiddenClownfish() {
        releaseClownfish(Vec3d.ofCenter(pos))
    }

    private fun resetHiddenClownfish() {
        hiddenClownfish = null
        hideTimer = 0
        cooldownTimer = COOLDOWN_DURATION
    }

    private fun <E> predicate(event: AnimationEvent<E>): PlayState where E : BlockEntity?, E : IAnimatable {
        return if (world != null) {
            event.controller.setAnimation(SWAY_ANIMATION)
            PlayState.CONTINUE
        } else {
            PlayState.STOP
        }
    }

    override fun registerControllers(data: AnimationData) {
        data.addAnimationController(AnimationController(this, "controller", 0.0f, ::predicate))
    }

    override fun getFactory(): AnimationFactory {
        return factory
    }

    override fun toInitialChunkDataNbt(): NbtCompound {
        return createNbt()
    }

    override fun writeNbt(nbt: NbtCompound) {
        hiddenClownfish?.let { clownfishNbt -> nbt.put("clownfish", clownfishNbt) }

        nbt.putInt("hide_timer", hideTimer)
        nbt.putInt("cooldown_timer", cooldownTimer)
    }

    override fun readNbt(nbt: NbtCompound) {
        if (nbt.contains("clownfish", NbtElement.COMPOUND_TYPE.toInt())) {
            val clownfishNbt = nbt.getCompound("clownfish")
            hiddenClownfish = clownfishNbt
        }

        hideTimer = nbt.getInt("hide_timer")
        cooldownTimer = nbt.getInt("cooldown_timer")
    }

    override fun toUpdatePacket(): BlockEntityUpdateS2CPacket {
        return BlockEntityUpdateS2CPacket.create(this)
    }

    companion object {
        private const val HIDE_DURATION = 200
        private const val COOLDOWN_DURATION = 40
        val SWAY_ANIMATION: AnimationBuilder = AnimationBuilder().addAnimation("sway", ILoopType.EDefaultLoopTypes.LOOP)

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

        @Suppress("UNUSED_PARAMETER")
        fun tick(world: World, pos: BlockPos, state: BlockState, blockEntity: AnemoneBlockEntity) {
            blockEntity.tick(world)
        }

        fun removeIrrelevantNbtKeys(compound: NbtCompound) {
            IRRELEVANT_NBT_KEYS.forEach(compound::remove)
        }
    }
}
