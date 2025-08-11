package dev.hybridlabs.aquatic.block.entity

import net.minecraft.block.BlockState
import net.minecraft.block.entity.BlockEntity
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import software.bernie.geckolib.animatable.GeoAnimatable
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache
import software.bernie.geckolib.animation.*
import software.bernie.geckolib.util.GeckoLibUtil

class AnemoneBlockEntity(pos: BlockPos, state: BlockState) :
    BlockEntity(HybridAquaticBlockEntityTypes.ANEMONE, pos, state),
    GeoAnimatable {
    private val factory = GeckoLibUtil.createInstanceCache(this)

    fun tick(world: World) {
        if (world.isClient) {
            return
        }
    }

    private fun <E> predicate(event: AnimationState<E>): PlayState where E : BlockEntity, E : GeoAnimatable {
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

    override fun getTick(p0: Any?): Double {
        TODO("Not yet implemented")
    }

    override fun toUpdatePacket(): BlockEntityUpdateS2CPacket {
        return BlockEntityUpdateS2CPacket.create(this)
    }

    companion object {
        val SWAY_ANIMATION: RawAnimation = RawAnimation.begin().then("sway", Animation.LoopType.LOOP)

        @Suppress("UNUSED_PARAMETER")
        fun tick(world: World, pos: BlockPos, state: BlockState, blockEntity: AnemoneBlockEntity) {
            blockEntity.tick(world)
        }
    }
}
