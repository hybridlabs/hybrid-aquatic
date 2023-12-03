package dev.hybridlabs.aquatic.block.entity

import dev.hybridlabs.aquatic.entity.fish.ClownfishEntity
import net.minecraft.block.BlockState
import net.minecraft.block.entity.BlockEntity
import net.minecraft.nbt.NbtCompound
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
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

class AnemoneBlockEntity(pos: BlockPos, state: BlockState) : BlockEntity(HybridAquaticBlockEntityTypes.ANEMONE, pos, state), GeoAnimatable {
    private val factory = GeckoLibUtil.createInstanceCache(this)

    private var hideTimer = 0
    private var cooldownTimer = 0
    private var hiddenClownfish: ClownfishEntity? = null

    fun tick(world: World) {
        if (world.isClient) {
            return
        }

        if (hideTimer > 0) {
            println("Hide Timer: $hideTimer")
            hideTimer--
            if (hideTimer <= 0 && hiddenClownfish != null) {
                println("Releasing clownfish...")
                releaseClownfish()
            }
        } else if (cooldownTimer > 0) {
            cooldownTimer--
        }
    }

    fun hasClownfish(): Boolean {
        return hiddenClownfish != null
    }

    fun setClownfish(clownfish: ClownfishEntity?) {
        if (cooldownTimer <= 0) {
            hiddenClownfish = clownfish
            hideTimer = HIDE_DURATION
            cooldownTimer = COOLDOWN_DURATION
            println("Clownfish set in anemone. Hide Timer: $hideTimer, Cooldown Timer: $cooldownTimer")
        }
    }

    private fun releaseClownfish() {
        world?.let { world ->
            hiddenClownfish?.let { clownfish ->
                clownfish.refreshPositionAndAngles(pos.x + 0.5, pos.y.toDouble(), pos.z + 0.5, 0f, 0f)
                world.spawnEntity(clownfish)
                hiddenClownfish = null
                println("Clownfish released from anemone.")
            }
        }
    }

    private fun <E> predicate(event: AnimationState<E>): PlayState where E : BlockEntity?, E : GeoAnimatable {
        return if (world != null) {
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

    override fun toInitialChunkDataNbt(): NbtCompound {
        return createNbt()
    }

    override fun toUpdatePacket(): BlockEntityUpdateS2CPacket {
        return BlockEntityUpdateS2CPacket.create(this)
    }

    companion object {
        private const val HIDE_DURATION = 200
        private const val COOLDOWN_DURATION = 40
        val SWAY_ANIMATION: RawAnimation = RawAnimation.begin().then("sway", Animation.LoopType.LOOP)

        @Suppress("UNUSED_PARAMETER")
        fun tick(world: World, pos: BlockPos, state: BlockState, blockEntity: AnemoneBlockEntity) {
            blockEntity.tick(world)
        }
    }
}
